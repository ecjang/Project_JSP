package mvc.store.service.c_order.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.BookDAO;
import mvc.store.dao.BookDAOIpml;
import mvc.store.dto.BookDTO;

public class Guest_Cart_03_Ordernum_Pro_Handler implements Command_Handler {

	@Override
	public String orderexecute(HttpServletRequest req, HttpServletResponse res) {
		
		// ���� �ޱ�
		int ordernum = Integer.parseInt(req.getParameter("ordernum"));
		int c_num = Integer.parseInt(req.getParameter("c_num"));
		int b_num = Integer.parseInt(req.getParameter("b_num"));
		int ordernum_cnt = 0;
		
		/*
		System.out.println("ordernum ��  : " + ordernum);
		System.out.println("b_num ��  : " + b_num);
		System.out.println("c_num ��  : " + c_num);
		*/

		
		BookDAO dao = new BookDAOIpml().getInstance();
		
		// ��� �ľ� 
		int book_num = dao.cart_quan(b_num);
		
		
		
		System.out.println("ordernum ��  : " + ordernum);
		System.out.println("book_num ��  : " + book_num);
		
		
		
		if ( ordernum <= book_num ){				// �ֹ������� ����� �۾ƾ� ����
			
			
			
			// ��ٱ��� ������ ����
			int cart_ordernum = dao.cart_ordernum(c_num, ordernum);	
			
			if(cart_ordernum>0){
				ordernum_cnt = 1;
			} else {
				ordernum_cnt = 2;
			}
			
		} else {
			ordernum_cnt = 0;
		}
		
		req.setAttribute("ordernum_cnt", ordernum_cnt);
		req.setAttribute("book_num", book_num);
	
		
		// ��ȯ�� ����
		return "guest_cart_pro.do";	
	}

}
