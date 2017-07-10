package com.mybatis.bms.controller;


import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mybatis.bms.service.a_main.MainService;
import com.mybatis.bms.service.b_member.MemberService;
import com.mybatis.bms.service.c_cart.CartService;
import com.mybatis.bms.service.d_order.OrderService;
import com.mybatis.bms.service.e_book.BookService;
import com.mybatis.bms.service.f_boarad.BoardService;
import com.mybatis.bms.service.g_admin.AdminService;



@Controller
public class BMSControllerImpl implements BMSController {
	
	@Inject private MainService main;
	@Inject private MemberService member;
	@Inject private BookService book;
	@Inject private BoardService board;
	@Inject private CartService cart;
	@Inject private OrderService order;
	@Inject private AdminService admin;
	
	// MAIN
	@Override
	@RequestMapping(value="/main_search" , method={RequestMethod.GET , RequestMethod.POST})
	public String main_list(HttpServletRequest req, Model model) throws Exception {
		System.out.println("\n main_search...");
		model.addAttribute("req",req);
		main.main_serch(req, model);
		return "/Store/01_Main/01_Main_Search";
	}
	@Override
	@RequestMapping(value="/main_detail" , method={RequestMethod.GET , RequestMethod.POST})
	public String main_detail(HttpServletRequest req, Model model) throws Exception {
		System.out.println("\n main_detail...");
		model.addAttribute("req",req);
		main.main_detail(req,model);
		return "/Store/01_Main/02_Main_Detail";
	}
	
	// NAVIGATION
	@Override
	@RequestMapping(value="/header_main" , method={RequestMethod.GET , RequestMethod.POST})
	public String header_main(HttpServletRequest req, Model model) throws Exception {
		System.out.println("\n header_main...");
		model.addAttribute("req", req);
		return "redirect:/main_search";
	}
	@Override
	@RequestMapping(value="/header_login" , method={RequestMethod.GET , RequestMethod.POST})
	public String header_login(HttpServletRequest req, Model model) throws Exception {
		System.out.println("\n header_login...");
		model.addAttribute("req", req);
		return "/Store/02_Member/01_Login_Main";
	}
	@Override
	@RequestMapping(value="/header_book" , method={RequestMethod.GET , RequestMethod.POST})
	public String header_book(HttpServletRequest req, Model model) throws Exception {
		System.out.println("\n header_book...");
		model.addAttribute("req", req);
		return "redirect:/book_list";
	}
	@Override
	@RequestMapping(value="/header_board" , method={RequestMethod.GET , RequestMethod.POST})
	public String header_board(HttpServletRequest req, Model model) throws Exception {
		System.out.println("\n header_board...");
		model.addAttribute("req", req);
		/*model.addAttribute("kind","NOTICE");*/
		return "redirect:/board_list";
	}
	@Override
	@RequestMapping(value="/header_cart" , method={RequestMethod.GET , RequestMethod.POST})
	public String header_cart(HttpServletRequest req, Model model) throws Exception {
		System.out.println("\n header_cart...");
		model.addAttribute("req", req);
		if( req.getParameter("back") != null ){
			return req.getParameter("back");
		}
		return "redirect:/cart_listview";
	}
	@Override
	@RequestMapping(value="/header_order" , method={RequestMethod.GET , RequestMethod.POST})
	public String header_order ( HttpServletRequest req , Model model ) throws Exception{
		System.out.println("\n header_order...");
		model.addAttribute("req", req);
		return "redirect:/order_view";
	}
	@Override
	@RequestMapping(value="/header_admin" , method={RequestMethod.GET , RequestMethod.POST})
	public String header_admin ( HttpServletRequest req , Model model ) throws Exception{
		System.out.println("\n header_admin...");
		model.addAttribute("req",req);
		admin.admin_pro(model);
		return "/Store/06_Admin/01_Admin_Main";
	}
	
