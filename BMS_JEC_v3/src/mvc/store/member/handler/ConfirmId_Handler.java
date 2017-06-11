package mvc.store.member.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.MemberDAO;
/*import mvc.member.dao.MemberDAOImpl;*/
import mvc.store.dao.MemberDAOImpl;

public class ConfirmId_Handler implements Command_Handler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {
		
		String id = req.getParameter("id");
		
		System.out.println("  -> 중복확인 서비스 : ConfirmId_Handler ");
		System.out.println("  -> 입력한 id : " +id);
		
		// 다형성을 적용, 싱글톤 방식으로 객체를 생성
		MemberDAO dao = MemberDAOImpl.getInsatance();	// db에서 값 조회하고 cnt 값 계산
		int cnt = dao.idCheck(id);	// 중복된 아이디가 있는지 확인 1이면 중복된 아이디가 있음, 0이면 사용 가능
		
		// 4단계. request나 session에 처리결과를 저장
		req.setAttribute("cnt", cnt);
		req.setAttribute("id", id);
		
		return "/_Store/View/Login/03_Login_Join_ConfirmId.jsp";	
	}
	

}
