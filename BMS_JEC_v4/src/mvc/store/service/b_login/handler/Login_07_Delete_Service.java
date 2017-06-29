package mvc.store.service.b_login.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.MemberDAO;
import mvc.store.dao.MemberDAOImpl;
import mvc.store.service.Command_Handler;

public class Login_07_Delete_Service implements Command_Handler {

	@Override
	public String service(HttpServletRequest req, HttpServletResponse res) {
		
		System.out.println("  -> Login_07_Delete_Service");
		
		String strid = (String)req.getSession().getAttribute("memId");
		String strpwd = (String)req.getParameter("passwd");
		
		MemberDAO dao = MemberDAOImpl.getInsatance();
		int selectCnt = dao.check(strid,strpwd);
		
		int deleteCnt = 0;
		if( selectCnt == 1 ){
			deleteCnt = dao.delete(strid);
		} 
		
		System.out.println("  -> selectCnt °ª : " + selectCnt );
		System.out.println("  -> deleteCnt °ª : " + deleteCnt );
		
		req.setAttribute("selectCnt", selectCnt);
		req.setAttribute("deleteCnt", deleteCnt);
		
		return "/_Store/View/02_Login/06_Login_Delete_Pro.jsp";
	}

}
