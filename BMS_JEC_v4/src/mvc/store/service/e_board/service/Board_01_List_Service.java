package mvc.store.service.e_board.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.BoardDAO;
import mvc.store.dao.BoardDAOImpl;
import mvc.store.dto.BoardDTO;
import mvc.store.dto.BookDTO;
import mvc.store.service.Command_Handler;

public class Board_01_List_Service implements Command_Handler {

	@Override
	public String service(HttpServletRequest req, HttpServletResponse res ) {
		
	System.out.println("  -> Board_01_List_Service ");
		
		String kind=" ";
		
		if(req.getAttribute("kind") != null){
			kind = (String) req.getAttribute("kind");
			
			if( req.getParameter("kind") != null ){
				kind = (String) req.getParameter("kind");
			}
			/*System.out.println("  -> kind�� : " + kind);*/	// kind �� �׽�Ʈ
		}
		
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
		
		
		BoardDAO dao = BoardDAOImpl.getInstance();
		cnt = dao.getCount(kind);
		
		
		/*-----------------------------------------------------*/
		
		pageNum = req.getParameter("pageNum"); 
		if(pageNum==null || pageNum.equals("0") ) pageNum="1";
		
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
		
		return "/_Store/View/05_Board/01_Board_List.jsp"; 
		

		
	}

}
