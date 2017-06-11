package mvc.store.dao;

import mvc.store.dto.MemberDTO;

public interface MemberDAO  {
	
	public int num();	
	
	public int idCheck(String strid); 	// 중복확인 체크
	public int mem_insert(MemberDTO dto);	// 회원가입
	public int check(String strid, String strpwd);	// 아이디, 비밀번호 체크, LoginProHandler에서 요청
	public int delete(String strid );	// 회원탈퇴, DeleteProHandler에서 요청
	public MemberDTO getMember(String strid);	// 회원정보가져오기, ModifyViewHandler에서 요청
	public int update(MemberDTO dto);	// 회원정보 수정, ModifypROHandler 에서 호출
	
	public int kind(String id);
	public int m_num(String id);
	
	
}
