package mvc.store.service.b_login.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.MemberDAO;
import mvc.store.dao.MemberDAOImpl;
import mvc.store.service.Command_Handler;

public class Login_02_Join_Form_Service implements Command_Handler {

	@Override
	public String service(HttpServletRequest req, HttpServletResponse res) {
		
		System.out.println("  -> Login_02_Join_Form_Service");
		MemberDAO dao = new MemberDAOImpl().getInsatance();
		int n = dao.num()+1;
		req.setAttribute("n", n);
		System.out.println("    : 임시번호의 값 : " + n ); 
		
		return "/_Store/View/02_Login/02_Login_Join_Form.jsp"; 
	}	
}	
