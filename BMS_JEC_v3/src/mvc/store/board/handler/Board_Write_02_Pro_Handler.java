package mvc.store.board.handler;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.BoardDAO;
import mvc.store.dao.BoardDAOIpml;
import mvc.store.dto.BoardDTO;

public class Board_Write_02_Pro_Handler implements Command_Handler {

	@Override
	public String write(HttpServletRequest req, HttpServletResponse res, String kind) {
		
		
		System.out.println("  -> Board_Write_02_Pro_Handler ");
		
		// 아이피 구하기
		 
		try {
			InetAddress local = InetAddress.getLocalHost();
			String ip = local.getHostAddress();
			
			BoardDTO dto = new BoardDTO();
			
			dto.setNO( Integer.parseInt( 	req.getParameter("no")) 	);
			dto.setM_NUM( Integer.parseInt( req.getParameter("Mnum")) 	);
			dto.setTITLE( req.getParameter("title") );
			dto.setWRITER( req.getParameter("writer") );
			dto.setCONTENT( req.getParameter("content") );
			dto.setVIEWS(0);
			dto.setREF( Integer.parseInt( req.getParameter("ref")) );
			dto.setREF_STEP( Integer.parseInt( req.getParameter("ref_step")) );
			dto.setREF_LEVEL( Integer.parseInt( req.getParameter("ref_level")) );
			dto.setREG_DATE( new Timestamp(System.currentTimeMillis()) );
			dto.setKIND( kind );
			dto.setIP(ip);
			
			/*
			System.out.println( "  -> 입력받은 값들을 확인 \n "+ dto.toString() );
			*/
			
			BoardDAO dao = new BoardDAOIpml().getInstance();
			
			int cnt = dao.insert(dto);

			
			int pageNum = Integer.parseInt(req.getParameter("pageNum"));  
			req.setAttribute("pageNum", pageNum );
			req.setAttribute("cnt", cnt);
			req.setAttribute("kind", kind);
			
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		
		return "/_Store/View/Board/10_Board_Write_Pro.jsp"; 
	}

}
