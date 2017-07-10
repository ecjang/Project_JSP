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
		
		// �α��� üũ
		
		
		if ( (String) req.getSession().getAttribute("memId") != null ){
			
			String memId = (String) req.getSession().getAttribute("memId");
			System.out.println("  -> �α����� ȸ�� ���̵� : " + memId );
			
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
		
		model.addAttribute("memeberCheck", memeberCheck);
		model.addAttribute("adminCheck", adminCheck);
		
	}

}
