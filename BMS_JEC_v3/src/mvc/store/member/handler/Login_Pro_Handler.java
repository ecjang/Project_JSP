package mvc.store.member.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.MemberDAO;
import mvc.store.dao.MemberDAOImpl;

public class Login_Pro_Handler implements Command_Handler  {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("  -> 본인확인 : Login_Pro_Handler");
		
		String strid = req.getParameter("id");
		String strpwd = req.getParameter("passwd");
		
		MemberDAO dao = MemberDAOImpl.getInsatance();
		
		int memkind = dao.kind(strid);
		req.getSession().setAttribute("memkind", memkind);	
		System.out.println("  -> memkind 세션값 : " + memkind);
		
		
		int m_num = dao.m_num(strid);
		
		req.getSession().setAttribute("m_num", m_num);	
		/*System.out.println("  -> m_num 세션값 : " + req.getSession().getAttribute("m_num") );*/
		
		
		int cnt = dao.check(strid,strpwd);
		if(cnt==1){
			req.getSession().setAttribute("memId", strid);	
		} 
		
		req.setAttribute("cnt", cnt);
		
		/*return "Index.jsp";*/
		return "/_Store/View/Login/01_Login_Main.jsp";
	}

}
