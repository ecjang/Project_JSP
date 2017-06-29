package mvc.store.service.c_order.handler;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.BoardDAO;
import mvc.store.dao.BoardDAOIpml;
import mvc.store.dao.BookDAO;
import mvc.store.dao.BookDAOIpml;
import mvc.store.dto.BoardDTO;
import mvc.store.dto.BookDTO;

public class Guest_Order_01_View_Handler implements Command_Handler {

	@Override
	public String orderexecute(HttpServletRequest req, HttpServletResponse res) {
		
		System.out.println("  -> Guest_Order_01_View_Handler");

		int pageSize = 10, pageBlock = 10;	// 한페이지 글 개수 / 페이지 개수
		int cnt=0, start=0, end=0, number=0; 
		int currentPage=0, pageCount=0, startPage=0, endPage=0;
		String pageNum=null;
		
		BookDAO dao = BookDAOIpml.getInstance();
		cnt = dao.getOrderCount();
		
		
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
			
			ArrayList<BookDTO> dtos = new ArrayList<>();
			// getArticles 를 분기문 작성 / 전페이지에서 변수를 설정하자
			
			dtos = dao.getorder(start, end);
			req.setAttribute("dtos", dtos);					// 큰 바구니 속성 설정
			
			
			
			int sum = dao.sum();
			req.setAttribute("sum", sum);
			
			/*
			int n = cnt+1;
			req.setAttribute("n", n);
			*/
			req.setAttribute("cnt",cnt);
			
			
		} else {
			System.out.println("  -> 작성된 게시물이 없음");
		}
		
		return "/_Store/View/Guest/02_Guest_Order.jsp"; 
		
		
	}

}
