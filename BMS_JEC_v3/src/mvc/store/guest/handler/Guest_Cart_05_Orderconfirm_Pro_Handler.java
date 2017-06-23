package mvc.store.guest.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.BookDAO;
import mvc.store.dao.BookDAOIpml;
import mvc.store.dto.BookDTO;

public class Guest_Cart_05_Orderconfirm_Pro_Handler implements Command_Handler {

	@Override
	public String orderexecute(HttpServletRequest req, HttpServletResponse res) {
		
		// 변수 받기
			int ordernum = Integer.parseInt(req.getParameter("ordernum")) ;
			int c_num =  Integer.parseInt(req.getParameter("c_num")) ;
			int confirm_cnt =0;
			/*int b_num =  Integer.parseInt(req.getParameter("b_num")) ;*/
			
			
			System.out.println("ordernum 값  : " + ordernum);
			System.out.println("c_num 값  : " + c_num);
			
			/*System.out.println("b_num 값  : " + b_num);*/
			
			
			BookDAO dao = new BookDAOIpml().getInstance();
			
			
			// 정보 불러오기
			BookDTO dto = dao.cart_input(c_num , ordernum);
			
			// 정보 대입하기
			confirm_cnt = dao.cart_output(dto);
			
			
			// 주문목록으로 이동 성공시
			if ( confirm_cnt > 0 ){
				
				// 장바구니 목록 삭제
				dao.cart_orderdel(c_num);
				
			}
			
			req.setAttribute("confirm_cnt", confirm_cnt);
			
			return "guest_cart_pro.do";
			
		
	}

}
