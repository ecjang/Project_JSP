package mvc.store.service.d_book.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.BookDAO;
import mvc.store.dao.BookDAOIpml;
import mvc.store.dto.BookDTO;
import mvc.store.service.Command_Handler;

public class Book_02_Detail_Service implements Command_Handler  {

	@Override
	public String service(HttpServletRequest req, HttpServletResponse res) {
		
		System.out.println("  -> Book_02_Detail_Service ");
		
		int b_num = Integer.parseInt( req.getParameter("b_num") );
		int number = Integer.parseInt( req.getParameter("number") );
		int pageNum = Integer.parseInt( req.getParameter("pageNum") );
		
		BookDTO dto = new BookDTO();
		BookDAO dao = new BookDAOIpml().getInstance();
		
		dto = dao.getBookInfo(b_num);
		
		req.setAttribute("dto", dto);
		req.setAttribute("b_num", b_num);
		req.setAttribute("number", number);
		req.setAttribute("pageNum", pageNum);
		
		req.setAttribute("b_num", 0);
		
		/*
		System.out.println("  -> b_num의 값 " + b_num);
		System.out.println("  -> number의 값 " + number);
		System.out.println("  -> pageNum의 값 " + pageNum);
		*/
		
		return "/_Store/View/04_Book/02_Book_Detail.jsp";	
	}

}
