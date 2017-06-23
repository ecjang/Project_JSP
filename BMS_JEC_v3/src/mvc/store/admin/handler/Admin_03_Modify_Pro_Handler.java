package mvc.store.admin.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Admin_03_Modify_Pro_Handler implements Command_Handler {

	@Override
	public String supervise(HttpServletRequest req, HttpServletResponse res) {
		
		return "/book_modify_from.do"; 
	}

}
