package com.mybatis.bms.service.g_admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class AdminServiceImpl implements AdminService {

	@Override
	public void admin_pro(Model model) throws Exception {
		

		System.out.println("  -> Admin_01_Pro_Service");
		
		Map<String,Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		
		int memeberCheck = 0;
		int adminCheck = 0;
		
		// 로그인 체크
		
		
		if ( (String) req.getSession().getAttribute("memId") != null ){
			
			String memId = (String) req.getSession().getAttribute("memId");
			System.out.println("  -> 로그인한 회원 아이디 : " + memId );
			
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
		
		model.addAttribute("memeberCheck", memeberCheck);
		model.addAttribute("adminCheck", adminCheck);
		
	}

}
