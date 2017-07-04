package mvc.store.service.b_login.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.MemberDAO;
/*import mvc.member.dao.MemberDAOImpl;*/
import mvc.store.dao.MemberDAOImpl;
import mvc.store.service.Command_Handler;

public class Login_03_ConfirmId_Service implements Command_Handler {

	@Override
	public String service(HttpServletRequest req, HttpServletResponse res) {
		
		String id = req.getParameter("id");
		
		System.out.println("  -> ConfirmId_Handler ");
		System.out.println("  -> �Է��� id : " +id);
		
		// �������� ����, �̱��� ������� ��ü�� ����
		MemberDAO dao = MemberDAOImpl.getInsatance();	// db���� �� ��ȸ�ϰ� cnt �� ���
		int cnt = dao.idCheck(id);	// �ߺ��� ���̵� �ִ��� Ȯ�� 1�̸� �ߺ��� ���̵� ����, 0�̸� ��� ����
		
		// 4�ܰ�. request�� session�� ó������� ����
		/*
		req.setAttribute("cnt", cnt);
		req.setAttribute("id", id);
		*/
		return "/_Store/View/02_Login/03_Login_Join_ConfirmId.jsp?id="+id+"&cnt="+cnt;	
	}
	

}
