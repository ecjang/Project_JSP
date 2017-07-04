package mvc.store.service.c_order.service;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.BookDAO;
import mvc.store.dao.BookDAOImpl;
import mvc.store.dao.OrderDAO;
import mvc.store.dao.OrderDAOImpl;
import mvc.store.dto.BookDTO;
import mvc.store.service.Command_Handler;

public class Order_01_View_Service implements Command_Handler {

	
	@Override
	public String service(HttpServletRequest req, HttpServletResponse res) {

		System.out.println("  -> Order_01_View_Service ");
		
		int cnt = 0;		// 카운트
		int state_cnt = 0;	// 상태값
		
		int start = 0;		// 시작번호 
		int m_num = 0;		// 회원번호
		int b_num = 0;		// 서적번호
		int o_num = 0;		// 주문 번호
		int c_num = 0;		// 주문 번호
		int quan = 0;		// 주문 수량
		String state = null;	// 도서 상태
		
		// 로그인 체크 
		// 로그인 안 했으면 로그인 페이지로 이동
		if ( req.getSession().getAttribute("m_num") == null ){
			
				
			System.out.println( "  -> 로그인이 aa 서비스" );
			return "/_Store/View/02_Login/01_Login_Main.jsp?cnt=3";

		
		// 로그인 했으면 로그인 한 회원 번호 출력	
		} else {	
			
			m_num = (Integer)req.getSession().getAttribute("m_num"); 
			System.out.println("    : 로그인한 회원번호 : " + m_num );
		
			BookDAO book = BookDAOImpl.getInstance();	// 서적 DAO
			OrderDAO order = OrderDAOImpl.getInstance();	// 주문 DAO
			
			BookDTO dto = new BookDTO();					
			
			
			
			
			// 주문목록에 담긴 도서가 있을 때
			
			if(req.getParameter("b_num") != null){	
				
				
				start 	= Integer.parseInt( req.getParameter("start") 	);		// 책번호
				b_num 	= Integer.parseInt( req.getParameter("b_num") 	);		// 책번호
				
				/*state 	= req.getParameter("state");*/							// 도서 상태
				
				quan 	= Integer.parseInt( req.getParameter("quan") 	);		// 주문수량 */				
				
				state = "ORDER";
				
				
				// 도서 정보 가져오기
				dto = book.getBookInfo(b_num);
				
				dto.setB_num(b_num);	// 도서 번호
				dto.setM_num(m_num);	// 회원 번호
				dto.setQuan(quan);		// 주문 수량
				dto.setState(state);	// 도서 상태
				dto.setReg_date( new Timestamp(System.currentTimeMillis()) );	
				 
				state_cnt = order.bookstate(dto);
				
				System.out.println("  -> state_cnt 값 : " + state_cnt);
				System.out.println("  -> [ 1:정상처리 / 2:삭제오류 / 3:재고수량이 적음  / 4:장바구니 입력 오류 / 0:재고없음 ] ");
				
				
				// state_cnt가 1일 때만 진행
				if(state_cnt == 1){
					
					if(req.getParameter("o_num") != null){
						
						o_num = Integer.parseInt( req.getParameter("o_num") );	// 주문번호
						quan = 	Integer.parseInt( req.getParameter("ordernum") 	);	// 주문수량
						
						req.setAttribute("o_num", o_num);
						req.setAttribute("quan", quan);
						req.setAttribute("state_cnt", state_cnt);
					}
				}
		
			
			// 주문에 담긴 도서가 있을 때	
			} else {
				
				/*System.out.println("  -> 주문에 담긴 도서 없음");*/
				state_cnt = 0;
				
			}
		}
		
		// 변수 넘김
		req.setAttribute("b_num", b_num);
		req.setAttribute("quan",quan);
		req.setAttribute("pageNum", req.getParameter("param.pageNum"));
		req.setAttribute("state_cnt", state_cnt);
		
		/*return "05_order_cartpro.do";	*/	
		 
		return "15_order_orderpro.do";			 
	}
}
