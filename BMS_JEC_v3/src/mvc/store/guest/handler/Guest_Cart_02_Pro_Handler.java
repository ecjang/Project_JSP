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
		
		
		// 변수 받기 : 총 2개
		//  pageNum / start
		/*int start = Integer.parseInt(req.getParameter("start"));	// 페이지 번호*/	
		String pageNum = req.getParameter("pageNum");	
		if(pageNum==null) pageNum="1";	// 페이지 번호
		
		
		// 장바구니 목록 개수 불러오기
		BookDAO dao = BookDAOIpml.getInstance();
		int cnt = dao.getCount_cart();						// 목록 불러오기

		
		// 페이지 설정 
		int pageSize = 10, pageBlock = 3;	// 한페이지 글 개수 / 페이지 개수
		int end=0, number=0, start=0;
		int currentPage=0, pageCount=0, startPage=0, endPage=0;
		
		
		/*--------------------------------*/
		
		
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
		
		
		/*--------------------------------*/
		
		
		// 카트 테이블을 불러옴 
		BookDAO bdao = BookDAOIpml.getInstance();		// 데이터 조회 클래스
		ArrayList<BookDTO> carts = new ArrayList<>();
		
		
		carts = bdao.getcart(start, end);			// 큰 바구니에 범위만큼 값을 대입
		
		
		// 위에 받은 변수 : 총 6개
		// m_num / b_num / start / ordernum / state / pageNum
		
		// 넘기는 변수 : 기본 

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
			req.setAttribute("carts", carts);				// 큰 바구니 속성 설정
			
			
		} else {
			System.out.println("  -> 장바구니에 담긴 서적이 없습니다.");
		}
		
		return "/_Store/View/Guest/01_Guest_Cart.jsp"; 

	}


}
