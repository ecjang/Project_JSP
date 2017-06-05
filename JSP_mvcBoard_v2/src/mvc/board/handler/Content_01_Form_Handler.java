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
		
		System.out.println("  -> ContentForm_01_Handler���� execute����  : ���� ");
		
		int num = Integer.parseInt( req.getParameter("num") );
		int pageNum = Integer.parseInt( req.getParameter("pageNum") );
		int number = Integer.parseInt( req.getParameter("number") );
		
		System.out.println("  -> num : " + num);
		System.out.println("  -> number : " + number);
		System.out.println("  -> pageNum : " + pageNum);
		
		
		
		BoardDAO dao = new BoardDAOIpml().getInstance();
		BoardDTO dto = new BoardDTO();
		System.out.println("  -> getAritcles() ȣ�� ");
		dto = dao.getArticles(num);
		
		// ������ üũ �� ��ȸ�� ����
		InetAddress ipck = InetAddress.getLocalHost();
		String ip = ipck.getHostAddress();
		System.out.println("   : ������ ip�ּ� : " + ip);
		
		if( !dto.getIp().equals(ip) ){
			System.out.println("  -> �ۼ��� ip�ּҿ� �ٸ� : ��ȸ�� ���� ");
			dao.addReadCnt(num);
		}
		
		req.setAttribute("dto", dto);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("number", number);
		req.setAttribute("num", num);
		
		return "/board/view/content.jsp";
	}

}
