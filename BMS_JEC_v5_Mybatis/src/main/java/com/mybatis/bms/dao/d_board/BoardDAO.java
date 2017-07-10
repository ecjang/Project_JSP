package com.mybatis.bms.dao.d_board;

import java.util.List;
import java.util.Map;

import com.mybatis.bms.dto.BoardDTO;
import com.mybatis.bms.dto.MemberDTO;


public interface BoardDAO {
	

	public int cnt_board ( String kind );
	public List<BoardDTO> get_boards (  Map<String,Object> daoMap );
	public BoardDTO get_board ( int no );
	public int add_viewcnt ( int no );
	
	public MemberDTO check_bym_num ( int Mnum );
	
	public int insert_board_01 (  );
	public int insert_board_02 ( Map<String,Object> daoMap );
	public int insert_board_03 ( BoardDTO dto );
	
	public int upadte_board ( BoardDTO dto );
	
	
	public BoardDTO delete_board_01 ( int num );
	public List<BoardDTO> delete_board_02 ( Map<String,Object> daoMap );
	public int delete_board_03 ( Map<String,Object> daoMap );
	public int delete_board_04 ( int num );
	
	
	/*
	
	
	*/
	
}
