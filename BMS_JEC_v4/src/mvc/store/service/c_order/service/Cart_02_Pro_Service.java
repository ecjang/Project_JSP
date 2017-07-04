package mvc.store.service.c_order.service;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.dao.CartDAO;
import mvc.store.dao.CartDAOImpl;
import mvc.store.dto.CartDTO;
import mvc.store.service.Command_Handler;


public class Cart_02_Pro_Service implements Command_Handler {

	@Override
	public String service(HttpServletRequest req, HttpServletResponse res) {
		
		System.out.println("  -> Cart_02_Pro_Service");
		
		
		// 변수 받기 : 총 2개
		//  pageNum / start
		/*int start = Integer.parseInt(req.getParameter("start"));	// 페이지 번호*/	
		String pageNum = req.getParameter("pageNum");	
		if(pageNum==null) pageNum="1";	// 페이지 번호
		
		
		// 페이지 설정 
		int pageSize = 10;		// 한페이지 글 개수
		int pageBlock = 10;		// 페이지 개수
		int start=0;			// 게시물 시작 번호
		int end=0;				// 게심물 끝 번호
		int number=0; 			// 게시물 번호
		int currentPage=0;		// 현제 페이지 번호
		int pageCount=0;		// 페이지 전체 번호
		int startPage=0; 		// 시작 페이지 번호
		int endPage=0;			// 끝 페이지 번호
		
		
		
		// 장바구니 목록 개수 불러오기
		CartDAO cart = CartDAOImpl.getInstance();
		int cnt = cart.getCount_cart();						// 목록 불러오기

		
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
		ArrayList<CartDTO> carts = new ArrayList<>();
		
		carts = cart.getcart(start, end);			// 큰 바구니에 범위만큼 값을 대입
		
		
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
		
		
		// 상태값에 따른 변수 처리
		if ( req.getAttribute("quan_cnt") != null ){
			System.out.println("  _> quan_cnt : " + req.getAttribute("quan_cnt"));
			int quan_cnt = (Integer) req.getAttribute("quan_cnt");
			req.setAttribute("quan_cnt", quan_cnt);
		}
		
		if ( req.getAttribute("orderCnt") != null ){
			System.out.println("  _> orderCnt : " + req.getAttribute("orderCnt"));
			int orderCnt = (Integer) req.getAttribute("orderCnt");
			req.setAttribute("orderCnt", orderCnt);
		}
		
			
		if ( req.getAttribute("cartall") != null ){
			int cartall = (Integer) req.getAttribute("cartall");
			req.setAttribute("cartall", cartall);
		}
			
		
		
		return "/_Store/View/03_Order/01_Order_Cart.jsp"; 

	}


}
