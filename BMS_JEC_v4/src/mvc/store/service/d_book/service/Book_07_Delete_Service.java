package mvc.store.service.d_book.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.BookDAO;
import mvc.store.dao.BookDAOImpl;
import mvc.store.service.Command_Handler;

public class Book_07_Delete_Service implements Command_Handler {

	@Override
	public String service(HttpServletRequest req, HttpServletResponse res) {
		
		System.out.println("  -> Book_07_Delete_Service ");
		
		int b_num = Integer.parseInt( req.getParameter("b_num") );
		int pageNum = Integer.parseInt( req.getParameter("pageNum") );
		
		BookDAO dao = BookDAOImpl.getInstance();
		
		int cnt = dao.delete(b_num);
		req.setAttribute("cnt", cnt);
		System.out.println("  -> cnt°ª :" + cnt);
		
		req.setAttribute("cnt", cnt);
		req.setAttribute("pageNum", pageNum);
		
		return "/_Store/View/04_Book/08_Book_Delete_Pro.jsp";
	}

}
