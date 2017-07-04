package mvc.store.service.c_order.service;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.BookDAO;
import mvc.store.dao.BookDAOImpl;
import mvc.store.dao.CartDAO;
import mvc.store.dao.CartDAOImpl;
import mvc.store.dto.BookDTO;
import mvc.store.service.Command_Handler;

public class Cart_01_View_Service implements Command_Handler {

	
	@Override
	public String service(HttpServletRequest req, HttpServletResponse res) {

		System.out.println("  -> Cart_01_View_Service ");
		
		int cnt = 0;		// ī��Ʈ
		int state_cnt = 0;	// ���°�
		
		int start = 0;		// ���۹�ȣ
		int m_num = 0;		// ȸ����ȣ
		int b_num = 0;		// ������ȣ
		int c_num = 0;		// ��ٱ��� ��ȣ
		int quan = 0;		// ��ٱ��� �ֹ�����
		String state = null;	// ���� ����
		
		// �α��� üũ 
		// �α��� �� ������ �α��� �������� �̵�
		if ( req.getSession().getAttribute("m_num") == null ){
			
				
			System.out.println( "  -> �α����� �ʿ��� ����" );
			/*return "/_Store/View/02_Login/01_Login_Main.jsp?cnt=3&back=/_Store/View/03_Order/01_Order_Cart.jsp";*/
			return "/_Store/View/02_Login/01_Login_Main.jsp?cnt=3";
			
			/*return "/_Store/View/02_Login/01_Login_Main.jsp?cnt=3&back=m3_header_order.do";*/
			/*return "m2_header_login.do?cnt=3&back=m3_header_order.do";*/
			
		
		// �α��� ������ �α��� �� ȸ�� ��ȣ ���	
		} else {	
			
			m_num = (Integer)req.getSession().getAttribute("m_num"); 
			System.out.println("    : �α����� ȸ����ȣ : " + m_num );
		
			
			BookDAO book = BookDAOImpl.getInstance();	// ���� DAO
			CartDAO cart = CartDAOImpl.getInstance();	// ��ٱ��� DAO
			BookDTO dto = new BookDTO();					
			
			
			// ��ٱ��Ͽ� ��� ������ ���� ��
			if(req.getParameter("b_num") != null){
				
				start 	= Integer.parseInt( req.getParameter("start") 	);		// å��ȣ
				b_num 	= Integer.parseInt( req.getParameter("b_num") 	);		// å��ȣ
				/*state 	= req.getParameter("state");	*/						// ���� ����
				quan 	= Integer.parseInt( req.getParameter("quan") 	);	// �ֹ����� */				
				
				state = "CART";
				
				// ���� ���� ��������
				dto = book.getBookInfo(b_num);
				
				dto.setB_num(b_num);	// ���� ��ȣ
				dto.setM_num(m_num);	// ȸ�� ��ȣ
				dto.setQuan(quan);		// �ֹ� ����
				dto.setState(state);	// ���� ����
				dto.setReg_date( new Timestamp(System.currentTimeMillis()) );	
		
				 
				state_cnt = cart.bookstate(dto);
				
				System.out.println("  -> state_cnt �� : " + state_cnt);
				System.out.println("  -> [ 1:����ó�� / 2:�������� / 3:�������� ����  / 4:��ٱ��� �Է� ���� / 0:������ ] ");
				
				
				// state_cnt�� 1�� ���� ����
				if(state_cnt == 1){
					
					if(req.getParameter("c_num") != null){
						
						c_num = Integer.parseInt( req.getParameter("c_num") );	// īƮ��ȣ
						quan = 	Integer.parseInt( req.getParameter("ordernum") 	);	// �ֹ�����
						
						req.setAttribute("c_num", c_num);
						req.setAttribute("quan", quan);
						req.setAttribute("state_cnt", state_cnt);
					}
				}
		
			
			// ��ٱ��Ͽ� ��� ������ ���� ��	
			} else {
				/*System.out.println("  -> ��ٱ��Ͽ� ��� ���� ����");*/
				state_cnt = 0;
				
			}
		}
		
		// ���� �ѱ�
		req.setAttribute("b_num", b_num);
		req.setAttribute("quan",quan);
		req.setAttribute("pageNum", req.getParameter("param.pageNum"));
		req.setAttribute("state_cnt", state_cnt);
		
		/*return "05_order_cartpro.do";	*/	
		 
		return "05_order_cartpro.do";			 
	}
}
