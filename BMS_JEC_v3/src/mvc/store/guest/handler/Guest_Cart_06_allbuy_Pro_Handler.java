package mvc.store.guest.handler;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.BookDAO;
import mvc.store.dao.BookDAOIpml;
import mvc.store.dto.BookDTO;

public class Guest_Cart_06_allbuy_Pro_Handler implements Command_Handler  {

	@Override
	public String orderexecute(HttpServletRequest req, HttpServletResponse res) {
		
		
		int allbuy_cnt = Integer.parseInt(req.getParameter("allbuy_cnt"));
		// 변수 받기 : 회원번호
		int m_num = (Integer)req.getSession().getAttribute("m_num");
		
		BookDAO dao = new BookDAOIpml().getInstance();
		
		
		if(allbuy_cnt == 0){
			// 카트 정보 불러오기 -> 리스트에 담기
			ArrayList<BookDTO> dtos = dao.cart_getcart( m_num );
			
			// 주문 테이블에 담기
			allbuy_cnt = dao.cart_moveorder( dtos );
		}

		
		// 카트 테이블 비우기 id 검색
		if ( allbuy_cnt > 0 ) dao.cart_afterdel(m_num);
		
		
		req.setAttribute("allbuy_cnt", allbuy_cnt);
		
		return "guest_cart_pro.do";
	}

}
