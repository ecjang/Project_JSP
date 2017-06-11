package mvc.store.dao;

import mvc.store.dto.MemberDTO;

public interface MemberDAO  {
	
	public int num();	
	
	public int idCheck(String strid); 	// �ߺ�Ȯ�� üũ
	public int mem_insert(MemberDTO dto);	// ȸ������
	public int check(String strid, String strpwd);	// ���̵�, ��й�ȣ üũ, LoginProHandler���� ��û
	public int delete(String strid );	// ȸ��Ż��, DeleteProHandler���� ��û
	public MemberDTO getMember(String strid);	// ȸ��������������, ModifyViewHandler���� ��û
	public int update(MemberDTO dto);	// ȸ������ ����, ModifypROHandler ���� ȣ��
	
	public int kind(String id);
	public int m_num(String id);
	
	
}
