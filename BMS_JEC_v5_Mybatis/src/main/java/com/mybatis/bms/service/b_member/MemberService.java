package com.mybatis.bms.service.b_member;

import org.springframework.ui.Model;

public interface MemberService  {

	public void login_login( Model model ) throws Exception;
	public void login_logout( Model model ) throws Exception;
	public void member_joinform ( Model model ) throws Exception;
	public void member_confirmid( Model model ) throws Exception;
	public void member_join_pro( Model model ) throws Exception;
	public void member_delete( Model model ) throws Exception;
	public void member_modify_form( Model model ) throws Exception;
	public void member_modify_pro( Model model ) throws Exception;
	
	
	/*
	*/
		
	
}
