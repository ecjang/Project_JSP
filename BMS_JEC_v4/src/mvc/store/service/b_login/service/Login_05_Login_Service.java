package mvc.store.service.b_login.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.MemberDAO;
import mvc.store.dao.MemberDAOImpl;
import mvc.store.service.Command_Handler;

public class Login_05_Login_Service implements Command_Handler  {

	@Override
	public String service(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("  -> Login_05_Login_Service");

		int memkind = 0;
		int m_num  =0 ;
		int cnt = 0;
		
		String strid = req.getParameter("id");
		String strpwd = req.getParameter("passwd");
		
		MemberDAO dao = MemberDAOImpl.getInsatance();
		
		cnt = dao.check(strid,strpwd);	// ���̵� , �����ȣ Ȯ��
		
		if(cnt==1){
			
			memkind = dao.kind(strid);	// ȸ�� ��� Ȯ��
			m_num = dao.m_num(strid);	// ȸ�� ��ȣ Ȯ��
			
			req.getSession().setAttribute("m_num", m_num);	
			req.getSession().setAttribute("memId", strid);	
			req.getSession().setAttribute("memkind", memkind);	
			
			System.out.println("  -> m_num ���ǰ� : " + m_num);
			System.out.println("  -> memId ���ǰ� : " + strid);
			System.out.println("  -> memkind ���ǰ� : " + memkind);
			
		} 
				
		req.setAttribute("cnt", cnt);
		/*req.setAttribute("b_num", 0);*/
		
		
		// �α��� �Ϸ� ------------------------------------------//
		
		
		// ���� b_num ���� ������ �������� �̵�.
		if( req.getAttribute("b_num") != null ){
			return "/_Store/Main.jsp";
		}
		
		
		// �ǵ��ư� �ּҰ� ������ �ش� �ּҷ� �̵�
		if( req.getParameter("back") != null ){
			String back = (String) req.getParameter("back");
			System.out.println(" back �� : "+ back);
			return back;
		} 
		
		
		return "/_Store/View/02_Login/01_Login_Main.jsp";
	}

}
