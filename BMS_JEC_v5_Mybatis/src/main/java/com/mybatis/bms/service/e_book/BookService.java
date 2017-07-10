package com.mybatis.bms.service.e_book;

import org.springframework.ui.Model;

public interface BookService {
	
	
	
	public void book_list( Model model ) throws Exception;
	public void book_detail( Model model ) throws Exception;
	public void book_write_form( Model model ) throws Exception;
	public void book_write_pro( Model model ) throws Exception;
	public void book_delete_pro( Model model ) throws Exception;
	public void book_modify_form( Model model ) throws Exception;
	public void book_modify_pro( Model model ) throws Exception;
	
	/*
	
	
	

	*/
}
