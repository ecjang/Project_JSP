package mvc.store.member.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.MemberDAO;
import mvc.store.dao.MemberDAOImpl;
import mvc.store.dto.MemberDTO;

public class ModifyViewHandler implements Command_Handler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {
		
		System.out.println("\n - ModifyViewHandler.java �Դϴ�.");
		System.out.println(" - ���̵�� ��й�ȣ�� �Է¹޾� �����۾��� �մϴ�. ");
		
		
		// 1�ܰ�. ȭ�����κ��� Ȥ�� �������κ��� �Է¹��� ��( ���̵�, �н����� )�� �����´�. �����ϱ� ���ؼ�
		String strid = (String)req.getSession().getAttribute("memId");
		String strpwd = (String)req.getParameter("passwd"); 
		
		System.out.println(" - �׽�Ʈ : �ҷ��� id�� : " + strid );
		System.out.println(" - �׽�Ʈ : �ҷ��� pwd�� : " + strpwd);
		
		
		// 2-1�ܰ�. dao��ü ����
		MemberDAO dao = MemberDAOImpl.getInsatance();
		
		// 2-2�ܰ�. check(���̵�,���) ȣ��  : ���̵�� �н����尡 �´��� üũ,  int selectCnt�� �޴´�.
		//
		System.out.println("\n  - ModifyViewHandler���� check�� ȣ���մϴ�.");
		int selectCnt = dao.check(strid, strpwd);
		
		// 2-3�ܰ�. getMember(���̵�) ȣ�� : ȸ�������� �����´�. selectCnt�� 1�� ��쿡�� ����(���̵�, ��й�ȣ�� db�� ���� ��� )
		System.out.println("\n  - ModifyViewHandler���� getMember�� ȣ���մϴ�.");
		if( selectCnt == 1 ){  
			MemberDTO dto = dao.getMember(strid); 
			req.setAttribute("dto", dto); 
		}
		
		// 3�ܰ�. request�� session�� ó������� ����
		req.setAttribute("selectCnt", selectCnt);
		
		
		
		return "/member/modifyView.jsp";
	}

}
