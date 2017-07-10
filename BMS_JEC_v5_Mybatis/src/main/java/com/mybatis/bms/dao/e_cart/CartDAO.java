package com.mybatis.bms.dao.e_cart;


import java.util.List;
import java.util.Map;

import com.mybatis.bms.dto.BookDTO;
import com.mybatis.bms.dto.CartDTO;


public interface CartDAO {
	
	public int insert_cart_01 ( BookDTO dto );	
	public int insert_cart_02 ( BookDTO dto );	
	public int insert_cart_03 ( BookDTO dto );
	public int cnt_cart (  );
	public List<CartDTO> get_carts (  Map<String,Object> daoMap );
	public int cnt_cartquan ( int c_num );
	public int cnt_bookquan ( int b_num );
	public int quanminus_cart_01 (  Map<String,Object> daoMap );
	public int quanminus_cart_02 (  Map<String,Object> daoMap );
	public int quanplus_cart_01 (  Map<String,Object> daoMap );
	public int quanplus_cart_02 (  Map<String,Object> daoMap );
	public int delete_cart_byc_num ( int c_num );
	public CartDTO get_cart ( Map<String,Object> daoMap );
	public int insert_order ( CartDTO dto  );
	public List<CartDTO> getall_cart ( int m_num );
	public int allinsert_order( CartDTO dto );
	public int delete_cart_bym_num ( int m_num );
	
	
	
	
	/*
	
	
	
	
	
	

	*/
	
}
