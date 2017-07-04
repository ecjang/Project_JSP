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
		
		// ���� �ޱ�
			int quan = Integer.parseInt(req.getParameter("quan")) ;
			int c_num =  Integer.parseInt(req.getParameter("c_num")) ;
			int orderCnt =0;
			/*int b_num =  Integer.parseInt(req.getParameter("b_num")) ;*/
			
			/*
			System.out.println("quan ��  : " + quan );
			System.out.println("c_num ��  : " + c_num );
			*/
			/*System.out.println("b_num ��  : " + b_num);*/
			
			CartDAO dao = new CartDAOImpl().getInstance();
			
			
			// ���� �ҷ�����
			CartDTO cart = dao.cart_getCart(c_num, quan);
			
			
			// ���� �����ϱ�
			orderCnt = dao.cart_putOrder(cart);
			
			
			// �ֹ�������� �̵� ������
			if ( orderCnt > 0 ){
				
				// ��ٱ��� ��� ����
				dao.cartdel(c_num);
				
			}
			
			req.setAttribute("orderCnt", orderCnt);
			System.out.println("  -> orderCnt : " + orderCnt);
			
			
			return "10_order_cartpro.do";
			
		
	}

}
