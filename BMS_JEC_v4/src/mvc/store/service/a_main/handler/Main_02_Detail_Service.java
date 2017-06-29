package mvc.store.service.a_main.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.BookDAO;
import mvc.store.dao.BookDAOIpml;
import mvc.store.dto.BookDTO;
import mvc.store.service.Command_Handler;

public class Main_02_Detail_Service implements Command_Handler {

	@Override
	public String service(HttpServletRequest req, HttpServletResponse res) {

		System.out.println("  -> Main_02_Detail_Service ");
		
		int b_num = Integer.parseInt( 	req.getParameter("b_num") );	// ���� ��ȸ�� å �ڵ� ��ȣ
		int pageNum = Integer.parseInt( req.getParameter("pageNum") );	// �˻������� ������ ��ȣ
		int start = Integer.parseInt( 	req.getParameter("start") );	// �˻������� ���� ��ȣ
		String str = req.getParameter("str");							// �˻������� �˻���
	
		
		BookDTO dto = new BookDTO();
		BookDAO dao = new BookDAOIpml().getInstance();
		
		dto = dao.getBookInfo(b_num);
		
		req.setAttribute("dto", dto);
		req.setAttribute("b_num", b_num);
		req.setAttribute("start", start);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("str", str);
		
		return "/_Store/View/01_Main/02_Main_Detail.jsp";	
	}
	
}