	// MEMBER
	@Override
	@RequestMapping( value="/login_loginpro" , method={RequestMethod.GET , RequestMethod.POST} )
	public String member_login(HttpServletRequest req, Model model) throws Exception {
		System.out.println("\n member_loginpro...");
		model.addAttribute("req",req);
		member.login_login(model);
		
		if( req.getAttribute("back")!=null ){
			String back = (String)req.getAttribute("back");
			return back;
		}
		return "/Store/02_Member/01_Login_Main";
	}
	@Override
	@RequestMapping( value="/login_logout" , method={RequestMethod.GET , RequestMethod.POST} )
	public String member_logout ( HttpServletRequest req , Model model ) throws Exception{
		System.out.println("\n login_logout...");
		model.addAttribute("req",req);
		member.login_logout(model);
		return "/Store/02_Member/01_Login_Main";
	}
	@Override
	@RequestMapping( value="/member_joinform" , method={RequestMethod.GET , RequestMethod.POST} )
	public String member_joinform ( HttpServletRequest req , Model model ) throws Exception{
		System.out.println("\n member_joinform...");
		model.addAttribute("req",req);
		member.member_joinform(model);
		return "/Store/02_Member/02_Login_Join_Form";
	}
	@Override
	@RequestMapping( value="/member_confirmid" , method={RequestMethod.GET , RequestMethod.POST} )
	public String member_confirmid ( HttpServletRequest req , Model model ) throws Exception{
		System.out.println("\n member_confirmid...");
		model.addAttribute("req",req);
		member.member_confirmid(model);
		return "/Store/02_Member/03_Login_Join_ConfirmId";
	}
	@Override
	@RequestMapping( value="/member_joinpro" , method={RequestMethod.GET , RequestMethod.POST} )
	public String member_joinpro ( HttpServletRequest req , Model model ) throws Exception{
		System.out.println("\n member_joinpro...");
		model.addAttribute("req",req);
		member.member_join_pro(model);
		return "/Store/02_Member/04_Login_Join_Pro";
	}
	@Override
	@RequestMapping( value="/member_deletecheck" , method={RequestMethod.GET , RequestMethod.POST} )
	public String member_deletecheck ( HttpServletRequest req , Model model ) throws Exception{
		System.out.println("\n member_deletecheck...");
		model.addAttribute("req",req);
		return "/Store/02_Member/05_Login_Delete_Form";
	}
	@Override
	@RequestMapping( value="/member_deletepro" , method={RequestMethod.GET , RequestMethod.POST} )
	public String member_deletepro ( HttpServletRequest req , Model model ) throws Exception{
		System.out.println("\n member_deletepro...");
		model.addAttribute("req",req);
		member.member_delete(model);
		return "/Store/02_Member/06_Login_Delete_Pro";
	}
	@Override
	@RequestMapping( value="/member_modifycheck" , method={RequestMethod.GET , RequestMethod.POST} )
	public String member_modifycheck(HttpServletRequest req, Model model) throws Exception {
		System.out.println("\n member_modifycheck...");
		model.addAttribute("req",req);
		return "/Store/02_Member/07_Login_Modify_Form";
	}
	@Override
	@RequestMapping( value="/member_modifyform" , method={RequestMethod.GET , RequestMethod.POST} )
	public String member_modifyform(HttpServletRequest req, Model model) throws Exception {
		System.out.println("\n member_modifyform...");
		model.addAttribute("req",req);
		member.member_modify_form(model);
		return "/Store/02_Member/08_Login_Modify_View";
	}
	@Override
	@RequestMapping( value="/member_modifypro" , method={RequestMethod.GET , RequestMethod.POST} )
	public String member_modifypro(HttpServletRequest req, Model model) throws Exception {
		System.out.println("\n member_modifypro...");
		model.addAttribute("req",req);
		member.member_modify_pro(model);
		return "/Store/02_Member/09_Login_Modify_Pro";
	}

