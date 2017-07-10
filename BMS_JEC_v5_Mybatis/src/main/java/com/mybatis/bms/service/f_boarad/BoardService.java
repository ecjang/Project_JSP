package com.mybatis.bms.service.f_boarad;

import org.springframework.ui.Model;

public interface BoardService {
	
	public void board_list( Model model ) throws Exception;
	public void board_detail( Model model ) throws Exception;
	public void board_write_form( Model model ) throws Exception;
	public void board_write_pro( Model model ) throws Exception;
	public void board_modify_check( Model model ) throws Exception;
	public void board_modify_form( Model model ) throws Exception;
	public void board_modify_pro( Model model ) throws Exception;
	public void board_delte_check( Model model ) throws Exception;
	public void board_delete_pro( Model model ) throws Exception;
	
	
	/*
	
	
	
	*/
	
}
