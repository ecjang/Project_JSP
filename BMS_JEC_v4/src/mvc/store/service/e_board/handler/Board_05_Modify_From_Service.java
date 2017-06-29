package mvc.store.service.e_board.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.service.Command_Handler;

public class Board_05_Modify_From_Service implements Command_Handler {

	@Override
	public String service(HttpServletRequest req, HttpServletResponse res ) {

		System.out.println("  -> Board_05_Modify_From_Service");
		
		int no = Integer.parseInt( req.getParameter("no") );
		int Mnum = Integer.parseInt( req.getParameter("Mnum") );
		int pageNum = Integer.parseInt( req.getParameter("pageNum") );
		String kind = req.getParameter("kind");
		
		req.setAttribute("no", no);
		req.setAttribute("Mnum", Mnum);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("kind", kind);
		
		// 관리자 일 경우 바로 이동
		if( (Integer)req.getSession().getAttribute("memkind") == 1 ){
			return "/_Store/View/05_Board/06_Board_Modify_View.jsp"; 
		}
				
		return "/_Store/View/05_Board/05_Board_Modify_From.jsp"; 
	}


}
