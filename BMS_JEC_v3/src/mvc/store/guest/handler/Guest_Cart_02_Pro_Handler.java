package mvc.store.guest.handler;


import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.store.dao.BookDAO;
import mvc.store.dao.BookDAOIpml;
import mvc.store.dao.MemberDAO;
import mvc.store.dao.MemberDAOImpl;
import mvc.store.dto.BookDTO;


public class Guest_Cart_02_Pro_Handler implements Command_Handler {

	@Override
	public String orderexecute(HttpServletRequest req, HttpServletResponse res) {
		
		System.out.println("  -> Guest_Cart_02_Pro_Handler");
		
		
		// ���� �ޱ� : �� 2��
		//  pageNum / start
		/*int start = Integer.parseInt(req.getParameter("start"));	// ������ ��ȣ*/	
		String pageNum = req.getParameter("pageNum");	
		if(pageNum==null) pageNum="1";	// ������ ��ȣ
		
		
		// ��ٱ��� ��� ���� �ҷ�����
		BookDAO dao = BookDAOIpml.getInstance();
		int cnt = dao.getCount_cart();						// ��� �ҷ�����

		
		// ������ ���� 
		int pageSize = 10, pageBlock = 3;	// �������� �� ���� / ������ ����
		int end=0, number=0, start=0;
		int currentPage=0, pageCount=0, startPage=0, endPage=0;
		
		
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
		BookDAO bdao = BookDAOIpml.getInstance();		// ������ ��ȸ Ŭ����
		ArrayList<BookDTO> carts = new ArrayList<>();
		
		
		carts = bdao.getcart(start, end);			// ū �ٱ��Ͽ� ������ŭ ���� ����
		
		
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
		
		return "/_Store/View/Guest/01_Guest_Cart.jsp"; 

	}


}
