package mvc.store.service.b_login.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.service.Command_Handler;

public class Login_06_Logout_Service implements Command_Handler  {

	@Override
	public String service(HttpServletRequest req, HttpServletResponse res) {
		
		System.out.println("  -> Login_04_Logout_Service ");
		
		req.getSession().setAttribute("memId", null);
		req.setAttribute("cnt", 2);	
		
		
		return "/_Store/View/02_Login/01_Login_Main.jsp";
	}

}
