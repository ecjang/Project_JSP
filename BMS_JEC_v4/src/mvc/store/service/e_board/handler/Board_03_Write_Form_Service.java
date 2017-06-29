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
		
		// ������ ���
		int no=0 ;			// ��ȣ
		int pageNum=0 ;		// ������ ��ȣ
		int ref=0 ;			// ��� �׷��� ��ȣ
		int ref_step=0 ;	// ��� �鿩����
		int ref_level=0; 	// ��� ����
		int number=0;		// �Խù� ��ȣ
		String kind = null;
		
		/*aaa
		 ?Mnum=45&no=297&pageNum=1&kind=FAQ&ref=297&ref_step=0&ref_level=0
		*/
		
		// ��� �� ���
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
		
		
		// ȸ���� �� �ۼ��� ���� 
		if( Integer.parseInt(req.getParameter("Mnum")) >= 0 ){
		
			Mnum = Integer.parseInt( req.getParameter("Mnum") );
			
			// ȸ�� ��ȣ�� ��ġ�ϴ� ȸ���� �ִ��� ��ȸ
			cnt = dao.idCheck(Mnum);
		
			
			if( cnt == 1 ){

				String memId = (String) req.getSession().getAttribute("memId");
				
				/*kind = dao.kindCheck(no);*/
				
				int n = dao.getCount("all") + 1;
				System.out.println("  -> �ӽ� ��ȣ ���� : " + n);
				
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
		
		System.out.println("  -> �α��� �Ǿ� ���� ����");
		return "/_Store/View/02_Login/01_Login_Main.jsp?cnt=3";
	}

}
