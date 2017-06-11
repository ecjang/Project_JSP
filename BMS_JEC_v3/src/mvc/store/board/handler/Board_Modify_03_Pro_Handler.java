package mvc.store.board.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.BoardDAO;
import mvc.store.dao.BoardDAOIpml;
import mvc.store.dto.BoardDTO;

public class Board_Modify_03_Pro_Handler implements Command_Handler {

	@Override
	public String write(HttpServletRequest req, HttpServletResponse res, String kind) {
		
		System.out.println("  -> Board_Modify_03_Pro_Handler");
		/*System.out.println("kind °ª : " + kind);*/
		
		
		int no = Integer.parseInt( req.getParameter("no") );
		int Mnum = Integer.parseInt( req.getParameter("Mnum") );
		int pageNum = Integer.parseInt( req.getParameter("pageNum") );
		
		
		String title = req.getParameter("title");
		String content =  req.getParameter("content");
		
		BoardDTO dto = new BoardDTO();
		BoardDAO dao = BoardDAOIpml.getInstance();
		
		dto.setNO(no);
		dto.setM_NUM(Mnum);
		dto.setTITLE(title);
		dto.setCONTENT(content);
		dto.setKIND(kind);

		int cnt = dao.upadte(dto);

		req.setAttribute("cnt", cnt);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("kind", kind);
		
		return "/_Store/View/Board/06_Board_Modify_Pro.jsp"; 
	}

	
	

}
