package mvc.store.service.c_order.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.BookDAO;
import mvc.store.dao.BookDAOIpml;

public class Guest_Cart_04_Orderdel_Pro_Handler implements Command_Handler {

	@Override
	public String orderexecute(HttpServletRequest req, HttpServletResponse res) {

		// 변수 받기
		
		System.out.println(req.getParameter("c_num"));
		int c_num =  Integer.parseInt(req.getParameter("c_num")) ;
		
		
		// 수량 조절 sql
		BookDAO dao = new BookDAOIpml().getInstance();
		int cnt = dao.cart_orderdel(c_num);
		req.setAttribute("cnt", cnt);
		
		
		// 반환값 리턴
		return "guest_cart_pro.do";	
	}

}
