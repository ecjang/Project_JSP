package mvc.store.book.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.BookDAO;
import mvc.store.dao.BookDAOIpml;

public class Write_01_From_Handler implements Command_Handler {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		
		System.out.println("  -> 신간 도서 입력 : Write_01_From_Handler ");
		/*
		System.out.println("  -> b_num값 : " + req.getParameter("b_num") );
		System.out.println("  -> pageNum값 : " + req.getParameter("pageNum") );
		*/
		int b_num = Integer.parseInt( req.getParameter("b_num") );
		int pageNum = Integer.parseInt( req.getParameter("pageNum") );
		
		System.out.println("  -> b_num값 : " + b_num);
		
		BookDAO dao = BookDAOIpml.getInstance();
		
		int n = dao.getCount() + 1;
		System.out.println("  -> 임시 번호 생성 : " + n);
		
		req.setAttribute("n", n);
		req.setAttribute("b_num", b_num);
		req.setAttribute("pageNum", pageNum);

		return "/_Store/View/Book/04_Book_List_Write_Form.jsp";	
	}

}
