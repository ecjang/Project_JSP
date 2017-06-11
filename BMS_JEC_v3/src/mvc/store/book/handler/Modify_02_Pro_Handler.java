package mvc.store.book.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.BookDAO;
import mvc.store.dao.BookDAOIpml;
import mvc.store.dto.BookDTO;

public class Modify_02_Pro_Handler implements Command_Handler {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		
		System.out.println("  -> 도서정보 수정 적용 : Modify_02_Pro_Handler ");
		
		int b_num 	= Integer.parseInt( req.getParameter("b_num") 	);
		int pageNum = Integer.parseInt( req.getParameter("pageNum") );
	
		String title = req.getParameter("b_title");
		String subtitle =  req.getParameter("b_subtitle");
		String author =  req.getParameter("b_author");
		int price = Integer.parseInt( req.getParameter("b_price") );
		int quan =Integer.parseInt( req.getParameter("b_quan") );
		String kind = req.getParameter("b_kind");
		
		BookDTO dto = new BookDTO();
		BookDAO dao = BookDAOIpml.getInstance();
		
		dto.setB_num(b_num);
		dto.setTitle(title);
		dto.setSubtitle(subtitle);
		dto.setAuthor(author);
		dto.setPrice(price);
		dto.setQuan(quan);
		dto.setKind(kind);
		
		int cnt = dao.upadte(dto);

		req.setAttribute("cnt", cnt);
		req.setAttribute("pageNum", pageNum);
		
		return "/_Store/View/Book/07_Book_List_Modify_Pro.jsp";
	}

}
