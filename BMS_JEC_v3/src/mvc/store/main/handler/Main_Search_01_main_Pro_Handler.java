package mvc.store.main.handler;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.BoardDAO;
import mvc.store.dao.BoardDAOIpml;
import mvc.store.dao.BookDAO;
import mvc.store.dao.BookDAOIpml;
import mvc.store.dto.BookDTO;


public class Main_Search_01_main_Pro_Handler implements Command_Handler   {

	@Override
	public String mainexecute(HttpServletRequest req, HttpServletResponse res , String str ) {
		
		System.out.println("  -> Main_Serch_01_Pro_Handler ");
		
		int pageSize = 5, pageBlock = 5;	// �������� �� ���� / ������ ����
		int cnt=0, start=0, end=0, number=0; 
		int currentPage=0, pageCount=0, startPage=0, endPage=0;
		String pageNum=null;
		
		pageNum = req.getParameter("pageNum"); 
		if(pageNum==null) pageNum="1";
		
		/*-----------------------------------------------------*/
		
		BookDAO dao = BookDAOIpml.getInstance();
		str = req.getParameter("str"); 
		cnt = dao.searchCnt(str);
		
		
		/*-----------------------------------------------------*/

		
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
		req.setAttribute("str", str);
		
		
		if(cnt >0 ){
			req.setAttribute("startPage", startPage);		// ���� ������
			req.setAttribute("endPage", endPage);			// �� ������
			req.setAttribute("pageBlock", pageBlock);		// �Խñ� ǥ�� ����
			req.setAttribute("pageCount", pageCount);		// ������ �� ����
			req.setAttribute("currentPage", currentPage);	// ���� ������ ��ȣ
			
			
			ArrayList<BookDTO> dtos = new ArrayList<>();
			
			
			dtos = dao.booksearch(str);
			
			
			req.setAttribute("dtos", dtos);					// ū �ٱ��� �Ӽ� ����
			int n = cnt+1;
			req.setAttribute("n", n);
			
		} else {
			System.out.println("  -> ��ϵ� ���� ����");
		}
		
		return "/_Store/View/Main/01_Serch_Main.jsp";
	}

}
