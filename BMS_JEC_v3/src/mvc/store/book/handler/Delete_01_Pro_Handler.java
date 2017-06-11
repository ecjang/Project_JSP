package mvc.store.book.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.BookDAO;
import mvc.store.dao.BookDAOIpml;

public class Delete_01_Pro_Handler implements Command_Handler {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		
		System.out.println("  -> Delete_01_Pro_Handler ");
		
		int b_num = Integer.parseInt( req.getParameter("b_num") );
		int pageNum = Integer.parseInt( req.getParameter("pageNum") );
		
		BookDAO dao = BookDAOIpml.getInstance();
		
		int cnt = dao.delete(b_num);
		req.setAttribute("cnt", cnt);
		System.out.println("  -> cnt°ª :" + cnt);
		
		req.setAttribute("cnt", cnt);
		req.setAttribute("pageNum", pageNum);
		
		return "/_Store/View/Book/08_Book_List_Delete_Pro.jsp";
	}

}
