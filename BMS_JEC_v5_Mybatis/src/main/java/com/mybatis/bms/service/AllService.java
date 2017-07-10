package com.mybatis.bms.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface AllService {
	
	
	public void main_serch( HttpServletRequest req , Model model ) throws Exception;
	public void main_detail( HttpServletRequest req , Model model ) throws Exception;
	
	/*
	member_input
	login_join_form
	join_confirmid
	login_join_pro
	login_login
	login_logout
	member_delete
	member_modify_form
	member_modify_pro

	book_list
	book_detail
	book_write_form
	book_write_pro
	book_modify_form
	book_modify_pro
	book_delete_pro

	board_list
	board_detail
	board_write_form
	board_write_pro
	board_modify_check
	board_modify_form
	board_modify_pro
	board_delete_form
	board_delte_pro

	cart_list_view
	cart_list_pro
	cart_cart_cal
	cart_delete
	cart_toorder

	order_view
	order_pro
	order_state
	admin_pro
	*/
}
