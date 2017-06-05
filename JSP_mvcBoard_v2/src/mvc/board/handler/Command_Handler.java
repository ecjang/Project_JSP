package mvc.board.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command_Handler {
	
	public String execute( HttpServletRequest req, HttpServletResponse res ) 
			throws  ServletException, IOException ;

}
