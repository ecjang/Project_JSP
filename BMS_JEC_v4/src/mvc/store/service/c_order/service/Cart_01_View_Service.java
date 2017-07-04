package mvc.store.service.c_order.service;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.BookDAO;
import mvc.store.dao.BookDAOImpl;
import mvc.store.dao.CartDAO;
import mvc.store.dao.CartDAOImpl;
import mvc.store.dto.BookDTO;
import mvc.store.service.Command_Handler;

public class Cart_01_View_Service implements Command_Handler {

	
	@Override
	public String service(HttpServletRequest req, HttpServletResponse res) {

		System.out.println("  -> Cart_01_View_Service ");
		
		int cnt = 0;		// 카운트
		int state_cnt = 0;	// 상태값
		
		int start = 0;		// 시작번호
		int m_num = 0;		// 회원번호
		int b_num = 0;		// 서적번호
		int c_num = 0;		// 장바구니 번호
		int quan = 0;		// 장바구니 주문수량
		String state = null;	// 도서 상태
		
		// 로그인 체크 
		// 로그인 안 했으면 로그인 페이지로 이동
		if ( req.getSession().getAttribute("m_num") == null ){
			
				
			System.out.println( "  -> 로그인이 필요한 서비스" );
			/*return "/_Store/View/02_Login/01_Login_Main.jsp?cnt=3&back=/_Store/View/03_Order/01_Order_Cart.jsp";*/
			return "/_Store/View/02_Login/01_Login_Main.jsp?cnt=3";
			
			/*return "/_Store/View/02_Login/01_Login_Main.jsp?cnt=3&back=m3_header_order.do";*/
			/*return "m2_header_login.do?cnt=3&back=m3_header_order.do";*/
			
		
		// 로그인 했으면 로그인 한 회원 번호 출력	
		} else {	
			
			m_num = (Integer)req.getSession().getAttribute("m_num"); 
			System.out.println("    : 로그인한 회원번호 : " + m_num );
		
			
			BookDAO book = BookDAOImpl.getInstance();	// 서적 DAO
			CartDAO cart = CartDAOImpl.getInstance();	// 장바구니 DAO
			BookDTO dto = new BookDTO();					
			
			
			// 장바구니에 담긴 도서가 있을 때
			if(req.getParameter("b_num") != null){
				
				start 	= Integer.parseInt( req.getParameter("start") 	);		// 책번호
				b_num 	= Integer.parseInt( req.getParameter("b_num") 	);		// 책번호
				/*state 	= req.getParameter("state");	*/						// 도서 상태
				quan 	= Integer.parseInt( req.getParameter("quan") 	);	// 주문수량 */				
				
				state = "CART";
				
				// 도서 정보 가져오기
				dto = book.getBookInfo(b_num);
				
				dto.setB_num(b_num);	// 도서 번호
				dto.setM_num(m_num);	// 회원 번호
				dto.setQuan(quan);		// 주문 수량
				dto.setState(state);	// 도서 상태
				dto.setReg_date( new Timestamp(System.currentTimeMillis()) );	
		
				 
				state_cnt = cart.bookstate(dto);
				
				System.out.println("  -> state_cnt 값 : " + state_cnt);
				System.out.println("  -> [ 1:정상처리 / 2:삭제오류 / 3:재고수량이 적음  / 4:장바구니 입력 오류 / 0:재고없음 ] ");
				
				
				// state_cnt가 1일 때만 진행
				if(state_cnt == 1){
					
					if(req.getParameter("c_num") != null){
						
						c_num = Integer.parseInt( req.getParameter("c_num") );	// 카트번호
						quan = 	Integer.parseInt( req.getParameter("ordernum") 	);	// 주문수량
						
						req.setAttribute("c_num", c_num);
						req.setAttribute("quan", quan);
						req.setAttribute("state_cnt", state_cnt);
					}
				}
		
			
			// 장바구니에 담긴 도서가 있을 때	
			} else {
				/*System.out.println("  -> 장바구니에 담긴 도서 없음");*/
				state_cnt = 0;
				
			}
		}
		
		// 변수 넘김
		req.setAttribute("b_num", b_num);
		req.setAttribute("quan",quan);
		req.setAttribute("pageNum", req.getParameter("param.pageNum"));
		req.setAttribute("state_cnt", state_cnt);
		
		/*return "05_order_cartpro.do";	*/	
		 
		return "05_order_cartpro.do";			 
	}
}
