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
		
		// 로그인 체크
		String memId = (String) req.getSession().getAttribute("memId");
		System.out.println("  -> 로그인한 회원 아이디 : " + memId );
		
		if ( memId != null ){
			
			memeberCheck = 1;
			
			// 회원일 경우 관리자권환인지 체크
			int memkind = (Integer) req.getSession().getAttribute("memkind");
			
			if ( memkind == 1 ){
				
				System.out.println("  -> 관리지 권환 확인");
				adminCheck = 1;
			
			} else {
			
				System.out.println("  -> 관리자 권환 없음");
				adminCheck = 0;
			}
		
		// 비회원 경우 로그인 페이지로	
		} else {	
			System.out.println("  -> 비로그인 상태, 로그인 페이지로 이동"  );
			memeberCheck = 0;
		
		}	
		
		
		req.setAttribute("memeberCheck", memeberCheck);
		req.setAttribute("adminCheck", adminCheck);
		
		return "/_Store/View/06_Admin/01_Admin_Main.jsp"; 
	}

	
	
	
	
}
