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
		
		int pageSize = 10, pageBlock = 10;	// 한페이지 글 개수 / 페이지 개수
		int cnt=0, start=0, end=0, number=0; 
		int currentPage=0, pageCount=0, startPage=0, endPage=0;
		String pageNum=null;
		
		BoardDAO dao = BoardDAOIpml.getInstance();
		System.out.println("  -> kind값 : " + kind);
		cnt = dao.getCount(kind);
		System.out.println("  -> 게시물 개수 : " + cnt);
		
		/*-----------------------------------------------------*/
		
		pageNum = req.getParameter("pageNum"); 
		System.out.println("pageNum : "  + pageNum);
		if(pageNum==null || pageNum.equals("0")) pageNum="1";
		
		currentPage = Integer.parseInt(pageNum);			// 현재 페이지 설정
		
		start = (currentPage-1)*pageSize +1;				// 게시글 시작번호
		end   = start+pageSize -1;	if(end>cnt) end=cnt;	// 게시글 끝번호
		number = cnt- (currentPage-1)*pageSize;				// 게시글  번호

		startPage=(currentPage/pageBlock)*pageBlock +1;		// 게시글 시작 번호
		if(currentPage%pageBlock==0){						// 게시글 번호가 페이지 블록과 같은 경우
			startPage = startPage-pageBlock ;
		}
		pageCount = (cnt/pageSize)+(cnt%pageSize>0?1:0);	// 페이지 총 개수
		endPage=startPage+pageBlock -1;						// 게시글 끝 번호
		if(endPage > pageCount) endPage = pageCount;		// 마지막 페이지인 경우
		
		/*-----------------------------------------------------*/
		
		req.setAttribute("cnt", cnt);			// 게시물 개수
		req.setAttribute("number", number);		// 현제 페이지 번호
		req.setAttribute("pageNum", pageNum);	// pageNum -> currentPage
		req.setAttribute("start", start);
		req.setAttribute("end", end);
		req.setAttribute("kind", kind);
		
		if(cnt >0 ){
			req.setAttribute("startPage", startPage);		// 시작 페이지
			req.setAttribute("endPage", endPage);			// 끝 페이지
			req.setAttribute("pageBlock", pageBlock);		// 게시글 표시 개수
			req.setAttribute("pageCount", pageCount);		// 페이지 총 개수
			req.setAttribute("currentPage", currentPage);	// 현제 페이지 번호
			
			ArrayList<BoardDTO> dtos = new ArrayList<>();
			// getArticles 를 분기문 작성 / 전페이지에서 변수를 설정하자
			
			dtos = dao.getArticles(start, end , kind);
			req.setAttribute("dtos", dtos);					// 큰 바구니 속성 설정
			
			int n = cnt+1;
			req.setAttribute("n", n);
			
		} else {
			System.out.println("  -> 작성된 게시물이 없음");
		}
		
		return "/_Store/View/Board/02_Board_List_Table.jsp"; 
		

		
	}

}
