package com.mybatis.bms.dao.c_book;

import java.util.ArrayList;
import java.util.Map;

import com.mybatis.bms.dto.BookDTO;

public interface BookDAO {
	
	public int cnt_book (  );
	public  ArrayList<BookDTO> get_books ( Map<String,Object> daoMap );
	public BookDTO getbook ( int b_num );
	public int delete_book ( int b_num );
	public int insert_book ( BookDTO dto );
	public int upadte_book ( BookDTO dto );
	
	
	/*
	
	
	*/
	
}
