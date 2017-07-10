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
			System.out.println("    : 존재하지 않는 아이디 입니다. 회원가입 해주세요."); 
			cnt=0; 
			
		} else if ( check_id == 1 ){
			
			System.out.println("    : 아이디가 일치합니다.");
			
			/*System.out.println(" 비번 : " + member.getPw() );*/
			
			Map<String,Object> daoMap = new HashMap<>();
			daoMap.put("id", id);
			daoMap.put("pwd", pwd);
			MemberDTO member = dao.check_login(daoMap);
			
			if ( member.getPw().equals(pwd) ){ 
				
				System.out.println("    : 비밀번호가 일치합니다."); 
				cnt=1; 
					
				req.getSession().setAttribute("m_num",member.getM_num());	
				req.getSession().setAttribute("memId", member.getId());	
				req.getSession().setAttribute("memkind", member.getKind());	
				
				System.out.println("  -> m_num 세션값 : " + req.getSession().getAttribute("m_num") );
				System.out.println("  -> memId 세션값 : " + req.getSession().getAttribute("memId") );
				System.out.println("  -> memkind 세션값 : " +  req.getSession().getAttribute("memkind"));
				
			} else { 
				cnt=-1; 
				System.out.println("    : 비밀번호가 일치하지 않습니다.");  }
			
		}  else {
			
			System.out.println("    : 아이디가 일치하지 않습니다.");
		}
	
				
		model.addAttribute("cnt", cnt);
		
		
		// 로그인 완료 ------------------------------------------//
		
		
		String back = "";

		// 만일 b_num 값을 받으면 메인으로 이동.
		if( req.getAttribute("b_num") != null ){
			back = "/main_search";
			model.addAttribute("back", back);
		}
		
		
		// 되돌아갈 주소가 있으면 해당 주소로 이동
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
		System.out.println("  -> 임시번호의 값 : " + n ); 
		
	}
	
	
	@Override
	public void member_confirmid(Model model) throws Exception {
		
		System.out.println("  -> member_confirmid_Service ");

		Map<String,Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		
		String id = req.getParameter("id");
		
		System.out.println("  -> ConfirmId_Handler ");
		System.out.println("  -> 입력한 id : " +id);
		
		
		// 중복된 아이디가 있는지 확인 1이면 중복된 아이디가 있음, 0이면 사용 가능
		int cnt = dao.check_id(id);
		
		model.addAttribute("cnt", cnt);
		model.addAttribute("id", id);
	}

	
	@Override
	public void member_join_pro(Model model) throws Exception {
		

		System.out.println(" -> Login_Join_Pro_Service ");
		
		Map<String,Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest)map.get("req");
		
		// 되돌아갈 값이 있다면 해당 값을 받아옴
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
		if( email3.equals("0")){ 	// 직접 입력
			email = email1 + "@" + email2;
		} else {					// 선택입력
			email = email1 + "@" + email3;
		}
		dto.setEmail(email);
		dto.setReg_date( new Timestamp(System.currentTimeMillis()) );
		
		int cnt = dao.insert_member(dto);
		
		System.out.println("  -> 입력된 개수 : " + cnt );
		
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
			System.out.println("  -> deleteCnt 값 : " + deleteCnt );
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
		System.out.println("  -> 불러온 id값 : " + strid );
		System.out.println("  -> 불러온 pwd값 : " + strpwd);
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
		

		System.out.println("  -> DTO에 입력된 정보 출력 ");
		System.out.println(
			"  -> 수정할 아이디 : " + dto.getId()			+"\n"+
			"  -> 수정된 비밀번호 : " + dto.getPw() 		+"\n"+
			"  -> 수정된 이메일 : " + dto.getEmail() 		+"\n"+
			"  -> 수정된 연락처 : " + dto.getHp() 		+"\n");
		
		int cnt = dao.update_member(dto);
		
		model.addAttribute("cnt", cnt);
		
		
	}


	
		
	
	
	

	
	
	
}
