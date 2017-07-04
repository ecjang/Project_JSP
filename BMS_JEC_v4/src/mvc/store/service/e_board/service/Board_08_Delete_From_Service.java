package mvc.store.service.e_board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.service.Command_Handler;

public class Board_08_Delete_From_Service implements Command_Handler {

	@Override
	public String service(HttpServletRequest req, HttpServletResponse res) {

		System.out.println("  -> Board_08_Delete_From_Service");
		
		int no = Integer.parseInt( req.getParameter("no") );
		int Mnum = Integer.parseInt( req.getParameter("Mnum") );
		int pageNum = Integer.parseInt( req.getParameter("pageNum") );
		String kind = req.getParameter("kind");
		
		req.setAttribute("no", no);
		req.setAttribute("Mnum", Mnum);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("kind", kind);
		
		return "/_Store/View/05_Board/08_Board_Delete_Form.jsp"; 
		
	}

}
