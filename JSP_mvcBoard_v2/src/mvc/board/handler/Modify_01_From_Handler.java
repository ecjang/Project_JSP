package mvc.board.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Modify_01_From_Handler implements Command_Handler {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		System.out.println("  -> modify_01_From_Handler에서 execute실행  : 서비스 ");
		
		int num = Integer.parseInt( req.getParameter("num") );
		int pageNum = Integer.parseInt( req.getParameter("pageNum") );
		
		req.setAttribute("num", num);
		req.setAttribute("pageNum", pageNum);
		
		System.out.println("  -> modify_01_Form으로 이동");
		
		return "/board/view/modify_01_Form.jsp";
	}

}
