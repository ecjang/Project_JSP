package mvc.store.service.c_order.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.BookDAO;
import mvc.store.dao.BookDAOImpl;
import mvc.store.dao.OrderDAO;
import mvc.store.dao.OrderDAOImpl;
import mvc.store.service.Command_Handler;

public class Order_03_State_Service implements Command_Handler {

	@Override
	public String service(HttpServletRequest req, HttpServletResponse res) {
		
		
		System.out.println("  -> Order_03_State_Service");
		
		
		// 주문 상태 변환
		
		int o_num = Integer.parseInt( req.getParameter("o_num") );
		String state = req.getParameter("state");
		
		System.out.println( "  -> o_num 값 : " + o_num);
		System.out.println( "  -> state 값 : " + state);
		
		
		OrderDAO order = OrderDAOImpl.getInstance();
		
		
		
		int cnt = order.changeState(o_num,state);
		
		
		
		req.setAttribute("order_cnt", cnt);
		
		
		/*return "/_Store/View/03_Order/02_Order_Order.jsp";*/
		return "14_order_orderpro.do";
	}

}
