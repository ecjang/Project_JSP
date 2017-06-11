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
		
		System.out.println("  -> �ߺ�Ȯ�� ���� : ConfirmId_Handler ");
		System.out.println("  -> �Է��� id : " +id);
		
		// �������� ����, �̱��� ������� ��ü�� ����
		MemberDAO dao = MemberDAOImpl.getInsatance();	// db���� �� ��ȸ�ϰ� cnt �� ���
		int cnt = dao.idCheck(id);	// �ߺ��� ���̵� �ִ��� Ȯ�� 1�̸� �ߺ��� ���̵� ����, 0�̸� ��� ����
		
		// 4�ܰ�. request�� session�� ó������� ����
		req.setAttribute("cnt", cnt);
		req.setAttribute("id", id);
		
		return "/_Store/View/Login/03_Login_Join_ConfirmId.jsp";	
	}
	

}
