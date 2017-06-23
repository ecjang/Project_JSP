package mvc.store.admin.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command_Handler {
		public String supervise ( HttpServletRequest req , HttpServletResponse res);
		
}
