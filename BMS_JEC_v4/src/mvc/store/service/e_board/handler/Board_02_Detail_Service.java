package mvc.store.service.e_board.handler;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.BoardDAO;
import mvc.store.dao.BoardDAOIpml;
import mvc.store.dto.BoardDTO;
import mvc.store.service.Command_Handler;

public class Board_02_Detail_Service implements Command_Handler {

	@Override
	public String service(HttpServletRequest req, HttpServletResponse res) {
		
		System.out.println("  -> Board_02_Detail_Service ");
		
		
		int no = 0;
		int pageNum = 0;
		int number = 0;
		String kind = null;
		
		
		if( req.getParameter("no") != null ){
			
			no = Integer.parseInt( req.getParameter("no") );
			pageNum = Integer.parseInt( req.getParameter("pageNum") );
			kind = (String) req.getAttribute("kind");
			
			if ( req.getParameter("kind")!=null ){
				kind = req.getParameter("kind");
			}
			
		}
		
		
		/*
		System.out.println("  -> kind : " + kind);
		System.out.println("  -> no : " + no);
		System.out.println("  -> number : " + number);
		System.out.println("  -> pageNum : " + pageNum);
		*/
		
		BoardDAO dao = new BoardDAOIpml().getInstance();
		BoardDTO dto = new BoardDTO();
		
		dto = dao.getArticles(no);
		
		
		// 아이피 체크 후 조회수 증가
		try {
			String ip = InetAddress.getLocalHost().getHostAddress();
			System.out.println("   : 접속한 ip주소 : " + ip);
		
			if( !dto.getIP().equals(ip) ){
				System.out.println("  -> 작성자 ip주소와 다름 : 조회수 증가 ");
				dao.addview(no);
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		
		req.setAttribute("dto", dto);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("no", no);
		req.setAttribute("kind", kind);
		
		return "/_Store/View/05_Board/02_Board_Detail.jsp"; 
		
	}

}
