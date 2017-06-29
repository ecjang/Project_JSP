package mvc.store.service.e_board.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.BoardDAO;
import mvc.store.dao.BoardDAOIpml;
import mvc.store.service.Command_Handler;

public class Board_03_Write_Form_Service implements Command_Handler {

	@Override
	public String service(HttpServletRequest req, HttpServletResponse res) {

		System.out.println("  -> Board_03_Write_Form_Service ");
		
		// 새글일 경우
		int no=0 ;			// 번호
		int pageNum=0 ;		// 페이지 번호
		int ref=0 ;			// 댓글 그룹핑 번호
		int ref_step=0 ;	// 댓글 들여쓰기
		int ref_level=0; 	// 댓글 레벨
		int number=0;		// 게시물 번호
		String kind = null;
		
		/*aaa
		 ?Mnum=45&no=297&pageNum=1&kind=FAQ&ref=297&ref_step=0&ref_level=0
		*/
		
		// 답글 일 경우
		if ( req.getParameter("no")!= null ){
			
			no 			= Integer.parseInt	( req.getParameter("no") 		);
			pageNum 	= Integer.parseInt	( req.getParameter("pageNum") 	);
			ref 		= Integer.parseInt	( req.getParameter("ref")		);
			ref_step 	= Integer.parseInt	( req.getParameter("ref_step")	);
			ref_level	= Integer.parseInt	( req.getParameter("ref_level")	);
			kind 		= (String) req.getAttribute("kind");
			
			
		}
		
		BoardDAO dao = new BoardDAOIpml().getInstance();
		
		
		int Mnum=0;
		int cnt=0;
		
		
		// 회원만 글 작성이 가능 
		if( Integer.parseInt(req.getParameter("Mnum")) >= 0 ){
		
			Mnum = Integer.parseInt( req.getParameter("Mnum") );
			
			// 회원 번호와 일치하는 회원이 있는지 조회
			cnt = dao.idCheck(Mnum);
		
			
			if( cnt == 1 ){

				String memId = (String) req.getSession().getAttribute("memId");
				
				/*kind = dao.kindCheck(no);*/
				
				int n = dao.getCount("all") + 1;
				System.out.println("  -> 임시 번호 생성 : " + n);
				
				req.setAttribute("n", n);
				req.setAttribute("no", no);
				req.setAttribute("Mnum", Mnum);
				req.setAttribute("pageNum", pageNum);
				req.setAttribute("ref", ref);
				req.setAttribute("ref_step", ref_step);
				req.setAttribute("ref_level", ref_level);
				req.setAttribute("kind", kind);
					
				return "/_Store/View/05_Board/03_Board_Write_From.jsp"; 
				}
			
		} 
		
		System.out.println("  -> 로그인 되어 있지 않음");
		return "/_Store/View/02_Login/01_Login_Main.jsp?cnt=3";
	}

}
