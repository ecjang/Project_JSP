package mvc.store.board.handler;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.BoardDAO;
import mvc.store.dao.BoardDAOIpml;
import mvc.store.dto.BoardDTO;



public class Board_Modify_02_View_Handler implements Command_Handler {

	@Override
	public String write(HttpServletRequest req, HttpServletResponse res, String kind) {
		
		System.out.println("  -> Board_Modify_02_View_Handler");
		/*System.out.println("KIND °ª : " + kind);*/
		
		
		int n = 0;
		int no	= Integer.parseInt(req.getParameter("no"));
		int Mnum	= Integer.parseInt(req.getParameter("Mnum"));
		int pageNum	= Integer.parseInt(req.getParameter("pageNum"));
		String ps	= req.getParameter("passwd");
		
		
		
		req.setAttribute("no", no);
		req.setAttribute("Mnum", Mnum);
		req.setAttribute("kind", kind);
		req.setAttribute("pageNum", pageNum);
		
		BoardDTO dto = new BoardDTO();
		BoardDAO dao = BoardDAOIpml.getInstance();
		
		int cnt = dao.pwCheck(Mnum,ps);
		req.setAttribute("cnt", cnt);
		
		if( cnt == 1){
			
			dto = dao.getArticles(no);
			req.setAttribute("dto", dto);
			
			n = dao.getCount("all")+1;
			req.setAttribute("n", n);
			
			
		} else {
			req.setAttribute("cnt", cnt);
			return "/_Store/View/Board/06_Board_Modify_Pro.jsp"; 
		}

		
		return "/_Store/View/Board/05_Board_Modify_View.jsp"; 

		
	}



}
