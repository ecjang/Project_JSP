package mvc.store.board.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.BoardDAO;
import mvc.store.dao.BoardDAOIpml;

public class Board_Delete_02_Pro_Handler implements Command_Handler {

	@Override
	public String write(HttpServletRequest req, HttpServletResponse res, String kind) {

		System.out.println("  -> Board_Delete_02_Pro_Handler ");
		
		int no = Integer.parseInt( req.getParameter("no") );
		int Mnum = Integer.parseInt( req.getParameter("Mnum") );
		int pageNum = Integer.parseInt( req.getParameter("pageNum") );
		String pw = req.getParameter("pw");
		
		BoardDAO dao = BoardDAOIpml.getInstance();

		int scnt = dao.pwCheck(Mnum, pw);
		
		if ( scnt==1 ){ 
			int dcnt = dao.delete(no);
			req.setAttribute("dcnt", dcnt);
		}
		
		req.setAttribute("scnt", scnt);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("kind", kind);
		
		return "/_Store/View/Board/08_Board_Delete_Pro.jsp"; 
		
	}

}
