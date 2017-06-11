package mvc.store.board.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Board_Delete_01_From_Handler implements Command_Handler {

	@Override
	public String write(HttpServletRequest req, HttpServletResponse res, String kind) {

		System.out.println("  -> Board_Delete_01_From_Handler");
		
		int no = Integer.parseInt( req.getParameter("no") );
		int Mnum = Integer.parseInt( req.getParameter("Mnum") );
		int pageNum = Integer.parseInt( req.getParameter("pageNum") );
		
		req.setAttribute("no", no);
		req.setAttribute("Mnum", Mnum);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("kind", kind);
		
		return "/_Store/View/Board/07_Board_Delete_Form.jsp"; 
		
	}

}
