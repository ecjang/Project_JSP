package mvc.store.guest.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.BookDAO;
import mvc.store.dao.BookDAOIpml;

public class Guest_Order_02_Pro_Handler implements Command_Handler {

	@Override
	public String orderexecute(HttpServletRequest req, HttpServletResponse res) {
		
		// 주문 상태 변환
		
		int o_num = Integer.parseInt( req.getParameter("o_num") );
		String state = req.getParameter("state");
		
		System.out.println( " o_num 값 : " + o_num);
		System.out.println( " state 값 : " + state);
		
		BookDAO dao = BookDAOIpml.getInstance();
		
		int cnt = dao.changeState(o_num,state);
		
		
		
		
		return "guest_order.do"; 
	}

}
