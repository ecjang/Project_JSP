package mvc.store.main.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.BoardDAO;
import mvc.store.dao.BoardDAOIpml;
import mvc.store.dto.BoardDTO;

public class Main_Serch_01_Pro_Handler implements Command_Handler   {

	@Override
	public String mainexecute(HttpServletRequest req, HttpServletResponse res, String kind) {
		
		// ���� Ž��
		BoardDAO dao = BoardDAOIpml.getInstance();
		
		
				
		
		// ���� 10��
		
		// ���̺� �߰�
		
		// ������ �ҷ�����
		
		// 
		
		
		return null;
	}

}
