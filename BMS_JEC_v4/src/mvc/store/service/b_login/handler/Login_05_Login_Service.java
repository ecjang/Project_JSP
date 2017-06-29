package mvc.store.service.b_login.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.MemberDAO;
import mvc.store.dao.MemberDAOImpl;
import mvc.store.service.Command_Handler;

public class Login_05_Login_Service implements Command_Handler  {

	@Override
	public String service(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("  -> ����Ȯ�� : Login_Pro_Handler");
		
		// ���� b_num ���� ������ �������� �̵�.
		String b_num = (String) req.getAttribute("b_num");
		if( b_num != null ){
			return "/_Store/Main.jsp";
		}
		
		
		String strid = req.getParameter("id");
		String strpwd = req.getParameter("passwd");
		
		MemberDAO dao = MemberDAOImpl.getInsatance();
		
		int memkind = dao.kind(strid);
		int m_num = dao.m_num(strid);
		
		req.getSession().setAttribute("memkind", memkind);	
		req.getSession().setAttribute("m_num", m_num);	
		
		System.out.println("  -> memkind ���ǰ� : " + memkind);
		System.out.println("  -> m_num ���ǰ� : " + m_num);
		
		
		int cnt = dao.check(strid,strpwd);
		if(cnt==1){
			req.getSession().setAttribute("memId", strid);	
			System.out.println("  -> memId ���ǰ� : " + strid);
		} 
		
		req.setAttribute("cnt", cnt);
		req.setAttribute("b_num", 0);
		
		return "/_Store/View/02_Login/01_Login_Main.jsp";
	}

}
