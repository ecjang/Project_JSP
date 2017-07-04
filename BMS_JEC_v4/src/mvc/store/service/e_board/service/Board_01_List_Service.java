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
			/*System.out.println("  -> kind값 : " + kind);*/	// kind 값 테스트
		}
		
		int pageSize = 10;		// 한페이지 글 개수
		int pageBlock = 10;		// 페이지 개수
		int cnt=0;				// 카운트
		int start=0;			// 게시물 시작 번호
		int end=0;				// 게심물 끝 번호
		int number=0; 			// 게시물 번호
		int currentPage=0;		// 현제 페이지 번호
		int pageCount=0;		// 페이지 전체 번호
		int startPage=0; 		// 시작 페이지 번호
		int endPage=0;			// 끝 페이지 번호
		String pageNum=null;	// 페이지 번호 변수
		
		
		BoardDAO dao = BoardDAOImpl.getInstance();
		cnt = dao.getCount(kind);
		
		
		/*-----------------------------------------------------*/
		
		pageNum = req.getParameter("pageNum"); 
		if(pageNum==null || pageNum.equals("0") ) pageNum="1";
		
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
		
		return "/_Store/View/05_Board/01_Board_List.jsp"; 
		

		
	}

}
