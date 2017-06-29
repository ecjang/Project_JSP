package mvc.store.service.d_book.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.BookDAO;
import mvc.store.dao.BookDAOIpml;
import mvc.store.service.Command_Handler;

public class Book_03_Write_Form_Service implements Command_Handler {

	@Override
	public String service(HttpServletRequest req, HttpServletResponse res) {
		
		System.out.println("  -> Book_03_Write_Form_Service ");
		
		int b_num=0;
		int pageNum =0;
		
		
		if( req.getAttribute("amincnt")!=null ){
			
			int amincnt = (Integer) req.getAttribute("amincnt");
			if( amincnt != 1 ){
				b_num = Integer.parseInt( req.getParameter("b_num") );
				pageNum = Integer.parseInt( req.getParameter("pageNum") );
			}
		
		}
		
		System.out.println("  -> b_num값 : " + b_num);
		
		BookDAO dao = BookDAOIpml.getInstance();
		
		int n = dao.BookCnt() + 1;
		System.out.println("  -> 임시 번호 생성 : " + n);
		
		req.setAttribute("n", n);
		req.setAttribute("b_num", b_num);
		req.setAttribute("pageNum", pageNum);

		return "/_Store/View/04_Book/03_Book_Write_Form.jsp";	
	}

}