	// BOOK
	@Override
	@RequestMapping(value="/book_list" , method={RequestMethod.GET , RequestMethod.POST})
	public String book_list(HttpServletRequest req, Model model) throws Exception {
		System.out.println("\n book_list...");
		model.addAttribute("req",req);
		book.book_list(model);
		return "/Store/04_Book/01_Book_List";
	}
	@Override
	@RequestMapping(value="/book_detail" , method={RequestMethod.GET , RequestMethod.POST})
	public String book_detail(HttpServletRequest req, Model model) throws Exception {
		System.out.println("\n book_list...");
		model.addAttribute("req",req);
		book.book_detail(model);
		return "/Store/04_Book/02_Book_Detail";
	}
	@Override
	@RequestMapping(value="/book_writeform" , method={RequestMethod.GET , RequestMethod.POST})
	public String book_writeform(HttpServletRequest req, Model model) throws Exception {
		System.out.println("\n book_list...");
		model.addAttribute("req",req);
		book.book_write_form(model);
		return "/Store/04_Book/03_Book_Write_Form";
	}
	@Override
	@RequestMapping(value="/book_writepro" , method={RequestMethod.GET , RequestMethod.POST})
	public String book_writepro(HttpServletRequest req, Model model) throws Exception {
		System.out.println("\n book_list...");
		model.addAttribute("req",req);
		book.book_write_pro(model);
		return "/Store/04_Book/04_Book_Write_Pro";
	}
	@Override
	@RequestMapping(value="/book_delete" , method={RequestMethod.GET , RequestMethod.POST})
	public String book_delete(HttpServletRequest req, Model model) throws Exception {
		System.out.println("\n book_list...");
		model.addAttribute("req",req);
		book.book_delete_pro(model);
		return "/Store/04_Book/07_Book_Delete_Pro";
	}
	@Override
	@RequestMapping(value="/book_modifyform" , method={RequestMethod.GET , RequestMethod.POST})
	public String book_modifyform(HttpServletRequest req, Model model) throws Exception {
		System.out.println("\n book_modifyform...");
		model.addAttribute("req",req);
		book.book_modify_form(model);
		return "/Store/04_Book/05_Book_Modify_Form";
	}
	@Override
	@RequestMapping(value="/book_modifypro" , method={RequestMethod.GET , RequestMethod.POST})
	public String book_modifypro(HttpServletRequest req, Model model) throws Exception {
		System.out.println("\n book_modifypro...");
		model.addAttribute("req",req);
		book.book_modify_pro(model);
		return "/Store/04_Book/06_Book_Modify_Pro";
	}

