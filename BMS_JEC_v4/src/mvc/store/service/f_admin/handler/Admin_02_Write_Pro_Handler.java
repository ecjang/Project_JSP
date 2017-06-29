package mvc.store.service.f_admin.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Admin_02_Write_Pro_Handler implements Command_Handler {

	@Override
	public String supervise(HttpServletRequest req, HttpServletResponse res) {
		
		req.setAttribute("amincnt", 1);
		
		return "/book_write.do"; 
	}

}
