package com.mybatis.bms.dao.f_order;


import java.util.List;
import java.util.Map;

import com.mybatis.bms.dto.BookDTO;
import com.mybatis.bms.dto.OrderDTO;


public interface OrderDAO {
	
	
	
	public int change_orderstate_01 ( BookDTO dto );
	public int change_orderstate_02 ( BookDTO dto );
	public int change_orderstate_03 ( BookDTO dto );
	
	public int cnt_order (  );
	public List<OrderDTO> get_orders (  Map<String,Object> daoMap );
	public int sum_order (  );
	
	public int input_order_01 ( int o_num );
	public int input_order_02 ( Map<String,Object> daoMap );
	public int input_order_03 ( int o_num );
	public int input_order_04 ( Map<String,Object> daoMap );
	
	/*
	
	
	
	
	public int change_orderstate ( BookDTO DTO );
	*/
	
	
}
