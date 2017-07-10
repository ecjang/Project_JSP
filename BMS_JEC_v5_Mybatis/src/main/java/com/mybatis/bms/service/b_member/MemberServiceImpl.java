package com.mybatis.bms.service.b_member;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.mybatis.bms.dao.b_member.MemberDAO;
import com.mybatis.bms.dto.MemberDTO;

@Service
public class MemberServiceImpl  implements MemberService {
	
	@Inject
	MemberDAO dao;
	
	
	@Override
	public void login_login(Model model) throws Exception {
		
		System.out.println("  -> Login_Login_Service");

		Map<String,Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		
		
		int cnt = 0;
		
		String id = req.getParameter("id");
		String pwd = req.getParameter("passwd");
		
		System.out.println("  -> id : " + id + " / pwd : " + pwd );
		
		
		int check_id = dao.check_id(id);
		
	
		
		/*System.out.println(member.toString());*/
		
		if( check_id == 0 ) { 
			System.out.println("    : �������� �ʴ� ���̵� �Դϴ�. ȸ������ ���ּ���."); 
			cnt=0; 
			
		} else if ( check_id == 1 ){
			
			System.out.println("    : ���̵� ��ġ�մϴ�.");
			
			/*System.out.println(" ��� : " + member.getPw() );*/
			
			Map<String,Object> daoMap = new HashMap<>();
			daoMap.put("id", id);
			daoMap.put("pwd", pwd);
			MemberDTO member = dao.check_login(daoMap);
			
			if ( member.getPw().equals(pwd) ){ 
				
				System.out.println("    : ��й�ȣ�� ��ġ�մϴ�."); 
				cnt=1; 
					
				req.getSession().setAttribute("m_num",member.getM_num());	
				req.getSession().setAttribute("memId", member.getId());	
				req.getSession().setAttribute("memkind", member.getKind());	
				
				System.out.println("  -> m_num ���ǰ� : " + req.getSession().getAttribute("m_num") );
				System.out.println("  -> memId ���ǰ� : " + req.getSession().getAttribute("memId") );
				System.out.println("  -> memkind ���ǰ� : " +  req.getSession().getAttribute("memkind"));
				
			} else { 
				cnt=-1; 
				System.out.println("    : ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");  }
			
		}  else {
			
			System.out.println("    : ���̵� ��ġ���� �ʽ��ϴ�.");
		}
	
				
		model.addAttribute("cnt", cnt);
		
		
		// �α��� �Ϸ� ------------------------------------------//
		
		
		String back = "";

		// ���� b_num ���� ������ �������� �̵�.
		if( req.getAttribute("b_num") != null ){
			back = "/main_search";
			model.addAttribute("back", back);
		}
		
		
		// �ǵ��ư� �ּҰ� ������ �ش� �ּҷ� �̵�
		if( req.getParameter("back") != null ){
			back = (String) req.getParameter("back");
			model.addAttribute("back", back);
		} 
		
	}

	
	@Override
	public void login_logout(Model model) throws Exception {
		System.out.println("  -> member_logout_Service ");
		
		Map<String,Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");

		req.getSession().setAttribute("m_num", null);
		req.getSession().setAttribute("memId", null);
		req.getSession().setAttribute("memkind", null);
		
		model.addAttribute("cnt", 2);	

		
	}

	
	@Override
	public void member_joinform(Model model) throws Exception {
		
		System.out.println("  -> member_joinform_Service ");

		int n = dao.cnt_member()+1;
		model.addAttribute("n", n);
		System.out.println("  -> �ӽù�ȣ�� �� : " + n ); 
		
	}
	
	
	@Override
	public void member_confirmid(Model model) throws Exception {
		
		System.out.println("  -> member_confirmid_Service ");

		Map<String,Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		
		String id = req.getParameter("id");
		
		System.out.println("  -> ConfirmId_Handler ");
		System.out.println("  -> �Է��� id : " +id);
		
		
		// �ߺ��� ���̵� �ִ��� Ȯ�� 1�̸� �ߺ��� ���̵� ����, 0�̸� ��� ����
		int cnt = dao.check_id(id);
		
		model.addAttribute("cnt", cnt);
		model.addAttribute("id", id);
	}

	
	@Override
	public void member_join_pro(Model model) throws Exception {
		

		System.out.println(" -> Login_Join_Pro_Service ");
		
		Map<String,Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest)map.get("req");
		
		// �ǵ��ư� ���� �ִٸ� �ش� ���� �޾ƿ�
		if( req.getParameter("back") != null){
			req.setAttribute("back", req.getParameter("back") );
		}
		
		
		MemberDTO dto = new MemberDTO();
		
