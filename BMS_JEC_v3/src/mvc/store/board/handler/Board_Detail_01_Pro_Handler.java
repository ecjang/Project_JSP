package mvc.store.board.handler;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.BoardDAO;
import mvc.store.dao.BoardDAOIpml;
import mvc.store.dto.BoardDTO;

public class Board_Detail_01_Pro_Handler implements Command_Handler {

	@Override
	public String write(HttpServletRequest req, HttpServletResponse res, String kind) {
		
		System.out.println("  -> Board_Detail_01_Pro_Handler ");
		
		int no = Integer.parseInt( req.getParameter("no") );
		int pageNum = Integer.parseInt( req.getParameter("pageNum") );
		int number = Integer.parseInt( req.getParameter("number") );
		
		/*
		System.out.println("받은 kind의 값  : " + kind_2);
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
		req.setAttribute("number", number);
		req.setAttribute("no", no);
		req.setAttribute("kind", kind);
		
		return "/_Store/View/Board/03_Board_Detail_Pro.jsp"; 
		
	}

}
