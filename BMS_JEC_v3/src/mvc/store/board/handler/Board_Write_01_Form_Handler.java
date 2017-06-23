package mvc.store.board.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.BoardDAO;
import mvc.store.dao.BoardDAOIpml;

public class Board_Write_01_Form_Handler implements Command_Handler {

	@Override
	public String write(HttpServletRequest req, HttpServletResponse res, String kind) {

		System.out.println("  -> Board_Write_01_Form_Handler ");
		
		// 새글일 경우
		int no=0, pageNum=0 ;
		int ref=0 , ref_step=0 , ref_level=0;
	
		/*
		onclick="window.location='board_write_from.do"
				+ "?Mnum=${dto.getM_NUM()}"
				+ "&no=${dto.NO}"
				+ "&pageNum=${pageNum}"
				+ "&kind=${param.kind}"
				+ "&ref=${dto.REF}"
				+ "&ref_step=${dto.REF_STEP}"
				+ "&ref_level=${dto.REF_LEVEL}'">
		
		*/
		
		// 답글 일 경우
		if ( req.getParameter("no")!= null ){
			
			no 			= Integer.parseInt	( req.getParameter("no") 		);
			pageNum 	= Integer.parseInt	( req.getParameter("pageNum") 	);
			ref 		= Integer.parseInt	( req.getParameter("ref")		);
			ref_step 	= Integer.parseInt	( req.getParameter("ref_step")	);
			ref_level	 = Integer.parseInt	( req.getParameter("ref_level")	);
			
		}
		
		BoardDAO dao = new BoardDAOIpml().getInstance();
		
		
		int Mnum=0;
		int cnt=0;
		if( req.getParameter("Mnum")!=null ){
		
			Mnum = Integer.parseInt( req.getParameter("Mnum") );
			cnt = dao.idCheck(Mnum);
		
		}
		
		String memId = (String) req.getSession().getAttribute("memId");
		
		if( memId == null ){
			System.out.println("  -> 로그인 되어 있지 않음");
			
		} else {
			
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
			
			
			return "/_Store/View/Board/09_Board_Write_From.jsp"; 
		}
		
		
		return "/_Store/View/Login/01_Login_Main.jsp?cnt=3";
		
	}

}
