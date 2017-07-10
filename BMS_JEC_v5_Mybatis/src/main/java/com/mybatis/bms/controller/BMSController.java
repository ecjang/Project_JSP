package com.mybatis.bms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface BMSController {

	
	// NAVIGATION
	public String header_main ( HttpServletRequest req , Model model ) throws Exception;
	public String header_login ( HttpServletRequest req , Model model ) throws Exception;
	public String header_book ( HttpServletRequest req , Model model ) throws Exception;
	public String header_board ( HttpServletRequest req , Model model ) throws Exception;
	public String header_cart ( HttpServletRequest req , Model model ) throws Exception;
	public String header_order ( HttpServletRequest req , Model model ) throws Exception;
	public String header_admin ( HttpServletRequest req , Model model ) throws Exception;
	
	
	// MAIN
	public String main_list ( HttpServletRequest req , Model model ) throws Exception;
	public String main_detail ( HttpServletRequest req , Model model ) throws Exception;
	
	
	// MEMBER
	public String member_login ( HttpServletRequest req , Model model ) throws Exception;
	public String member_logout ( HttpServletRequest req , Model model ) throws Exception;
	public String member_joinform ( HttpServletRequest req , Model model ) throws Exception;
	public String member_confirmid ( HttpServletRequest req , Model model ) throws Exception;
	public String member_joinpro ( HttpServletRequest req , Model model ) throws Exception;
	public String member_deletecheck ( HttpServletRequest req , Model model ) throws Exception;
	public String member_deletepro ( HttpServletRequest req , Model model ) throws Exception;
	public String member_modifycheck ( HttpServletRequest req , Model model ) throws Exception;
	public String member_modifyform ( HttpServletRequest req , Model model ) throws Exception;
	public String member_modifypro ( HttpServletRequest req , Model model ) throws Exception;


	
	// ORDER
	public String order_listview ( HttpServletRequest req , Model model ) throws Exception;
	public String order_listpro (HttpServletRequest req, Model model) throws Exception;
	public String order_statecon ( HttpServletRequest req , Model model ) throws Exception;
	
	
	// ADMIN
	public String admin_view ( HttpServletRequest req , Model model ) throws Exception;


	// BOOK 
	public String book_list ( HttpServletRequest req , Model model ) throws Exception;
	public String book_detail ( HttpServletRequest req , Model model ) throws Exception;
	public String book_writeform ( HttpServletRequest req , Model model ) throws Exception;
	public String book_writepro ( HttpServletRequest req , Model model ) throws Exception;
	public String book_delete ( HttpServletRequest req , Model model ) throws Exception;
	public String book_modifyform ( HttpServletRequest req , Model model ) throws Exception;
	public String book_modifypro ( HttpServletRequest req , Model model ) throws Exception;
	
	
	
	// BOARD
	public String board_list ( HttpServletRequest req , Model model ) throws Exception;
	public String board_detail ( HttpServletRequest req , Model model ) throws Exception;
	public String board_writeform ( HttpServletRequest req , Model model ) throws Exception;
	public String board_wrtiepro ( HttpServletRequest req , Model model ) throws Exception;
	public String board_deletecheck ( HttpServletRequest req , Model model ) throws Exception;
	public String board_deletepro ( HttpServletRequest req , Model model ) throws Exception;
	public String board_modifycheck ( HttpServletRequest req , Model model ) throws Exception;
	public String board_modifyform ( HttpServletRequest req , Model model ) throws Exception;
	public String board_modifypro ( HttpServletRequest req , Model model ) throws Exception;
	
	
	// CART
	public String cart_listview ( HttpServletRequest req , Model model ) throws Exception;
	public String cart_listpro ( HttpServletRequest req , Model model ) throws Exception;
	public String cart_quancon ( HttpServletRequest req , Model model ) throws Exception;
	public String cart_cartdelete ( HttpServletRequest req , Model model ) throws Exception;
	public String cart_reqorder ( HttpServletRequest req , Model model ) throws Exception;
	public String cart_reqorderall ( HttpServletRequest req , Model model ) throws Exception;
	public String cart_clean ( HttpServletRequest req , Model model ) throws Exception;
	
	
	
	
	
	
	
	/*
	
	
	
	
	
	
	
	

	
	*/
	


}
