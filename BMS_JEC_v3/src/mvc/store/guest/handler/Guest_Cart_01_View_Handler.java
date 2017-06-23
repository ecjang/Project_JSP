package mvc.store.guest.handler;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.BookDAO;
import mvc.store.dao.BookDAOIpml;
import mvc.store.dto.BookDTO;

public class Guest_Cart_01_View_Handler implements Command_Handler {

	
	@Override
	public String orderexecute(HttpServletRequest req, HttpServletResponse res) {

		System.out.println("  -> Guest_Cart_01_View_Handler");
		
		int state_cnt = 0;
		int b_num = 0;
		int c_num = 0;
		int ordernum = 0;
		String state = null;
		
		
		
		// �α��� üũ 
		// �α��� �� ������ �α��� �������� �̵�
		if ( (Integer)req.getSession().getAttribute("m_num")==null ){
			System.out.println( "  -> �α��� �ȵ�" );
			return "/header_m2.do?cnt=3";
			
		} else {	// �α��� ������ �α��� �� ȸ�� ��ȣ ���
			int m_num = (Integer)req.getSession().getAttribute("m_num"); 
			System.out.println("    : �α����� ȸ����ȣ : " + m_num );
		}
		
		// ���� �ޱ� : ��4��
		// m_num / b_num / ordernum / state / 
		
		
		
		
		/*System.out.println("m_num �� : " +  req.getSession().getAttribute("m_num")  );*/
		int m_num = 0;
		m_num= (Integer) req.getSession().getAttribute("m_num"); 
		
		// ���ǿ� ȸ�� ��ȣ�� ���� ���
		if (m_num != 0 ){
/*//			m_num = (Integer) req.getSession().getAttribute("m_num"); 	// ȸ����ȣ
*/			
			
			BookDAO bdao = BookDAOIpml.getInstance();		// ������ ��ȸ Ŭ����
			BookDTO dto = new BookDTO();					// ���� ���� Ŭ����
			
			
			// ���� ��ȣ�� ���� ��
			if(req.getParameter("b_num") != null){
				b_num = 	Integer.parseInt( req.getParameter("b_num") 	);	// å��ȣ
				ordernum = 	Integer.parseInt( req.getParameter("ordernum") 	);	// �ֹ�����
				state = req.getParameter("state");			// ���� ����
				
				dto = bdao.getArticles(b_num);
				dto.setB_num(b_num);	// ���� ��ȣ
				dto.setM_num(m_num);	// ȸ�� ��ȣ
				dto.setQuan(ordernum);	// �ֹ� ����
				dto.setState(state);	// ���� ����
				dto.setReg_date( new Timestamp(System.currentTimeMillis()) );	// ����ð� ����
				
			}
			
			
			// ��ٱ��� ��ȣ�� ���� ��
			if(req.getParameter("c_num") != null){
				c_num = 	Integer.parseInt( req.getParameter("c_num") 	);	// å��ȣ
				ordernum = 	Integer.parseInt( req.getParameter("ordernum") 	);	// �ֹ�����
										// ���� ����
			}
			
			
			state_cnt = bdao.bookstate(dto);
			
			
			/*
			String pageNum = req.getParameter("pageNum");
			if(pageNum==null) pageNum="1";	// ������ ��ȣ
			*/
			
			
			/*
			 // �˻���� ���̻� �ʿ����
			 String str = 	req.getParameter("str");
			 int m_num = 	(Integer) req.getSession().getAttribute("m_num"	);	// ȸ����ȣ(���ǿ�)
			 */
			
			
			// �Է� ���� ������ ��ٱ��� ��ȸ
			// �ϴ� �ʿ��� ������ ���� 
			
			
			
			// ���� ���̺���  ���� ���� ��������
			// ������ �ҷ��ͼ� �Է¹��� ������ ����
			
			
			
			
			// ������ ������ ���� ������ �� ���̺��� ����
			// �ֹ��� ��ŭ�� ������ īƮ���̺� �߰�
			// state_cnt -> 1:����ó�� / 2:�������� / 3:�������� ���� / 0:������
			
			System.out.println("  -> state_cnt �� : " + state_cnt);
			
			// state_cnt�� 1�� ���� ����
			if(state_cnt == 1){
				
				
				
				// ���� �������� �ѱ���
				req.setAttribute("b_num", b_num);
				req.setAttribute("c_num", c_num);
				req.setAttribute("pageNum", req.getParameter("param.pageNum"));
				/*req.setAttribute("start", req.getParameter("param.start"));*/
				return "/guest_cart_pro.do";
				
			} else {
				// state_cnt�� ������ ��� �������� �̵� 
				req.setAttribute("state_cnt", state_cnt);
				return "/_Store/View/Guest/01_Guest_Cart.jsp";	
			}
			
		} else {	// ���ǿ� ȸ�� ������ ���� ���. �α������� �̵�
			return "/header_m2.do?cnt=3";
		} 
	}
}
		