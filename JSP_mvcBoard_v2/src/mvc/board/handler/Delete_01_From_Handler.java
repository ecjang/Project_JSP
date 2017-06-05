package mvc.board.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Delete_01_From_Handler implements Command_Handler {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		System.out.println("  -> Delete_01_From_Handler에서 execute실행  : 서비스 ");
		
		int num = Integer.parseInt( req.getParameter("num") );
		int pageNum = Integer.parseInt( req.getParameter("pageNum") );
		
		req.setAttribute("num", num);
		req.setAttribute("pageNum", pageNum);
		
		return "/board/view/delete_01_From.jsp";
	}

}
