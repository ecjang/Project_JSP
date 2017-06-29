package mvc.store.service.a_main.handler;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.BoardDAO;
import mvc.store.dao.BoardDAOIpml;
import mvc.store.dao.BookDAO;
import mvc.store.dao.BookDAOIpml;
import mvc.store.dto.BookDTO;
import mvc.store.service.Command_Handler;


public class Main_01_Serch_Service implements Command_Handler   {

	@Override
	public String service(HttpServletRequest req, HttpServletResponse res ) {
		
		System.out.println(" -> Main_01_Serch_Service ");
		
		int pageSize = 10, pageBlock = 5;	// �������� �� ���� / ������ ����
		int cnt=0, start=0, end=0, number=0; 
		int currentPage=0, pageCount=0, startPage=0, endPage=0;
		String pageNum=null;
		
		pageNum = req.getParameter("pageNum"); 
		if(pageNum==null) pageNum="1";
		
		/*-----------------------------------------------------*/
		
		
		BookDAO dao = BookDAOIpml.getInstance();
		String str = req.getParameter("str");
		
		// �˻�� ������ �Ű� ��� 10�� ���
		if( str==null ){
			cnt=8;
		// �˻�� ������ �������� ��ȸ
		} else{
			cnt = dao.searchCnt(str);
		}
		
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
		req.setAttribute("start", start);		// ���������� ���۹�ȣ
		req.setAttribute("end", end);			// ���������� ����ȣ
		req.setAttribute("str", str);
		
		
		if(cnt >0 ){
			req.setAttribute("startPage", startPage);		// ���� ������
			req.setAttribute("endPage", endPage);			// �� ������
			req.setAttribute("pageBlock", pageBlock);		// �Խñ� ǥ�� ����
			req.setAttribute("pageCount", pageCount);		// ������ �� ����
			req.setAttribute("currentPage", currentPage);	// ���� ������ ��ȣ
			
			
			ArrayList<BookDTO> dtos = new ArrayList<>();

			// �˻�� ������ �Ű� ��� 10�� ���
			if( str==null ){
				// �Ű� ���� 10�� ���
				dtos = dao.NewBookCnt(cnt);
				
			// �˻�� ������ �������� ��ȸ
			} else{
				// �˻���� �� ����Ʈ ����
				dtos = dao.BookSearch(str,start,end);	
			}
			
			req.setAttribute("dtos", dtos);					// ū �ٱ��� �Ӽ� ����
			int n = cnt+1;
			req.setAttribute("n", n);
			
		} else {
			System.out.println("  -> ��ϵ� ���� ����");
		}
		
		return "/_Store/View/01_Main/01_Main_Serch.jsp";
	}

}
