package mvc.store.main.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.BookDAO;
import mvc.store.dao.BookDAOIpml;
import mvc.store.dto.BookDTO;

public class Main_Search_02_Detail_Pro_Handler implements Command_Handler {

	@Override
	public String mainexecute(HttpServletRequest req, HttpServletResponse res) {

		System.out.println("  -> Main_Search_02_Detail_Pro_Handler ");
		
		int b_num = Integer.parseInt( 	req.getParameter("b_num") );	// 도서 조회용 책 코드 번호
		int pageNum = Integer.parseInt( req.getParameter("pageNum") );	// 검색페이지 페이지 번호
		int start = Integer.parseInt( 	req.getParameter("start") );	// 검색페이지 순서 번호
		String str = req.getParameter("str");							// 검색페이지 검색어
		/*
		System.out.println("  - b_num : " + b_num);
		System.out.println("  - pageNum : " + pageNum);
		System.out.println("  - start : " + start);
		System.out.println("  - str : " + str);
		*/
		BookDTO dto = new BookDTO();
		BookDAO dao = new BookDAOIpml().getInstance();
		dto = dao.getArticles(b_num);
		
		req.setAttribute("dto", dto);
		req.setAttribute("b_num", b_num);
		req.setAttribute("start", start);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("str", str);
		
		return "/_Store/View/Main/02_Serch_Detail.jsp";	
	}
	
}
