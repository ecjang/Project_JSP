package mvc.board.handler;

import java.io.IOException;
import java.net.InetAddress;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.board.dao.BoardDAO;
import mvc.board.dao.BoardDAOIpml;
import mvc.board.dto.BoardDTO;

public class Content_01_Form_Handler implements Command_Handler{
	
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		System.out.println("  -> ContentForm_01_Handler에서 execute실행  : 서비스 ");
		
		int num = Integer.parseInt( req.getParameter("num") );
		int pageNum = Integer.parseInt( req.getParameter("pageNum") );
		int number = Integer.parseInt( req.getParameter("number") );
		
		System.out.println("  -> num : " + num);
		System.out.println("  -> number : " + number);
		System.out.println("  -> pageNum : " + pageNum);
		
		
		
		BoardDAO dao = new BoardDAOIpml().getInstance();
		BoardDTO dto = new BoardDTO();
		System.out.println("  -> getAritcles() 호출 ");
		dto = dao.getArticles(num);
		
		// 아이피 체크 후 조회수 증가
		InetAddress ipck = InetAddress.getLocalHost();
		String ip = ipck.getHostAddress();
		System.out.println("   : 접속한 ip주소 : " + ip);
		
		if( !dto.getIp().equals(ip) ){
			System.out.println("  -> 작성자 ip주소와 다름 : 조회수 증가 ");
			dao.addReadCnt(num);
		}
		
		req.setAttribute("dto", dto);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("number", number);
		req.setAttribute("num", num);
		
		return "/board/view/content.jsp";
	}

}