		dto.setId(req.getParameter("id"));
		dto.setPw(req.getParameter("passwd"));
		dto.setName(req.getParameter("name"));
		dto.setJumin1(req.getParameter("jumin1"));
		dto.setJumin2(req.getParameter("jumin2"));
		
		String hp1 = req.getParameter("hp1");
		String hp2 = req.getParameter("hp2");
		String hp3 = req.getParameter("hp3");
		
		if( !hp1.equals("") && !hp2.equals("") && !hp3.equals("")){
			String hp = hp1 +"-"+ hp2 +"-"+ hp3;
			dto.setHp(hp);
		} 
		
		String email1 = req.getParameter("email1");
		String email2 = req.getParameter("email2");
		String email3 = req.getParameter("email3");
		String email;
		if( email3.equals("0")){ 	// ���� �Է�
			email = email1 + "@" + email2;
		} else {					// �����Է�
			email = email1 + "@" + email3;
		}
		dto.setEmail(email);
		dto.setReg_date( new Timestamp(System.currentTimeMillis()) );
		
		int cnt = dao.insert_member(dto);
		
		System.out.println("  -> �Էµ� ���� : " + cnt );
		
		model.addAttribute("cnt", cnt);
		
		
	}

	
	@Override
	public void member_delete( Model model ) throws Exception {

		System.out.println("  -> Login_Delete_Service");
		
		Map<String,Object> Map = model.asMap();
		HttpServletRequest req = (HttpServletRequest)Map.get("req");
		
		String id = (String)req.getSession().getAttribute("memId");
		String pwd = (String)req.getParameter("passwd");
		
		Map<String,Object> daoMap = new HashMap<>();
		daoMap.put("id", id);
		daoMap.put("pwd", pwd);
		MemberDTO dto = dao.check_login(daoMap);
		
		int selectCnt = 0;
		int deleteCnt = 0;
		
		if ( dto.getId() != null ){
			selectCnt = 1;
			deleteCnt = dao.delete_member(id);
			System.out.println("  -> deleteCnt �� : " + deleteCnt );
		}
		
		model.addAttribute("selectCnt", selectCnt);
		model.addAttribute("deleteCnt", deleteCnt);
		
	}


	@Override
	public void member_modify_form(Model model) throws Exception {
		
		System.out.println("  -> member_modify_form_Service");
		
		Map<String,Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
				
		
		String id = (String)req.getSession().getAttribute("memId");
		String pwd = (String)req.getParameter("passwd"); 
		
		/*
		System.out.println("  -> �ҷ��� id�� : " + strid );
		System.out.println("  -> �ҷ��� pwd�� : " + strpwd);
		*/
		
		
		
		int selectCnt = dao.get_member(id);
		
		if( selectCnt == 1 ){  
			
			Map <String, Object> daoMap = new HashMap<>();
			daoMap.put("id", id);
			daoMap.put("pwd",pwd);
			MemberDTO dto = dao.check_login(daoMap);
			
			model.addAttribute("dto", dto); 
		}
		
		model.addAttribute("selectCnt", selectCnt); 
		
		
	}


	@Override
	public void member_modify_pro(Model model) throws Exception {
		
		System.out.println("  -> member_modify_pro_Service");
		
		Map<String,Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
				
		MemberDTO dto = new MemberDTO();
		
		String id = (String)req.getSession().getAttribute("memId");
		String email="",hp="";
		String e1 = req.getParameter("email1");
		String e2 = req.getParameter("email2");
		String h1 = req.getParameter("hp1");
		String h2 = req.getParameter("hp2");
		String h3 = req.getParameter("hp3");

		if( !h1.equals("") && !h2.equals("") && !h3.equals("") ){
			hp = h1 +"-"+ h2 +"-"+ h3; }
		if( !e1.equals("") && !e2.equals("") ){
			email = e1 +"@"+ e2; }
		
		dto.setId(id);
		dto.setPw( req.getParameter("passwd") );
		dto.setEmail( email );
		dto.setHp(hp); 
		

		System.out.println("  -> DTO�� �Էµ� ���� ��� ");
		System.out.println(
			"  -> ������ ���̵� : " + dto.getId()			+"\n"+
			"  -> ������ ��й�ȣ : " + dto.getPw() 		+"\n"+
			"  -> ������ �̸��� : " + dto.getEmail() 		+"\n"+
			"  -> ������ ����ó : " + dto.getHp() 		+"\n");
		
		int cnt = dao.update_member(dto);
		
		model.addAttribute("cnt", cnt);
		
		
	}


	
		
	
	
	

	
	
	
}
