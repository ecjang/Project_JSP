package mvc.board.handler;

import java.io.IOException;
import java.net.InetAddress;
import java.sql.Timestamp;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.board.dao.BoardDAO;
import mvc.board.dao.BoardDAOIpml;
import mvc.board.dto.BoardDTO;

public class Write_02_Pro_Handler implements Command_Handler {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		System.out.println("  -> Write_02_Pro_Handler���� execute����  : ���� ");
		
		// ������ ���ϱ�
		InetAddress local = InetAddress.getLocalHost();
		String ip = local.getHostAddress();
		
		BoardDTO dto = new BoardDTO();
		
		dto.setNum( Integer.parseInt( req.getParameter("num")) );
		dto.setWriter( req.getParameter("wr") );
		dto.setPasswd( req.getParameter("ps") );
		dto.setSubject( req.getParameter("sub") );
		dto.setContent( req.getParameter("con") );
		dto.setRef( Integer.parseInt( req.getParameter("ref")) );
		dto.setRef_step( Integer.parseInt( req.getParameter("ref_step")) );
		dto.setRef_level( Integer.parseInt( req.getParameter("ref_level")) );
		dto.setReg_date( new Timestamp(System.currentTimeMillis()) );
		dto.setIp(ip);
		/*
		System.out.println( "  -> �Է¹��� ������ Ȯ�� \n "+ dto.toString() );
		*/
		
		
		System.out.println("  -> insert()ȣ�� ");
		BoardDAO dao = new BoardDAOIpml().getInstance();
		int cnt = dao.insert(dto);
		
		System.out.println("  -> cnt�� : " + cnt);
		req.setAttribute("cnt", cnt);
		
		return "/board/view/write_02_Pro.jsp";
	}

}
