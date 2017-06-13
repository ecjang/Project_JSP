package mvc.store.guest.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command_Handler {
		public String orderexecute ( 
				HttpServletRequest req , HttpServletResponse res );

		String orderexecute(HttpServletRequest req, HttpServletResponse res, 
				int b_num, int pageNum, int ordernum,
				String state);

		
}
