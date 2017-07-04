package mvc.store.service.c_order.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.BookDAO;
import mvc.store.dao.BookDAOImpl;
import mvc.store.dao.CartDAO;
import mvc.store.dao.CartDAOImpl;
import mvc.store.dto.BookDTO;
import mvc.store.dto.CartDTO;
import mvc.store.service.Command_Handler;

public class Cart_06_Order_Service implements Command_Handler {

	@Override
	public String service(HttpServletRequest req, HttpServletResponse res) {
		
		System.out.println("  -> Cart_06_Order_Service");
		
		// 변수 받기
			int quan = Integer.parseInt(req.getParameter("quan")) ;
			int c_num =  Integer.parseInt(req.getParameter("c_num")) ;
			int orderCnt =0;
			/*int b_num =  Integer.parseInt(req.getParameter("b_num")) ;*/
			
			/*
			System.out.println("quan 값  : " + quan );
			System.out.println("c_num 값  : " + c_num );
			*/
			/*System.out.println("b_num 값  : " + b_num);*/
			
			CartDAO dao = new CartDAOImpl().getInstance();
			
			
			// 정보 불러오기
			CartDTO cart = dao.cart_getCart(c_num, quan);
			
			
			// 정보 대입하기
			orderCnt = dao.cart_putOrder(cart);
			
			
			// 주문목록으로 이동 성공시
			if ( orderCnt > 0 ){
				
				// 장바구니 목록 삭제
				dao.cartdel(c_num);
				
			}
			
			req.setAttribute("orderCnt", orderCnt);
			System.out.println("  -> orderCnt : " + orderCnt);
			
			
			return "10_order_cartpro.do";
			
		
	}

}
