package mvc.store.dao;

import java.util.ArrayList;

import mvc.store.dto.BookDTO;
import mvc.store.dto.CartDTO;

public interface CartDAO {

	public int bookstate(BookDTO dto);

	public ArrayList<CartDTO> getcart(int start, int end);

	public int getCount_cart();

	public int cartquanMinus(int c_num, int quan_abs);
	
	public int cartquanPlus(int c_num, int quan_abs);
	
	public int cartdel(int c_num);

	public int cart_orderconfirm(int c_num, int quan);

	public int cartquanCnt(int c_num);
	
	public int bookquanCnt(int b_num);

	public CartDTO cart_getCart(int c_num, int quan);

	public int cart_putOrder(CartDTO dto);
	
	public ArrayList<CartDTO> cart_getall(int id);

	public int cart_moveorder( ArrayList<CartDTO> carts );

	public int cart_afterdel(int m_num);

	
}
