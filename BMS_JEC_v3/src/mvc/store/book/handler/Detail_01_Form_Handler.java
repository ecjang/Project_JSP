package mvc.store.book.handler;

import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.BookDAO;
import mvc.store.dao.BookDAOIpml;
import mvc.store.dto.BookDTO;

public class Detail_01_Form_Handler implements Command_Handler  {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		
		System.out.println("  -> Detail_01_Form_Handler ");
		
		int b_num = Integer.parseInt( req.getParameter("b_num") );
		int number = Integer.parseInt( req.getParameter("number") );
		int pageNum = Integer.parseInt( req.getParameter("pageNum") );
		
		BookDTO dto = new BookDTO();
		BookDAO dao = new BookDAOIpml().getInstance();
		dto = dao.getArticles(b_num);
		
		req.setAttribute("dto", dto);
		req.setAttribute("b_num", b_num);
		req.setAttribute("number", number);
		req.setAttribute("pageNum", pageNum);
		
		
		/*
		System.out.println("  -> b_num의 값 " + b_num);
		System.out.println("  -> number의 값 " + number);
		System.out.println("  -> pageNum의 값 " + pageNum);
		*/
		
		return "/_Store/View/Book/03_Book_List_Detail.jsp";	
	}

}
