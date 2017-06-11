package mvc.store.member.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.MemberDAO;
import mvc.store.dao.MemberDAOImpl;
import mvc.store.dto.MemberDTO;

public class Modify_View_Handler implements Command_Handler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {
		
		System.out.println("  -> ����ȸ�� ��� �Է� : Modify_View_Handler");
		
		String strid = (String)req.getSession().getAttribute("memId");
		String strpwd = (String)req.getParameter("passwd"); 
		
		System.out.println("  -> �ҷ��� id�� : " + strid );
		System.out.println("  -> �ҷ��� pwd�� : " + strpwd);
		
		MemberDAO dao = MemberDAOImpl.getInsatance();
		
		int selectCnt = dao.check(strid, strpwd);
		
		if( selectCnt == 1 ){  
			MemberDTO dto = dao.getMember(strid); 
			req.setAttribute("dto", dto); 
		}
		
		req.setAttribute("selectCnt", selectCnt);
		
		return "/_Store/View/Login/08_Login_Modify_View.jsp";
	}

}
