package mvc.store.main.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.BookDAO;
import mvc.store.dao.BookDAOIpml;
import mvc.store.dto.BookDTO;

public class Main_Search_02_Detail_Pro_Handler implements Command_Handler {

	@Override
	public String mainexecute(HttpServletRequest req, HttpServletResponse res, String str) {

		System.out.println("  -> Main_Search_02_Detail_Pro_Handler ");
		
		
		int b_num = Integer.parseInt( req.getParameter("b_num") );
		int pageNum = Integer.parseInt( req.getParameter("pageNum") );
		int start = Integer.parseInt( req.getParameter("start") );
		
		
		System.out.println(" - b_num : " + b_num);
		System.out.println(" - pageNum : " + pageNum);
		System.out.println(" - start : " + start);
		System.out.println(" - str : " + str);
		
		
		BookDTO dto = new BookDTO();
		BookDAO dao = new BookDAOIpml().getInstance();
		dto = dao.getArticles(b_num);
		
		req.setAttribute("dto", dto);
		req.setAttribute("b_num", b_num);
		req.setAttribute("start", start);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("str", str);
		
		
		/*
		System.out.println("  -> b_num의 값 " + b_num);
		System.out.println("  -> number의 값 " + number);
		System.out.println("  -> pageNum의 값 " + pageNum);
		*/
		
		return "/_Store/View/Main/02_Serch_Detail.jsp";	
	}
	
}