	// BOARD
	@Override
	@RequestMapping(value="/board_list" , method={RequestMethod.GET , RequestMethod.POST})
	public String board_list(HttpServletRequest req, Model model) throws Exception {
		System.out.println("\n board_list...");
		model.addAttribute("req",req);
		board.board_list(model);
		return "/Store/05_Board/01_Board_List";
	}
	@Override
	@RequestMapping(value="/board_detail" , method={RequestMethod.GET , RequestMethod.POST})
	public String board_detail(HttpServletRequest req, Model model) throws Exception {
		System.out.println("\n board_detail...");
		model.addAttribute("req",req);
		board.board_detail(model);
		return "/Store/05_Board/02_Board_Detail";
	}
	@Override
	@RequestMapping(value="/board_writeform" , method={RequestMethod.GET , RequestMethod.POST})
	public String board_writeform(HttpServletRequest req, Model model) throws Exception {
		System.out.println("\n board_writeform...");
		model.addAttribute("req",req);
		board.board_write_form(model);
		
		if( req.getAttribute("back") != null){
			String back = (String) req.getAttribute("back");
			return back;
		}
		
		return "/Store/05_Board/03_Board_Write_From";
	}
	@Override
	@RequestMapping(value="/board_wrtiepro" , method={RequestMethod.GET , RequestMethod.POST})
	public String board_wrtiepro(HttpServletRequest req, Model model) throws Exception {
		System.out.println("\n board_wrtiepro...");
		model.addAttribute("req",req);
		board.board_write_pro(model);
		
		if( req.getAttribute("back") != null ){
			String back = (String) req.getAttribute("back");
			return back;
		}
		
		return "/Store/05_Board/04_Board_Write_Pro";
	}
	@Override
	@RequestMapping(value="/board_deletecheck" , method={RequestMethod.GET , RequestMethod.POST})
	public String board_deletecheck (HttpServletRequest req , Model model ) throws Exception{
		System.out.println("\n board_deletecheck...");
		model.addAttribute("req",req);
		board.board_delte_check(model);
		
		if( req.getAttribute("back") != null ){
			String back = (String) req.getAttribute("back");
			
			switch(back){
			case "admin" :
				int scnt = 1;
				int no = Integer.parseInt( req.getParameter("no") );
				int pageNum = Integer.parseInt( req.getParameter("pageNum") );
				String kind = req.getParameter("kind");
				
				req.setAttribute("scnt",scnt);
				req.setAttribute("no",no);
				req.setAttribute("pageNum",pageNum);
				req.setAttribute("kind",kind);

				model.addAttribute("req", req);
				board.board_delete_pro(model);
				return "/Store/05_Board/09_Board_Delete_Pro";
				
			case "login" :
				return "redirect:/header_login?cnt=3";
			}
		}
		
		return "/Store/05_Board/08_Board_Delete_Form";
	}
	@Override
	@RequestMapping(value="/board_deletepro" , method={RequestMethod.GET , RequestMethod.POST})
	public String board_deletepro(HttpServletRequest req, Model model) throws Exception {
		System.out.println("\n board_deletepro...");
		model.addAttribute("req",req);
		board.board_delete_pro(model);
		return "/Store/05_Board/09_Board_Delete_Pro";
	}
	@Override
	@RequestMapping(value="/board_modifycheck" , method={RequestMethod.GET , RequestMethod.POST})
	public String board_modifycheck(HttpServletRequest req, Model model) throws Exception {
		System.out.println("\n board_modifycheck...");
		model.addAttribute("req",req);
		board.board_modify_check(model);
		
		if( req.getAttribute("back") != null ){
			String back = (String) req.getAttribute("back");
			
			switch(back){
			case "admin" :
				int cnt = 1;
				int admin = 1;
				int no = Integer.parseInt( req.getParameter("no") );
				int pageNum = Integer.parseInt( req.getParameter("pageNum") );
				String kind = req.getParameter("kind");
				/*System.out.println(no+", "+pageNum+", "+kind);*/
				/*
				model.addAttribute("cnt", cnt);
				model.addAttribute("admin", admin);
				model.addAttribute("no", no);
				model.addAttribute("pageNum", pageNum);
				model.addAttribute("kind", kind);
				*/
				req.setAttribute("cnt",cnt);
				req.setAttribute("admin",admin);
				req.setAttribute("no",no);
				req.setAttribute("pageNum",pageNum);
				req.setAttribute("kind",kind);
				
				model.addAttribute("req", req);
				board.board_modify_form(model);
				return "/Store/05_Board/06_Board_Modify_View";
				
				
			case "login" :
				return "redirect:/header_login?cnt=3";
			}
		}
		
		return "/Store/05_Board/05_Board_Modify_From";
	}
	@Override
	@RequestMapping(value="/board_modifyform" , method={RequestMethod.GET , RequestMethod.POST})
	public String board_modifyform(HttpServletRequest req, Model model) throws Exception {
		System.out.println("\n board_modifyform...");
		model.addAttribute("req",req);
		board.board_modify_form(model);
		
		return "/Store/05_Board/06_Board_Modify_View";
	}
	@Override
	@RequestMapping(value="/board_modifypro" , method={RequestMethod.GET , RequestMethod.POST})
	public String board_modifypro(HttpServletRequest req, Model model) throws Exception {
		System.out.println("\n board_modifypro...");
		model.addAttribute("req",req);
		board.board_modify_pro(model);
		return "/Store/05_Board/07_Board_Modify_Pro";
	}

