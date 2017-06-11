package mvc.store.member.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.MemberDAO;
import mvc.store.dao.MemberDAOImpl;
import mvc.store.dto.MemberDTO;

public class ModifyViewHandler implements Command_Handler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {
		
		System.out.println("\n - ModifyViewHandler.java 입니다.");
		System.out.println(" - 아이디와 비밀번호를 입력받아 수정작업을 합니다. ");
		
		
		// 1단계. 화면으로부터 혹은 세션으로부터 입력받은 값( 아이디, 패스워드 )을 가져온다. 수정하기 위해서
		String strid = (String)req.getSession().getAttribute("memId");
		String strpwd = (String)req.getParameter("passwd"); 
		
		System.out.println(" - 테스트 : 불러온 id값 : " + strid );
		System.out.println(" - 테스트 : 불러온 pwd값 : " + strpwd);
		
		
		// 2-1단계. dao객체 생성
		MemberDAO dao = MemberDAOImpl.getInsatance();
		
		// 2-2단계. check(아이디,비번) 호출  : 아이디와 패스워드가 맞는지 체크,  int selectCnt로 받는다.
		//
		System.out.println("\n  - ModifyViewHandler에서 check를 호출합니다.");
		int selectCnt = dao.check(strid, strpwd);
		
		// 2-3단계. getMember(아이디) 호출 : 회원정보를 가져온다. selectCnt가 1일 경우에만 실행(아이디, 비밀번호가 db에 있을 경우 )
		System.out.println("\n  - ModifyViewHandler에서 getMember를 호출합니다.");
		if( selectCnt == 1 ){  
			MemberDTO dto = dao.getMember(strid); 
			req.setAttribute("dto", dto); 
		}
		
		// 3단계. request나 session에 처리결과를 저장
		req.setAttribute("selectCnt", selectCnt);
		
		
		
		return "/member/modifyView.jsp";
	}

}
