package mvc.store.main.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command_Handler {
		public String mainexecute ( HttpServletRequest req , HttpServletResponse res ,String str );
		
}