	// CART
	@Override
	@RequestMapping(value="/cart_listview" , method={RequestMethod.GET , RequestMethod.POST})
	public String cart_listview ( HttpServletRequest req , Model model ) throws Exception{
		System.out.println("\n cart_listview...");
		model.addAttribute("req",req);
		cart.cart_list_view(model);
		return "redirect:/cart_listpro";
	}
	@Override
	@RequestMapping(value="/cart_listpro" , method={RequestMethod.GET , RequestMethod.POST})
	public String cart_listpro ( HttpServletRequest req , Model model ) throws Exception{
		System.out.println("\n cart_listpro...");
		model.addAttribute("req",req);
		cart.cart_list_pro(model);
		return "/Store/03_Order/01_Order_Cart";
	}
	@Override
	@RequestMapping(value="/cart_quancon" , method={RequestMethod.GET , RequestMethod.POST})
	public String cart_quancon(HttpServletRequest req, Model model) throws Exception {
		System.out.println("\n cart_quancon...");
		model.addAttribute("req",req);
		cart.cart_cart_cal(model);
		return "redirect:/cart_listpro";
	}
	@Override
	@RequestMapping(value="/cart_cartdelete" , method={RequestMethod.GET , RequestMethod.POST})
	public String cart_cartdelete ( HttpServletRequest req , Model model ) throws Exception{
		System.out.println("\n cart_cartdelete...");
		model.addAttribute("req",req);
		cart.cart_delete(model);
		return "redirect:/cart_listpro";
	}
	@Override
	@RequestMapping(value="/cart_reqorder" , method={RequestMethod.GET , RequestMethod.POST})
	public String cart_reqorder(HttpServletRequest req, Model model) throws Exception {
		System.out.println("\n cart_reqorder...");
		model.addAttribute("req",req);
		cart.cart_toorder(model);
		return "redirect:/cart_listpro";
	}
	@Override
	@RequestMapping(value="/cart_reqorderall" , method={RequestMethod.GET , RequestMethod.POST})
	public String cart_reqorderall(HttpServletRequest req, Model model) throws Exception {
		System.out.println("\n cart_reqorderall...");
		model.addAttribute("req",req);
		cart.cart_allorder(model);
		return "redirect:/cart_listpro";
	}
	@Override
	@RequestMapping(value="/cart_clean" , method={RequestMethod.GET , RequestMethod.POST})
	public String cart_clean(HttpServletRequest req, Model model) throws Exception {
		System.out.println("\n cart_clean...");
		model.addAttribute("req",req);
		cart.cart_allorder(model);
		return "redirect:/cart_listpro";
	}
	
	// ORDER
	@Override
	@RequestMapping(value="/order_view" , method={RequestMethod.GET , RequestMethod.POST})
	public String order_listview(HttpServletRequest req, Model model) throws Exception {
		System.out.println("\n order_view...");
		model.addAttribute("req",req);
		order.order_view(model);
		return "redirect:/order_listpro";
	}
	@Override
	@RequestMapping(value="/order_listpro" , method={RequestMethod.GET , RequestMethod.POST})
	public String order_listpro (HttpServletRequest req, Model model) throws Exception{
		System.out.println(" order_listpro...");
		model.addAttribute("req",req);
		order.order_pro(model);
		return "/Store/03_Order/02_Order_Order";
	}
	@Override
	@RequestMapping(value="/order_statecon" , method={RequestMethod.GET , RequestMethod.POST})
	public String order_statecon(HttpServletRequest req, Model model) throws Exception {
		System.out.println(" order_statecon...");
		model.addAttribute("req",req);
		order.order_state(model);
		return "redirect:/order_listpro";
	}
	
	//ADMIN
	@Override
	@RequestMapping(value="/admin_pro" , method={RequestMethod.GET , RequestMethod.POST})
	public String admin_view ( HttpServletRequest req , Model model ) throws Exception{
		System.out.println("\n admin_pro...");
		model.addAttribute("req",req);
		admin.admin_pro(model);
		return "/Store/06_Admin/01_Admin_Main";
	}
	
	
	

	
}
