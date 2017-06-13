package mvc.store.guest.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Guest_Cart_01_Pro_Handler implements Command_Handler {

	@Override
	public String orderexecute(HttpServletRequest req, HttpServletResponse res, int b_num, int pageNum, int ordernum,
			String state) {
		
		System.out.println("  -> Guest_Cart_01_Pro_Handler");
		
		
		/*
		System.out.println(b_num);
		System.out.println(pageNum);
		System.out.println(ordernum);
		System.out.println(state);
		*/
		
		
		
		
		int idCheck = 0;
		int headCheck = 0;
		
		if(req.getSession().getAttribute("memId")!=null){
			
			idCheck = 1;
			String memId = (String) req.getSession().getAttribute("memId");
			System.out.println(memId);
			
		}else{
			
			idCheck = 0 ; 
			headCheck = 1;
			req.setAttribute("idCheck", idCheck);
			return "/_Store/View/Guest/01_Guest_Cart_01_Pro.jsp";
			
		}
		
		
		return "/_Store/View/Guest/01_Guest_Cart_01_Pro.jsp";
	}

}
