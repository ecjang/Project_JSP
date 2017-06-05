package mvc.board.handler;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.board.dao.BoardDAO;
import mvc.board.dao.BoardDAOIpml;
import mvc.board.dto.BoardDTO;

public class Modify_02_View_Handler implements Command_Handler {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("  -> modify_02_View_Handler에서 execute실행  : 서비스 ");
		
		int n = 0;
		int num		= Integer.parseInt(req.getParameter("num"));
		int pageNum	= Integer.parseInt(req.getParameter("pageNum"));
		String ps	= req.getParameter("passwd");
		
		BoardDTO dto = new BoardDTO();
		BoardDAO dao = BoardDAOIpml.getInstance();
		
		System.out.println( "  -> pwCheck() 호출" );
		int cnt = dao.pwCheck(num,ps);
		
		if( cnt == 1){
			System.out.println( "  -> getArticles() 호출" );
			dto = dao.getArticles(num);
			req.setAttribute("dto", dto);
			
			System.out.println( "  -> 임시번호 설정" );
			n = dao.getCount()+1;
			req.setAttribute("n", n);
			
		} else {
			req.setAttribute("cnt", cnt);
			return "/board/view/modify_03_Pro.jsp";
		}
		
		req.setAttribute("num", num);
		req.setAttribute("pageNum", pageNum);
		
		return "/board/view/modify_02_View.jsp";
	}

}
