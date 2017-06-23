package mvc.store.admin.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Admin_04_Delete_Pro_Handler implements Command_Handler {

	@Override
	public String supervise(HttpServletRequest req, HttpServletResponse res) {
		
		return "/book_delete_pro.do"; 
	}

}
