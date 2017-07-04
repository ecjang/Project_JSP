package mvc.store.service.e_board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.BoardDAO;
import mvc.store.dao.BoardDAOImpl;
import mvc.store.dto.BoardDTO;
import mvc.store.service.Command_Handler;



public class Board_06_Modify_View_Service implements Command_Handler {

	@Override
	public String service(HttpServletRequest req, HttpServletResponse res ) {
		
		System.out.println("  -> Board_06_Modify_View_Service");
		/*System.out.println("KIND 값 : " + kind);*/
		
		
		int n = 0;
		int no	= Integer.parseInt(req.getParameter("no"));
		int Mnum	= Integer.parseInt(req.getParameter("Mnum"));
		int pageNum	= Integer.parseInt(req.getParameter("pageNum"));
		
		String ps	= req.getParameter("passwd");
		String kind	= req.getParameter("kind");

		
		/*
		System.out.println(pageNum);
		System.out.println("Mnum - handler : " + (req.getParameter("Mnum")));
		System.out.println("Mnum - handler : " + Mnum);
		*/
		
		
		
		// 회원일 경우는 회원번호와 비밀번호 확인후 이동
		BoardDTO dto = new BoardDTO();
		BoardDAO dao = BoardDAOImpl.getInstance();
		
		int cnt = dao.pwCheck(Mnum,ps);
		req.setAttribute("cnt", cnt);
		
		req.setAttribute("no", no);
		req.setAttribute("Mnum", Mnum);
		req.setAttribute("kind", kind);
		req.setAttribute("pageNum", pageNum);
		
		
		if( cnt == 1){
			
			dto = dao.getArticles(no);
			req.setAttribute("dto", dto);
			
			n = dao.getCount("all")+1;
			req.setAttribute("n", n);
			
			return "/_Store/View/05_Board/06_Board_Modify_View.jsp"; 
			
		} else {
			req.setAttribute("cnt", cnt);
			return "/_Store/View/05_Board/07_Board_Modify_Pro.jsp"; 
		}
		
	}

}
