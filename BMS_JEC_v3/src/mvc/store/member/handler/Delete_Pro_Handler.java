package mvc.store.member.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.MemberDAO;
import mvc.store.dao.MemberDAOImpl;

public class Delete_Pro_Handler implements Command_Handler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("  -> 회원정보 삭제 : Delete_Pro_Handler");
		
		String strid = (String)req.getSession().getAttribute("memId");
		String strpwd = (String)req.getParameter("passwd");
		
		MemberDAO dao = MemberDAOImpl.getInsatance();
		int selectCnt = dao.check(strid,strpwd);
		
		int deleteCnt = 0;
		if( selectCnt == 1 ){
			deleteCnt = dao.delete(strid);
		} 
		
		System.out.println("  -> selectCnt 값 : " + selectCnt );
		System.out.println("  -> deleteCnt 값 : " + deleteCnt );
		
		req.setAttribute("selectCnt", selectCnt);
		req.setAttribute("deleteCnt", deleteCnt);
		
		return "/_Store/View/Login/06_Login_Delete_Pro.jsp";
	}

}
