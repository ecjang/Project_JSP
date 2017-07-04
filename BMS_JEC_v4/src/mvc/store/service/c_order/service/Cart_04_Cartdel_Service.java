package mvc.store.service.c_order.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.BookDAO;
import mvc.store.dao.BookDAOImpl;
import mvc.store.dao.CartDAO;
import mvc.store.dao.CartDAOImpl;
import mvc.store.service.Command_Handler;

public class Cart_04_Cartdel_Service implements Command_Handler {

	@Override
	public String service(HttpServletRequest req, HttpServletResponse res) {
		
		System.out.println("  -> Cart_04_Cartdel_Service");
		
		// 변수 받기
		System.out.println(req.getParameter("c_num"));
		int c_num =  Integer.parseInt(req.getParameter("c_num")) ;
	
		// 수량 조절 
		CartDAO cart = new CartDAOImpl().getInstance();
		int cnt = cart.cartdel(c_num);
		
		req.setAttribute("cnt", cnt);
		
		return "10_order_cartpro.do";	
	}

}
