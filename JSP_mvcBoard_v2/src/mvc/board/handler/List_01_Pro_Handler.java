package mvc.board.handler;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.board.dao.BoardDAO;
import mvc.board.dao.BoardDAOIpml;
import mvc.board.dto.BoardDTO;


public class List_01_Pro_Handler implements Command_Handler  {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		System.out.println("  -> list_01_Pro_Handler에서 execute실행  : 서비스 ");
		
		int pageSize = 10, pageBlock = 3;	// 한페이지 글 개수 / 페이지 개수
		int cnt=0, start=0, end=0, number=0; 
		int currentPage=0, pageCount=0, startPage=0, endPage=0;
		String pageNum=null;
		
		BoardDAO dao = BoardDAOIpml.getInstance();
		
		System.out.println("  -> getConut() 호출 ");
		cnt = dao.getCount();
		
		/*-----------------------------------------------------*/
		
		pageNum = req.getParameter("pageNum"); 
		if(pageNum==null) pageNum="1";
		
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
		
		
		if(cnt >0 ){
			req.setAttribute("startPage", startPage);		// 시작 페이지
			req.setAttribute("endPage", endPage);			// 끝 페이지
			req.setAttribute("pageBlock", pageBlock);		// 게시글 표시 개수
			req.setAttribute("pageCount", pageCount);		// 페이지 총 개수
			req.setAttribute("currentPage", currentPage);	// 현제 페이지 번호
			
			System.out.println("  -> getArticles() 호출 ");
			ArrayList<BoardDTO> dtos = new ArrayList<>();
			dtos = dao.getArticles(start, end);
			
			req.setAttribute("dtos", dtos);					// 큰 바구니 속성 설정
			
			int n = dao.getCount()+1;
			req.setAttribute("n", n);
			
		} else {
			System.out.println("  -> 작성된 게시물이 없음");
		}
		
		/*-----------------------------------------------------*/
		
		System.out.println("  -> 불러온 목록을 list.jsp에 출력 ");
		return "/board/view/list.jsp";
		
	}
	
}
