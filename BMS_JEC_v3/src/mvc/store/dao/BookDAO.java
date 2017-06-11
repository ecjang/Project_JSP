package mvc.store.dao;

import java.util.ArrayList;

import mvc.store.dto.BoardDTO;
import mvc.store.dto.BookDTO;

public interface BookDAO {
	
	static final int SALEABLE 			= 1;
	static final int CART 				= 2;
	static final int ORDER_COMPLETE 	= 3;
	static final int PAY_WAITING 		= 4;
	static final int PAY_COMPPLETE 		= 5;
	static final int SHIPPING_READY 	= 6;
	static final int SHIPPING 			= 7;
	static final int SHIPPING_COMPLETE 	= 8;
	static final int RETURN_REQUEST 	= 9;
	static final int RETURN_COMPLETE 	= 10;
	
	public int getCount();
	public ArrayList<BookDTO> getArticles(int start , int end);
	public BookDTO getArticles(int num);
	public int pwCheck(int num, String ps);
	public int upadte(BookDTO dto);
	public int book_insert(BookDTO dto);
	public int delete(int num);
	
	public BoardDTO search(String str);
	
}
