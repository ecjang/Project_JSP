package mvc.store.guest.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.BookDAO;
import mvc.store.dao.BookDAOIpml;
import mvc.store.dto.BookDTO;

public class Guest_Cart_05_Orderconfirm_Pro_Handler implements Command_Handler {

	@Override
	public String orderexecute(HttpServletRequest req, HttpServletResponse res) {
		
		// ���� �ޱ�
			int ordernum = Integer.parseInt(req.getParameter("ordernum")) ;
			int c_num =  Integer.parseInt(req.getParameter("c_num")) ;
			int confirm_cnt =0;
			/*int b_num =  Integer.parseInt(req.getParameter("b_num")) ;*/
			
			
			System.out.println("ordernum ��  : " + ordernum);
			System.out.println("c_num ��  : " + c_num);
			
			/*System.out.println("b_num ��  : " + b_num);*/
			
			
			BookDAO dao = new BookDAOIpml().getInstance();
			
			
			// ���� �ҷ�����
			BookDTO dto = dao.cart_input(c_num , ordernum);
			
			// ���� �����ϱ�
			confirm_cnt = dao.cart_output(dto);
			
			
			// �ֹ�������� �̵� ������
			if ( confirm_cnt > 0 ){
				
				// ��ٱ��� ��� ����
				dao.cart_orderdel(c_num);
				
			}
			
			req.setAttribute("confirm_cnt", confirm_cnt);
			
			return "guest_cart_pro.do";
			
		
	}

}
