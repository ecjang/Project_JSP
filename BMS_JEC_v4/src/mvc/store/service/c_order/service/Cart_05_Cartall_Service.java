package mvc.store.service.c_order.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.BookDAO;
import mvc.store.dao.BookDAOImpl;
import mvc.store.dao.CartDAO;
import mvc.store.dao.CartDAOImpl;
import mvc.store.dto.BookDTO;
import mvc.store.dto.CartDTO;
import mvc.store.service.Command_Handler;

public class Cart_05_Cartall_Service implements Command_Handler  {

	@Override
	public String service(HttpServletRequest req, HttpServletResponse res) {
		
		System.out.println("  -> Cart_05_Cartall_Service");
		
		
		int cartall = Integer.parseInt(req.getParameter("cartall"));
		
		// 변수 받기 : 회원번호
		int m_num = (Integer)req.getSession().getAttribute("m_num");
		
		CartDAO cart = new CartDAOImpl().getInstance();
		
		
		if(cartall == 0){
			// 카트 정보 불러오기 -> 리스트에 담기
			ArrayList<CartDTO> carts = cart.cart_getall( m_num );
			
			// 주문 테이블에 담기
			cartall = cart.cart_moveorder( carts );
		}
		
		// 카트 테이블 비우기 id 검색
		if ( cartall > 0 ) cart.cart_afterdel(m_num);
		
		req.setAttribute("cartall", cartall);
		
		
		return "11_order_cartpro.do";
	}

}
