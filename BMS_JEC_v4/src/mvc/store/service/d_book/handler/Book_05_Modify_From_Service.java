package mvc.store.service.d_book.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.BookDAO;
import mvc.store.dao.BookDAOIpml;
import mvc.store.dto.BookDTO;
import mvc.store.service.Command_Handler;

public class Book_05_Modify_From_Service implements Command_Handler {

	@Override
	public String service(HttpServletRequest req, HttpServletResponse res) {
	
		System.out.println("  -> Modify_01_From_Handler");
		
		int n = 0;
		int b_num	= Integer.parseInt(req.getParameter("b_num"));
		int pageNum	= Integer.parseInt(req.getParameter("pageNum"));
		
		BookDTO dto = new BookDTO();
		BookDAO dao = BookDAOIpml.getInstance();
		
		dto = dao.getBookInfo(b_num);
		req.setAttribute("dto", dto);
		
		System.out.println( "  -> 임시번호 설정" );
		n = dao.BookCnt()+1;
		req.setAttribute("n", n);

		req.setAttribute("num", b_num);
		req.setAttribute("pageNum", pageNum);
		
		return "/_Store/View/04_Book/06_Book_Modify_Form.jsp";	
	}

}
