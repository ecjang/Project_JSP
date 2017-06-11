package mvc.store.board.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command_Handler {
		public String write ( HttpServletRequest req , HttpServletResponse res ,  String kind);
		
}
