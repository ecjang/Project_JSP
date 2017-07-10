package com.mybatis.bms.service.c_cart;

import org.springframework.ui.Model;

public interface CartService {
	
	public void cart_list_view ( Model model ) throws Exception;
	public void cart_list_pro ( Model model ) throws Exception;
	public void cart_cart_cal ( Model model ) throws Exception;
	public void cart_delete ( Model model ) throws Exception;
	public void cart_toorder ( Model model ) throws Exception;
	public void cart_allorder ( Model model ) throws Exception;
	
	
	
	/*
	
	*/
	
}
