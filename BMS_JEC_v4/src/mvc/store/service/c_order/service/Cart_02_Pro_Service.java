package mvc.store.service.c_order.service;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.CartDAO;
import mvc.store.dao.CartDAOImpl;
import mvc.store.dto.CartDTO;
import mvc.store.service.Command_Handler;


public class Cart_02_Pro_Service implements Command_Handler {

	@Override
	public String service(HttpServletRequest req, HttpServletResponse res) {
		
		System.out.println("  -> Cart_02_Pro_Service");
		
		
		// ���� �ޱ� : �� 2��
		//  pageNum / start
		/*int start = Integer.parseInt(req.getParameter("start"));	// ������ ��ȣ*/	
		String pageNum = req.getParameter("pageNum");	
		if(pageNum==null) pageNum="1";	// ������ ��ȣ
		
		
		// ������ ���� 
		int pageSize = 10;		// �������� �� ����
		int pageBlock = 10;		// ������ ����
		int start=0;			// �Խù� ���� ��ȣ
		int end=0;				// �Խɹ� �� ��ȣ
		int number=0; 			// �Խù� ��ȣ
		int currentPage=0;		// ���� ������ ��ȣ
		int pageCount=0;		// ������ ��ü ��ȣ
		int startPage=0; 		// ���� ������ ��ȣ
		int endPage=0;			// �� ������ ��ȣ
		
		
		
		// ��ٱ��� ��� ���� �ҷ�����
		CartDAO cart = CartDAOImpl.getInstance();
		int cnt = cart.getCount_cart();						// ��� �ҷ�����

		
		/*--------------------------------*/
		
		
		currentPage = Integer.parseInt(pageNum);			// ���� ������ ����
		
		start = (currentPage-1)*pageSize +1;				// �Խñ� ���۹�ȣ
		end   = start+pageSize -1;	if(end>cnt) end=cnt;	// �Խñ� ����ȣ
		number = cnt- (currentPage-1)*pageSize;				// �Խñ�  ��ȣ

		startPage=(currentPage/pageBlock)*pageBlock +1;		// �Խñ� ���� ��ȣ
		if(currentPage%pageBlock==0){						// �Խñ� ��ȣ�� ������ ��ϰ� ���� ���
			startPage = startPage-pageBlock ;
		}
		pageCount = (cnt/pageSize)+(cnt%pageSize>0?1:0);	// ������ �� ����
		endPage=startPage+pageBlock -1;						// �Խñ� �� ��ȣ
		if(endPage > pageCount) endPage = pageCount;		// ������ �������� ���
		
		
		/*--------------------------------*/
		
		
		// īƮ ���̺��� �ҷ��� 
		ArrayList<CartDTO> carts = new ArrayList<>();
		
		carts = cart.getcart(start, end);			// ū �ٱ��Ͽ� ������ŭ ���� ����
		
		
		// ���� ���� ���� : �� 6��
		// m_num / b_num / start / ordernum / state / pageNum
		
		// �ѱ�� ���� : �⺻ 
		req.setAttribute("cnt", cnt);			// �Խù� ����
		req.setAttribute("number", number);		// ���� ������ ��ȣ
		req.setAttribute("pageNum", pageNum);	// pageNum -> currentPage
		req.setAttribute("start", start);
		req.setAttribute("end", end);
		
		
		if(cnt >0 ){
			
			req.setAttribute("startPage", startPage);		// ���� ������
			req.setAttribute("endPage", endPage);			// �� ������
			req.setAttribute("pageBlock", pageBlock);		// �Խñ� ǥ�� ����
			req.setAttribute("pageCount", pageCount);		// ������ �� ����
			req.setAttribute("currentPage", currentPage);	// ���� ������ ��ȣ
			
			req.setAttribute("carts", carts);				// ū �ٱ��� �Ӽ� ����
			
			
		} else {
			System.out.println("  -> ��ٱ��Ͽ� ��� ������ �����ϴ�.");
		}
		
		
		// ���°��� ���� ���� ó��
		if ( req.getAttribute("quan_cnt") != null ){
			System.out.println("  _> quan_cnt : " + req.getAttribute("quan_cnt"));
			int quan_cnt = (Integer) req.getAttribute("quan_cnt");
			req.setAttribute("quan_cnt", quan_cnt);
		}
		
		if ( req.getAttribute("orderCnt") != null ){
			System.out.println("  _> orderCnt : " + req.getAttribute("orderCnt"));
			int orderCnt = (Integer) req.getAttribute("orderCnt");
			req.setAttribute("orderCnt", orderCnt);
		}
		
			
		if ( req.getAttribute("cartall") != null ){
			int cartall = (Integer) req.getAttribute("cartall");
			req.setAttribute("cartall", cartall);
		}
			
		
		
		return "/_Store/View/03_Order/01_Order_Cart.jsp"; 

	}


}
