package mvc.store.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command_Handler {
		public String service ( HttpServletRequest req , HttpServletResponse res );
		
}
