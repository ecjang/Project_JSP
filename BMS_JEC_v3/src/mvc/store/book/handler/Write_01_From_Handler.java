package mvc.store.book.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.BookDAO;
import mvc.store.dao.BookDAOIpml;

public class Write_01_From_Handler implements Command_Handler {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		
		System.out.println("  -> �Ű� ���� �Է� : Write_01_From_Handler ");
		/*
		System.out.println("  -> b_num�� : " + req.getParameter("b_num") );
		System.out.println("  -> pageNum�� : " + req.getParameter("pageNum") );
		*/
		int b_num=0;
		int pageNum =0;
		int amincnt = (Integer) req.getAttribute("amincnt");
		
		
		if( amincnt != 1 ){
			b_num = Integer.parseInt( req.getParameter("b_num") );
			pageNum = Integer.parseInt( req.getParameter("pageNum") );
		}
		
		System.out.println("  -> b_num�� : " + b_num);
		
		BookDAO dao = BookDAOIpml.getInstance();
		
		int n = dao.getCount() + 1;
		System.out.println("  -> �ӽ� ��ȣ ���� : " + n);
		
		req.setAttribute("n", n);
		req.setAttribute("b_num", b_num);
		req.setAttribute("pageNum", pageNum);

		return "/_Store/View/Book/04_Book_List_Write_Form.jsp";	
	}

}
