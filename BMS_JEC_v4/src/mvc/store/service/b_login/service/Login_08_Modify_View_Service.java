package mvc.store.service.b_login.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.MemberDAO;
import mvc.store.dao.MemberDAOImpl;
import mvc.store.dto.MemberDTO;
import mvc.store.service.Command_Handler;

public class Login_08_Modify_View_Service implements Command_Handler {

	@Override
	public String service(HttpServletRequest req, HttpServletResponse res) {
		
		System.out.println("  -> Login_06_Modify_View_Service");
		
		String strid = (String)req.getSession().getAttribute("memId");
		String strpwd = (String)req.getParameter("passwd"); 
		/*
		System.out.println("  -> 불러온 id값 : " + strid );
		System.out.println("  -> 불러온 pwd값 : " + strpwd);
		*/
		MemberDAO dao = MemberDAOImpl.getInsatance();
		
		int selectCnt = dao.check(strid, strpwd);
		
		if( selectCnt == 1 ){  
			MemberDTO dto = dao.getMember(strid); 
			req.setAttribute("dto", dto); 
		}
		
		req.setAttribute("selectCnt", selectCnt);
		
		return "/_Store/View/02_Login/08_Login_Modify_View.jsp";
	}

}
