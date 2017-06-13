package mvc.store.dao;

import java.util.ArrayList;

import mvc.store.dto.BoardDTO;

public interface BoardDAO {

	public int getCount(String kind);
	public ArrayList<BoardDTO> getArticles(int start , int end , String kind);
	public BoardDTO getArticles(int num);
	public int pwCheck(int num, String ps);
	public int upadte(BoardDTO dto);
	public int delete(int num);
	public void addview(int no);
	public int idCheck(int Mnum);
	public int insert(BoardDTO dto );
	public String kindCheck(int no);
	
}
