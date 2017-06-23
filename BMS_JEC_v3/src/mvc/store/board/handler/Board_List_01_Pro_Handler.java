package mvc.store.board.handler;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.BoardDAO;
import mvc.store.dao.BoardDAOIpml;
import mvc.store.dto.BoardDTO;
import mvc.store.dto.BookDTO;

public class Board_List_01_Pro_Handler implements Command_Handler {

	@Override
	public String write(HttpServletRequest req, HttpServletResponse res , String kind) {
		
	System.out.println("  -> Board_List_01_Pro_Handler ");
		
		int pageSize = 10, pageBlock = 10;	// �������� �� ���� / ������ ����
		int cnt=0, start=0, end=0, number=0; 
		int currentPage=0, pageCount=0, startPage=0, endPage=0;
		String pageNum=null;
		
		BoardDAO dao = BoardDAOIpml.getInstance();
		System.out.println("  -> kind�� : " + kind);
		cnt = dao.getCount(kind);
		System.out.println("  -> �Խù� ���� : " + cnt);
		
		/*-----------------------------------------------------*/
		
		pageNum = req.getParameter("pageNum"); 
		System.out.println("pageNum : "  + pageNum);
		if(pageNum==null || pageNum.equals("0")) pageNum="1";
		
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
		req.setAttribute("kind", kind);
		
		if(cnt >0 ){
			req.setAttribute("startPage", startPage);		// ���� ������
			req.setAttribute("endPage", endPage);			// �� ������
			req.setAttribute("pageBlock", pageBlock);		// �Խñ� ǥ�� ����
			req.setAttribute("pageCount", pageCount);		// ������ �� ����
			req.setAttribute("currentPage", currentPage);	// ���� ������ ��ȣ
			
			ArrayList<BoardDTO> dtos = new ArrayList<>();
			// getArticles �� �б⹮ �ۼ� / ������������ ������ ��������
			
			dtos = dao.getArticles(start, end , kind);
			req.setAttribute("dtos", dtos);					// ū �ٱ��� �Ӽ� ����
			
			int n = cnt+1;
			req.setAttribute("n", n);
			
		} else {
			System.out.println("  -> �ۼ��� �Խù��� ����");
		}
		
		return "/_Store/View/Board/02_Board_List_Table.jsp"; 
		

		
	}

}
