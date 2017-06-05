package mvc.board.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.board.dao.BoardDAO;
import mvc.board.dao.BoardDAOIpml;
import mvc.board.dto.BoardDTO;

public class Modify_03_Pro_Handler implements Command_Handler {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("  -> modify_03_Pro_Handler에서 execute실행  : 서비스 ");
		
		int num = Integer.parseInt( req.getParameter("num") );
		int pageNum = Integer.parseInt( req.getParameter("pageNum") );
		
		String subject = req.getParameter("subject");
		String content =  req.getParameter("content");
		String ps = req.getParameter("pw");
		
		BoardDTO dto = new BoardDTO();
		BoardDAO dao = BoardDAOIpml.getInstance();
		
		dto.setNum(num);
		dto.setSubject(subject);
		dto.setContent(content);
		dto.setPasswd(ps);

		System.out.println("  -> upadte() 호출 ");
		int cnt = dao.upadte(dto);

		req.setAttribute("cnt", cnt);
		req.setAttribute("pageNum", pageNum);
		
		return "/board/view/modify_03_Pro.jsp";
	}

}
