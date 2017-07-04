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
		
		cnt = dao.check(strid,strpwd);	// 아이디 , 비빔번호 확인
		
		if(cnt==1){
			
			memkind = dao.kind(strid);	// 회원 등급 확인
			m_num = dao.m_num(strid);	// 회원 번호 확인
			
			req.getSession().setAttribute("m_num", m_num);	
			req.getSession().setAttribute("memId", strid);	
			req.getSession().setAttribute("memkind", memkind);	
			
			System.out.println("  -> m_num 세션값 : " + m_num);
			System.out.println("  -> memId 세션값 : " + strid);
			System.out.println("  -> memkind 세션값 : " + memkind);
			
		} 
				
		req.setAttribute("cnt", cnt);
		/*req.setAttribute("b_num", 0);*/
		
		
		// 로그인 완료 ------------------------------------------//
		
		
		// 만일 b_num 값을 받으면 메인으로 이동.
		if( req.getAttribute("b_num") != null ){
			return "/_Store/Main.jsp";
		}
		
		
		// 되돌아갈 주소가 있으면 해당 주소로 이동
		if( req.getParameter("back") != null ){
			String back = (String) req.getParameter("back");
			System.out.println(" back 값 : "+ back);
			return back;
		} 
		
		
		return "/_Store/View/02_Login/01_Login_Main.jsp";
	}

}
