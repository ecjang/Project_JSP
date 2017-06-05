package mvc.board.dao;

import java.util.ArrayList;

import mvc.board.dto.BoardDTO;

public interface BoardDAO {
	
	public int getCount();
	public ArrayList<BoardDTO> getArticles(int start , int end);
	public BoardDTO getArticles(int num);
	public void addReadCnt(int num);
	public int pwCheck(int num, String ps);
	public int upadte(BoardDTO dto);
	public int insert(BoardDTO dto);
	public int delete(int num);
	
}
