package com.mybatis.bms.dao.b_member;

import java.util.Map;

import com.mybatis.bms.dto.MemberDTO;

public interface MemberDAO {
	
	public MemberDTO check_login ( Map<String,Object> daoMap );
	public int cnt_member ( );
	public int check_id ( String id );
	public int insert_member ( MemberDTO DTO );
	public int delete_member ( String id );
	public int get_member ( String id );
	public int update_member ( MemberDTO dto );
	
	public MemberDTO check_login_bymnum ( int Mnum );
	
	/*
	
	
	 */
	
}
