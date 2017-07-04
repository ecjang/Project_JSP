package mvc.store.service.e_board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.BoardDAO;
import mvc.store.dao.BoardDAOImpl;
import mvc.store.service.Command_Handler;

public class Board_09_Delete_Pro_Service implements Command_Handler {

	@Override
	public String service(HttpServletRequest req, HttpServletResponse res ) {

		System.out.println("  -> Board_Delete_02_Pro_Handler ");
		
		int no = Integer.parseInt( req.getParameter("no") );
		int Mnum = Integer.parseInt( req.getParameter("Mnum") );
		int pageNum = Integer.parseInt( req.getParameter("pageNum") );
		String pw = req.getParameter("pw");
		String kind = req.getParameter("kind");
		
		BoardDAO dao = BoardDAOImpl.getInstance();

		int scnt = dao.pwCheck(Mnum, pw);
		
		if ( scnt==1 ){ 
			int dcnt = dao.delete(no);
			req.setAttribute("dcnt", dcnt);
		}
		
		req.setAttribute("scnt", scnt);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("kind", kind);
		
		return "/_Store/View/05_Board/09_Board_Delete_Pro.jsp"; 
		
	}

}
