package mvc.store.member.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Logout_Pro_Handler implements Command_Handler  {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {
		
		System.out.println("  -> ·Î±×¾Æ¿ô : Logout_Pro_Handler");
		
		req.getSession().setAttribute("memId", null);
		req.setAttribute("cnt", 2);	
		
		
		return "/_Store/View/Login/01_Login_Main.jsp";
	}

}
