package mvc.store.guest.handler;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.BookDAO;
import mvc.store.dao.BookDAOIpml;
import mvc.store.dto.BookDTO;

public class Guest_Cart_01_View_Handler implements Command_Handler {

	
	@Override
	public String orderexecute(HttpServletRequest req, HttpServletResponse res) {

		System.out.println("  -> Guest_Cart_01_View_Handler");
		
		int state_cnt = 0;
		int b_num = 0;
		int c_num = 0;
		int ordernum = 0;
		String state = null;
		
		
		
		// 로그인 체크 
		// 로그인 안 했으면 로그인 페이지로 이동
		if ( (Integer)req.getSession().getAttribute("m_num")==null ){
			System.out.println( "  -> 로그인 안됨" );
			return "/header_m2.do?cnt=3";
			
		} else {	// 로그인 했으면 로그인 한 회원 번호 출력
			int m_num = (Integer)req.getSession().getAttribute("m_num"); 
			System.out.println("    : 로그인한 회원번호 : " + m_num );
		}
		
		// 변수 받기 : 총4개
		// m_num / b_num / ordernum / state / 
		
		
		
		
		/*System.out.println("m_num 값 : " +  req.getSession().getAttribute("m_num")  );*/
		int m_num = 0;
		m_num= (Integer) req.getSession().getAttribute("m_num"); 
		
		// 세션에 회원 번호가 있을 경우
		if (m_num != 0 ){
/*//			m_num = (Integer) req.getSession().getAttribute("m_num"); 	// 회원번호
*/			
			
			BookDAO bdao = BookDAOIpml.getInstance();		// 데이터 조회 클래스
			BookDTO dto = new BookDTO();					// 도서 정보 클래스
			
			
			// 도서 번호가 있을 때
			if(req.getParameter("b_num") != null){
				b_num = 	Integer.parseInt( req.getParameter("b_num") 	);	// 책번호
				ordernum = 	Integer.parseInt( req.getParameter("ordernum") 	);	// 주문수량
				state = req.getParameter("state");			// 도서 상태
				
				dto = bdao.getArticles(b_num);
				dto.setB_num(b_num);	// 도서 번호
				dto.setM_num(m_num);	// 회원 번호
				dto.setQuan(ordernum);	// 주문 수량
				dto.setState(state);	// 도서 상태
				dto.setReg_date( new Timestamp(System.currentTimeMillis()) );	// 현재시간 설정
				
			}
			
			
			// 장바구니 번호가 있을 때
			if(req.getParameter("c_num") != null){
				c_num = 	Integer.parseInt( req.getParameter("c_num") 	);	// 책번호
				ordernum = 	Integer.parseInt( req.getParameter("ordernum") 	);	// 주문수량
										// 도서 상태
			}
			
			
			state_cnt = bdao.bookstate(dto);
			
			
			/*
			String pageNum = req.getParameter("pageNum");
			if(pageNum==null) pageNum="1";	// 페이지 번호
			*/
			
			
			/*
			 // 검색어는 더이상 필요없음
			 String str = 	req.getParameter("str");
			 int m_num = 	(Integer) req.getSession().getAttribute("m_num"	);	// 회원번호(세션용)
			 */
			
			
			// 입력 받은 변수로 장바구니 조회
			// 일단 필요한 생성자 생성 
			
			
			
			// 도서 테이블에서  도서 정보 가져오기
			// 정보를 불러와서 입력받은 정보를 대입
			
			
			
			
			// 수정한 정보를 담은 서적을 북 테이블에서 삭제
			// 주문한 만큼의 서적을 카트테이블에 추가
			// state_cnt -> 1:정상처리 / 2:삭제오류 / 3:재고수량이 적음 / 0:재고없음
			
			System.out.println("  -> state_cnt 값 : " + state_cnt);
			
			// state_cnt가 1일 때만 진행
			if(state_cnt == 1){
				
				
				
				// 남은 변수들은 넘기자
				req.setAttribute("b_num", b_num);
				req.setAttribute("c_num", c_num);
				req.setAttribute("pageNum", req.getParameter("param.pageNum"));
				/*req.setAttribute("start", req.getParameter("param.start"));*/
				return "/guest_cart_pro.do";
				
			} else {
				// state_cnt를 가지고 결과 페이지로 이동 
				req.setAttribute("state_cnt", state_cnt);
				return "/_Store/View/Guest/01_Guest_Cart.jsp";	
			}
			
		} else {	// 세션에 회원 정보가 없을 경우. 로그인으로 이동
			return "/header_m2.do?cnt=3";
		} 
	}
}
		