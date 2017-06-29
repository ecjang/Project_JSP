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
		if( email3.equals("0")){ 	// ���� �Է�
			email = email1 + "@" + email2;
		} else {					// �����Է�
			email = email1 + "@" + email3;
		}
		dto.setEmail(email);
		
		//reg_Date
		dto.setReg_date( new Timestamp(System.currentTimeMillis() ));
				
		/*
		System.out.println(" - inputProHandler���� �Է°� �׽�Ʈ");
		System.out.println(
				" - ���̵� : " + dto.getId()		+ ", ��� : " + dto.getPasswd()		+ ", �̸� : " + dto.getName()	+"\n"+
				" - �ֹ�1 : " + dto.getJumin1()	+ ", �ֹ�2 : " + dto.getJumin2()		+ ", hp : " + dto.getHp()	+"\n"+
				" - �̸��� : " + dto.getEmail()	+ ", ������ : " + dto.getReg_date()	);
		*/
		
		MemberDAO dao = MemberDAOImpl.getInsatance();
		
		int cnt = dao.mem_insert(dto);
		req.setAttribute("cnt", cnt);

		return "/_Store/View/02_Login/01_Login_Main.jsp";
	}

}
