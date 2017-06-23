package mvc.store.admin.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.MemberDAO;
import mvc.store.dao.MemberDAOImpl;

public class Admin_01_main_Pro_Handler implements Command_Handler {

	@Override
	public String supervise(HttpServletRequest req, HttpServletResponse res) {
		
		System.out.println("  -> Admin_main_01_Pro_Handler");
		
		
		return "/_Store/View/Admin/01_Admin_Main.jsp"; 
	}
	

}
