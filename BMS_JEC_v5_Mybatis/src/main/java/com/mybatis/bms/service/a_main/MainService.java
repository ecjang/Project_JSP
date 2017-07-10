package com.mybatis.bms.service.a_main;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.mybatis.bms.service.AllService;

public interface MainService {
	
	
	public void main_serch( HttpServletRequest req , Model model ) throws Exception;
	
	public void main_detail( HttpServletRequest req , Model model ) throws Exception;
	
	

}
