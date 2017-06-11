package mvc.store.book.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command_Handler {
		public String execute( HttpServletRequest req , HttpServletResponse res );
		
}
