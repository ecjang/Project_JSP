package mvc.store.service.c_order.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.BookDAO;
import mvc.store.dao.BookDAOImpl;
import mvc.store.dao.CartDAO;
import mvc.store.dao.CartDAOImpl;
import mvc.store.dto.BookDTO;
import mvc.store.service.Command_Handler;

public class Cart_03_Cartnum_Service implements Command_Handler {

	@Override
	public String service(HttpServletRequest req, HttpServletResponse res) {
		
		System.out.println("  -> Cart_03_Cartnum_Service");
		
		int quan=0;
		int c_num=0;
		int b_num=0;
		int quan_cnt=0;
		
		
		// 변수 받기
		if ( req.getParameter("quan") != null ){
			quan = Integer.parseInt(req.getParameter("quan"));
			c_num = Integer.parseInt(req.getParameter("c_num"));
			b_num = Integer.parseInt(req.getParameter("b_num"));
		}
		
		/*
		System.out.println("ordernum 값  : " + ordernum);
		System.out.println("b_num 값  : " + b_num);
		System.out.println("c_num 값  : " + c_num);
		*/

		CartDAO cart = new CartDAOImpl().getInstance();
		
		// 재고 파악 
		int book_num = cart.bookquanCnt(b_num);
		
		// 주문 수량 파악 
		int cart_num = cart.cartquanCnt(c_num);
		
		
		int cart_quan = 0;
		int quan_abs = 0;
		
		// 주문수량이 재고보다 작아야 가능
		if ( quan <= book_num ){
			
			quan_abs = Math.abs(cart_num-quan);
			
			if ( cart_num > quan ){
				
				System.out.println("  -> 주문 수량 감소 : 재고 추가");
				cart_quan = cart.cartquanMinus(c_num, quan_abs);
				
			} else if( cart_num < quan ){
				
				System.out.println("  -> 주문 수량 증가 : 재고 감소");
				cart_quan = cart.cartquanPlus(c_num, quan_abs);
				
			} else {
				System.out.println("  -> 주문 수량 변동 없음");
				cart_quan = -1;
			}
			
			System.out.println("  -> cart_quan 값 : " + cart_quan );

			
			
			/*
			if(cart_quan>0){
				quan_cnt = 1;
				
			} else {
				quan_cnt = 2;
			}
			*/
			
			
		} else {
			quan_cnt = 0;
		}
		
		
	req.setAttribute("cart_quan", cart_quan);
	req.setAttribute("book_num", book_num);

	return "08_order_cartpro.do";	
	}
	
}
