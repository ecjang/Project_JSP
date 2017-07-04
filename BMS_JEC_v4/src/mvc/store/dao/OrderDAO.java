package mvc.store.dao;

import java.util.ArrayList;

import javax.activation.DataSource;

import mvc.store.dto.BookDTO;
import mvc.store.dto.CartDTO;
import mvc.store.dto.OrderDTO;

public interface OrderDAO {

	public int bookstate( BookDTO dto);
	
	public int getOrderCnt();
	
	public ArrayList<OrderDTO> getOrder(int start, int end);
	
	public int orderSum();
	
	public int changeState(int o_num, String state);

	
	
	
}
