package mvc.store.dao;

import java.util.ArrayList;

import mvc.store.dto.BoardDTO;
import mvc.store.dto.BookDTO;

public interface BookDAO {
	
	static final int SALE				= 1;
	static final int CART 				= 2;
	static final int ORDER 				= 3;
	static final int ORDER_COMPLETE 	= 4;
	static final int ORDER_CANCLE 		= 5;
	static final int PAY_COMPLETE 		= 6;
	static final int PAY_CANCLE 		= 7;
	
	
	public int BookCnt();
	public ArrayList<BookDTO> NewBookCnt(int cnt);
	public ArrayList<BookDTO> BookSearch(String str, int start, int end );
	public ArrayList<BookDTO> getArticles(int start , int end);
	public BookDTO getBookInfo(int num);
	public int pwCheck(int num, String ps);
	public int upadte(BookDTO dto);
	public int book_insert(BookDTO dto);
	public int delete(int num);
	public int searchCnt(String str);
	

	
}
