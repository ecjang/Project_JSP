package mvc.store.service.e_board.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.BoardDAO;
import mvc.store.dao.BoardDAOImpl;
import mvc.store.dto.BoardDTO;
import mvc.store.service.Command_Handler;

public class Board_07_Modify_Pro_Service implements Command_Handler {

	@Override
	public String service(HttpServletRequest req, HttpServletResponse res ) {
		
		System.out.println("  -> Board_07_Modify_Pro_Service");
		/*System.out.println("kind °ª : " + kind);*/
		
		
		int no = Integer.parseInt( req.getParameter("no") );
		int Mnum = Integer.parseInt( req.getParameter("Mnum") );
		int pageNum = Integer.parseInt( req.getParameter("pageNum") );
		
		
		String title 	= req.getParameter("title");
		String content 	= req.getParameter("content");
		String kind		= req.getParameter("kind");
		
		BoardDTO dto = new BoardDTO();
		BoardDAO dao = BoardDAOImpl.getInstance();
		
		dto.setNO(no);
		dto.setM_NUM(Mnum);
		dto.setTITLE(title);
		dto.setCONTENT(content);
		dto.setKIND(kind);

		int cnt = dao.upadte(dto);

		req.setAttribute("cnt", cnt);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("kind", kind);
		
		return "/_Store/View/05_Board/07_Board_Modify_Pro.jsp"; 
	}

	
	

}
