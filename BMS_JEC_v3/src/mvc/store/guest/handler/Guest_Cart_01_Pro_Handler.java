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


public class Guest_Cart_01_Pro_Handler implements Command_Handler {

	@Override
	public String orderexecute(HttpServletRequest req, HttpServletResponse res) {
		
		System.out.println("  -> Guest_Cart_01_Pro_Handler");
		
		// 변수 받기
		int b_num = 	Integer.parseInt( req.getParameter("b_num") 	);
		int start = 	Integer.parseInt( req.getParameter("start")		);
		int ordernum = 	Integer.parseInt( req.getParameter("ordernum") 	);
		
		int m_num = 	(Integer) req.getSession().getAttribute("m_num");
		
		/*String str = 	req.getParameter("str");*/
		
		String state = 	req.getParameter("state");
		String pageNum = req.getParameter("pageNum"); 
		if(pageNum==null) pageNum="1";
		
		
		
		/*--------------------------------*/
		
		BookDAO dao = BookDAOIpml.getInstance();
		int cnt = dao.getCount_cart();						// 목록 불러오기
		
		
		/*--------------------------------*/
		
		// 페이지 설정 
		
		int pageSize = cnt, pageBlock = 3;	// 한페이지 글 개수 / 페이지 개수
		int end=0, number=0 ; start=0;
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
			
			
			BookDAO bdao = BookDAOIpml.getInstance();
			BookDTO dto = new BookDTO();
			
			// 도서 정보 가져오기 
			
			dto = bdao.getArticles(b_num);
			
			// 수정 정보 대입
			dto.setB_num(b_num);	// 도서 번호
			dto.setM_num(m_num);	// 회원 번호
			dto.setQuan(ordernum);	// 주문 수량
			dto.setState(state);	// 도서 상태
			dto.setReg_date( new Timestamp(System.currentTimeMillis()) );	// 현재시간 설정
			
			// 서적을 장바구니에 담음
			int cart_cnt = bdao.bookstate(dto);
			
			// 장바구니 목록을 불러옴
			ArrayList<BookDTO> carts = new ArrayList<>();
			
			
			
			carts = bdao.getcart(start, end);
			
			
			
			req.setAttribute("carts", carts);			// 큰 바구니 속성 설정
			req.setAttribute("b_num", b_num);
			
			
			int n = cnt+1;
			req.setAttribute("n", n);
			
		} else {
			System.out.println("  -> 장바구니에 담긴 서적이 없습니다.");
		}
		
		return "/_Store/View/Guest/01_Guest_Cart.jsp"; 

	}


}
