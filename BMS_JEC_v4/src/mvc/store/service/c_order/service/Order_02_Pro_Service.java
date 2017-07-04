package mvc.store.service.c_order.service;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.BoardDAO;
import mvc.store.dao.BoardDAOImpl;
import mvc.store.dao.BookDAO;
import mvc.store.dao.BookDAOImpl;
import mvc.store.dao.OrderDAO;
import mvc.store.dao.OrderDAOImpl;
import mvc.store.dto.BoardDTO;
import mvc.store.dto.BookDTO;
import mvc.store.dto.OrderDTO;
import mvc.store.service.Command_Handler;

public class Order_02_Pro_Service implements Command_Handler {

	@Override
	public String service(HttpServletRequest req, HttpServletResponse res) {
		
		System.out.println("  -> Order_02_Pro_Service");


		
		// ������ ���� 
		int pageSize = 10, pageBlock = 10;	// �������� �� ���� / ������ ����
		int cnt=0, start=0, end=0, number=0; 
		int currentPage=0, pageCount=0, startPage=0, endPage=0;
		String pageNum=null;
		
		
		// �ֹ� ��� �ҷ�����
		OrderDAO order = OrderDAOImpl.getInstance();
		cnt = order.getOrderCnt();
		
		
		/*-----------------------------------------------------*/
		
		pageNum = req.getParameter("pageNum"); 
		if(pageNum==null) pageNum="1";
		
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
		if(endPage > pageCount) endPage = pageCount;		// ������ �������� ���a
		
		/*-----------------------------------------------------*/
		
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
			
			ArrayList<OrderDTO> orders = new ArrayList<>();
			// getArticles �� �б⹮ �ۼ� / ������������ ������ ��������
			
			orders = order.getOrder(start, end);
			req.setAttribute("orders", orders);					// ū �ٱ��� �Ӽ� ����
			
			
			int sum = order.orderSum();
			req.setAttribute("sum", sum);
			
			/*
			int n = cnt+1;
			req.setAttribute("n", n);
			*/
			req.setAttribute("cnt",cnt);
			
			
		} else {
			System.out.println("  -> �ۼ��� �Խù��� ����");
		}
		
		
		if ( (Integer)req.getAttribute("order_cnt") != null ){
			int order_cnt = (Integer)req.getAttribute("order_cnt");
			req.setAttribute("order_cnt", order_cnt);
		}
		
		return "/_Store/View/03_Order/02_Order_Order.jsp"; 
		
		/*return "15_order_orderproview.do";*/ 
		
	}

}
