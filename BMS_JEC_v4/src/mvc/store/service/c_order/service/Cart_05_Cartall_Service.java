package mvc.store.service.c_order.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.BookDAO;
import mvc.store.dao.BookDAOImpl;
import mvc.store.dao.CartDAO;
import mvc.store.dao.CartDAOImpl;
import mvc.store.dto.BookDTO;
import mvc.store.dto.CartDTO;
import mvc.store.service.Command_Handler;

public class Cart_05_Cartall_Service implements Command_Handler  {

	@Override
	public String service(HttpServletRequest req, HttpServletResponse res) {
		
		System.out.println("  -> Cart_05_Cartall_Service");
		
		
		int cartall = Integer.parseInt(req.getParameter("cartall"));
		
		// ���� �ޱ� : ȸ����ȣ
		int m_num = (Integer)req.getSession().getAttribute("m_num");
		
		CartDAO cart = new CartDAOImpl().getInstance();
		
		
		if(cartall == 0){
			// īƮ ���� �ҷ����� -> ����Ʈ�� ���
			ArrayList<CartDTO> carts = cart.cart_getall( m_num );
			
			// �ֹ� ���̺� ���
			cartall = cart.cart_moveorder( carts );
		}
		
		// īƮ ���̺� ���� id �˻�
		if ( cartall > 0 ) cart.cart_afterdel(m_num);
		
		req.setAttribute("cartall", cartall);
		
		
		return "11_order_cartpro.do";
	}

}
