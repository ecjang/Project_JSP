package mvc.store.service.b_login.handler;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.MemberDAO;
import mvc.store.dao.MemberDAOImpl;
import mvc.store.dto.MemberDTO;
import mvc.store.service.Command_Handler;

public class Login_01_Input_Service implements Command_Handler {

	@Override
	public String service(HttpServletRequest req, HttpServletResponse res) {
		
		System.out.println("  -> Login_01_Input_Service");
		
		MemberDTO dto = new MemberDTO();
		
		dto.setId(req.getParameter("id"));
		dto.setPasswd(req.getParameter("passwd"));
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
		
		//reg_Date
		dto.setReg_date( new Timestamp(System.currentTimeMillis() ));
				
		/*
		System.out.println(" - inputProHandler에서 입력값 테스트");
		System.out.println(
				" - 아이디 : " + dto.getId()		+ ", 비번 : " + dto.getPasswd()		+ ", 이름 : " + dto.getName()	+"\n"+
				" - 주민1 : " + dto.getJumin1()	+ ", 주민2 : " + dto.getJumin2()		+ ", hp : " + dto.getHp()	+"\n"+
				" - 이메일 : " + dto.getEmail()	+ ", 가입일 : " + dto.getReg_date()	);
		*/
		
		MemberDAO dao = MemberDAOImpl.getInsatance();
		
		int cnt = dao.mem_insert(dto);
		req.setAttribute("cnt", cnt);

		return "/_Store/View/02_Login/01_Login_Main.jsp";
	}

}
