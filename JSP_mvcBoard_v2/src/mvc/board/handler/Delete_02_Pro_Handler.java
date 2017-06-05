package mvc.board.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.board.dao.BoardDAO;
import mvc.board.dao.BoardDAOIpml;
import mvc.board.dto.BoardDTO;

public class Delete_02_Pro_Handler implements Command_Handler {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("  -> Delete_02_Pro_Handler에서 execute실행  : 서비스 ");
		
		int num = Integer.parseInt( req.getParameter("num") );
		int pageNum = Integer.parseInt( req.getParameter("pageNum") );
		String pw = req.getParameter("pw");
		System.out.println("  -> pw값 :" + pw);
		
		
		BoardDAO dao = BoardDAOIpml.getInstance();

		System.out.println("  -> pwCheck() 호출 ");
		int scnt = dao.pwCheck(num, pw);
		System.out.println("  -> scnt값 :" + scnt);
		
		if ( scnt==1 ){ 
			System.out.println("  -> upadte() 호출 ");
			int dcnt = dao.delete(num);
			req.setAttribute("dcnt", dcnt);
			System.out.println("  -> dcnt값 :" + dcnt);
		}
		
		req.setAttribute("scnt", scnt);
		req.setAttribute("pageNum", pageNum);
		
		return "/board/view/delete_02_Pro.jsp";
	}

}
