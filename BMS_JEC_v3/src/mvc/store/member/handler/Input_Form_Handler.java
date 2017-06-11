package mvc.store.member.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.MemberDAO;
import mvc.store.dao.MemberDAOImpl;

public class Input_Form_Handler implements Command_Handler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("  -> 임시번호 불러오기 :Input_Form_Handler");
		MemberDAO dao = new MemberDAOImpl().getInsatance();
		int n = dao.num()+1;
		req.setAttribute("n", n);
		System.out.println("    : 임시번호의 값 : " + n ); 
		
		return "/_Store/View/Login/02_Login_Join_Form.jsp"; 
	}	
}	
