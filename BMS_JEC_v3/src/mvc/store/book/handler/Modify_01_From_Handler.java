package mvc.store.book.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.BookDAO;
import mvc.store.dao.BookDAOIpml;
import mvc.store.dto.BookDTO;

public class Modify_01_From_Handler implements Command_Handler {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
	
		System.out.println("  -> Modify_01_From_Handler");
		
		int n = 0;
		int b_num	= Integer.parseInt(req.getParameter("b_num"));
		int pageNum	= Integer.parseInt(req.getParameter("pageNum"));
		
		BookDTO dto = new BookDTO();
		BookDAO dao = BookDAOIpml.getInstance();
		
		dto = dao.getArticles(b_num);
		req.setAttribute("dto", dto);
		
		System.out.println( "  -> 임시번호 설정" );
		n = dao.getCount()+1;
		req.setAttribute("n", n);

		req.setAttribute("num", b_num);
		req.setAttribute("pageNum", pageNum);
		
		return "/_Store/View/Book/06_Book_List_Modify_Form.jsp";	
	}

}
