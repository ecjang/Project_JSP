package mvc.store.member.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.MemberDAO;
import mvc.store.dao.MemberDAOImpl;
import mvc.store.dto.MemberDTO;

public class Modify_Pro_Handler implements Command_Handler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {
		
		System.out.println("  -> 수정정보 등록 : Modify_Pro_Handler");
		
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
		dto.setPasswd( req.getParameter("passwd") );
		dto.setEmail( email );
		dto.setHp(hp); 
		

		System.out.println("  -> DTO에 입력된 정보 출력 ");
		System.out.println(
			"  -> 수정할 아이디 : " + dto.getId()			+"\n"+
			"  -> 수정된 비밀번호 : " + dto.getPasswd() 	+"\n"+
			"  -> 수정된 이메일 : " + dto.getEmail() 		+"\n"+
			"  -> 수정된 연락처 : " + dto.getHp() 		+"\n");
		
		MemberDAO dao = MemberDAOImpl.getInsatance();
		int cnt = dao.update(dto);
		
		req.setAttribute("cnt", cnt);
		
		return "/_Store/View/Login/09_Login_Modify_Pro.jsp";
	}

}
