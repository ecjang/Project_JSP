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
		
		
		// ���� �ޱ�
		if ( req.getParameter("quan") != null ){
			quan = Integer.parseInt(req.getParameter("quan"));
			c_num = Integer.parseInt(req.getParameter("c_num"));
			b_num = Integer.parseInt(req.getParameter("b_num"));
		}
		
		/*
		System.out.println("ordernum ��  : " + ordernum);
		System.out.println("b_num ��  : " + b_num);
		System.out.println("c_num ��  : " + c_num);
		*/

		CartDAO cart = new CartDAOImpl().getInstance();
		
		// ��� �ľ� 
		int book_num = cart.bookquanCnt(b_num);
		
		// �ֹ� ���� �ľ� 
		int cart_num = cart.cartquanCnt(c_num);
		
		
		int cart_quan = 0;
		int quan_abs = 0;
		
		// �ֹ������� ����� �۾ƾ� ����
		if ( quan <= book_num ){
			
			quan_abs = Math.abs(cart_num-quan);
			
			if ( cart_num > quan ){
				
				System.out.println("  -> �ֹ� ���� ���� : ��� �߰�");
				cart_quan = cart.cartquanMinus(c_num, quan_abs);
				
			} else if( cart_num < quan ){
				
				System.out.println("  -> �ֹ� ���� ���� : ��� ����");
				cart_quan = cart.cartquanPlus(c_num, quan_abs);
				
			} else {
				System.out.println("  -> �ֹ� ���� ���� ����");
				cart_quan = -1;
			}
			
			System.out.println("  -> cart_quan �� : " + cart_quan );

			
			
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
