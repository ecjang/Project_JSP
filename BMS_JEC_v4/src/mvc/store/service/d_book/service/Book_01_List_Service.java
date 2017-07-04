package mvc.store.service.d_book.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.BookDAO;
import mvc.store.dao.BookDAOImpl;
import mvc.store.dto.BookDTO;
import mvc.store.service.Command_Handler;

public class Book_01_List_Service implements Command_Handler {

	@Override
	public String service(HttpServletRequest req, HttpServletResponse res) {
		
		System.out.println("  -> Book_01_List_Service ");
		
		int pageSize = 10;		// �������� �� ����
		int pageBlock = 10;		// ������ ����
		int cnt=0;				// ī��Ʈ
		int start=0;			// �Խù� ���� ��ȣ
		int end=0;				// �Խɹ� �� ��ȣ
		int number=0; 			// �Խù� ��ȣ
		int currentPage=0;		// ���� ������ ��ȣ
		int pageCount=0;		// ������ ��ü ��ȣ
		int startPage=0; 		// ���� ������ ��ȣ
		int endPage=0;			// �� ������ ��ȣ
		String pageNum=null;	// ������ ��ȣ ����
		
		BookDAO dao = BookDAOImpl.getInstance();
		cnt = dao.BookCnt();
		
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
		if(endPage > pageCount) endPage = pageCount;		// ������ �������� ���
		
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
			
			
			ArrayList<BookDTO> dtos = new ArrayList<>();
			dtos = dao.getArticles(start, end);
			req.setAttribute("dtos", dtos);					// ū �ٱ��� �Ӽ� ����
			
			int n = cnt+1;
			req.setAttribute("n", n);
			req.setAttribute("b_num", 0);
			
		} else {
			System.out.println("  -> ��ϵ� ������ �����ϴ�.");
			
		}
		
		/*return "02_book_listmain.do"; */
		return "/_Store/View/04_Book/01_Book_List.jsp"; 
	}
}
