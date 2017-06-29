package mvc.store.service.d_book.handler;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import mvc.store.dao.BookDAO;
import mvc.store.dao.BookDAOIpml;
import mvc.store.dto.BookDTO;
import mvc.store.service.Command_Handler;

public class Book_04_Write_Pro_Service implements Command_Handler {

	@Override
	public String service(HttpServletRequest req, HttpServletResponse res) {
		
		
		System.out.println("  -> Book_04_Write_Pro_Service ");
		
		
		if( req.getSession().getAttribute("m_num") == null ){
			System.out.println("  -> 로그인 필요. ");
			return "/_Store/View/Login/01_Login_Main.jsp";		
		}
		
		int m_num = (Integer) req.getSession().getAttribute("m_num");
		
		
		BookDTO dto = new BookDTO();
		
		dto.setB_num( Integer.parseInt(req.getParameter("b_num")));
		dto.setM_num( m_num );
		dto.setTitle( req.getParameter("b_title") );
		dto.setSubtitle( req.getParameter("b_subtitle") );
		dto.setAuthor(req.getParameter("b_author") );
		dto.setPrice( Integer.parseInt(req.getParameter("b_price")));
		dto.setQuan( Integer.parseInt(req.getParameter("b_quan")));
		dto.setReg_date(new Timestamp( System.currentTimeMillis()));
		dto.setKind( req.getParameter("b_kind"));
		dto.setState("SALEABLE");
		
		/*System.out.println( "  -> 입력받은 값들을 확인 \n "+ dto.toString() );*/
		
		BookDAO dao = new BookDAOIpml().getInstance();
		int cnt = dao.book_insert(dto);
		
		System.out.println("  -> cnt값 : " + cnt);
		req.setAttribute("cnt", cnt);
		
		return "/_Store/View/04_Book/04_Book_Write_Pro.jsp";

	}

}
