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
	
	
	
	public int getCount();
	public ArrayList<BookDTO> getArticles(int start , int end);
	public BookDTO getArticles(int num);
	public int pwCheck(int num, String ps);
	public int upadte(BookDTO dto);
	public int book_insert(BookDTO dto);
	public int delete(int num);
	
	public ArrayList<BookDTO> booksearch(String str, int start, int end );
	public int searchCnt(String str);
	
	/*------------------------------------*/
	
	public int bookstate( BookDTO dto);
	public ArrayList<BookDTO> getcart(int start , int end);
	public int getCount_cart();
	public int cart_ordernum( int c_num , int ordernum );
	public int cart_orderdel(int c_num);
	public int cart_orderconfirm( int c_num , int ordernum );
	public int cart_quan(int b_num);
	public BookDTO cart_input(int c_num , int ordernum);
	public int cart_output(BookDTO dto);
	public ArrayList<BookDTO> cart_getcart(int id);
	public int cart_moveorder(ArrayList<BookDTO> dtos);
	public int cart_afterdel(int m_num);
	
	public ArrayList<BookDTO> getorder(int start, int end);
	public int getOrderCount();
	public int changeState(int o_num, String state);
	public int sum();
	
}
