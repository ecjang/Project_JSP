package mvc.board.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.board.dao.BoardDAO;
import mvc.board.dao.BoardDAOIpml;
import mvc.board.dto.BoardDTO;

public class Write_01_From_Handler implements Command_Handler {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		System.out.println("  -> Write_01_From_Handler에서 execute실행  : 서비스 ");

		// 새글일 경우
		int num=0, pageNum=0 ;
		int ref=0 , ref_step=0 , ref_level=0;
		
		// 답글 일 경우
		if ( req.getParameter("num")!= null ){
			num = Integer.parseInt( req.getParameter("num") );
			pageNum = Integer.parseInt( req.getParameter("pageNum") );
			ref = Integer.parseInt( req.getParameter("ref"));
			ref_step = Integer.parseInt( req.getParameter("ref_step"));
			ref_level = Integer.parseInt( req.getParameter("ref_level"));
			System.out.println("  -> num값 : " + num);
		}
		
		BoardDAO dao = new BoardDAOIpml().getInstance();
		int n = dao.getCount() + 1;
		System.out.println("  -> 임시 번호 생성 : " + n);
		req.setAttribute("n", n);
		
		req.setAttribute("num", num);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("ref", ref);
		req.setAttribute("ref_step", ref_step);
		req.setAttribute("ref_level", ref_level);

		return "/board/view/write_01_From.jsp";
	}

}
