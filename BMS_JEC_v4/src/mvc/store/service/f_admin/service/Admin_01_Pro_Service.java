package mvc.store.service.f_admin.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.service.Command_Handler;

public class Admin_01_Pro_Service implements Command_Handler {

	@Override
	public String service(HttpServletRequest req, HttpServletResponse res) {
		
		System.out.println("  -> Admin_01_Pro_Service");
		
		
		int memeberCheck = 0;
		int adminCheck = 0;
		
		// �α��� üũ
		String memId = (String) req.getSession().getAttribute("memId");
		System.out.println("  -> �α����� ȸ�� ���̵� : " + memId );
		
		if ( memId != null ){
			
			memeberCheck = 1;
			
			// ȸ���� ��� �����ڱ�ȯ���� üũ
			int memkind = (Integer) req.getSession().getAttribute("memkind");
			
			if ( memkind == 1 ){
				
				System.out.println("  -> ������ ��ȯ Ȯ��");
				adminCheck = 1;
			
			} else {
			
				System.out.println("  -> ������ ��ȯ ����");
				adminCheck = 0;
			}
		
		// ��ȸ�� ��� �α��� ��������	
		} else {	
			System.out.println("  -> ��α��� ����, �α��� �������� �̵�"  );
			memeberCheck = 0;
		
		}	
		
		
		req.setAttribute("memeberCheck", memeberCheck);
		req.setAttribute("adminCheck", adminCheck);
		
		return "/_Store/View/06_Admin/01_Admin_Main.jsp"; 
	}

	
	
	
	
}
