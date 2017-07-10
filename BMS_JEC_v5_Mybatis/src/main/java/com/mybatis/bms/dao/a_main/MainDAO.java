package com.mybatis.bms.dao.a_main;

import java.util.List;
import java.util.Map;

import com.mybatis.bms.dto.BookDTO;

public interface MainDAO  {

	public int cnt_search ( String str );
	public  List<BookDTO> get_newbooks ( int cnt );
	public  List<BookDTO> get_searchbooks ( Map<String,Object> daoMap );
	public BookDTO get_book ( int b_num );
	
	
}
