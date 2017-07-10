package com.mybatis.bms.service.c_cart;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.mybatis.bms.dao.c_book.BookDAO;
import com.mybatis.bms.dao.e_cart.CartDAO;
import com.mybatis.bms.dto.BookDTO;
import com.mybatis.bms.dto.CartDTO;

@Service
public class CartServiceImpl implements CartService {
	
	@Inject
	private CartDAO cart;
	
	@Inject
	private BookDAO book;
	

	@Override
	public void cart_list_view(Model model) throws Exception {
		
		System.out.println("  -> Cart_View_Service ");
		
		Map<String,Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		
		// 장바구니에 접속할 경우
		int cnt = 0;		// 카운트
		int state_cnt = 0;	// 상태값
		
		int start = 0;		// 시작번호
		int m_num = 0;		// 회원번호
		int b_num = 0;		// 서적번호
		int c_num = 0;		// 장바구니 번호
		int quan = 0;		// 장바구니 주문수량
		String state = null;	// 도서 상태
		
		m_num = (Integer)req.getSession().getAttribute("m_num"); 
		System.out.println("    : 로그인한 회원번호 : " + m_num );
		
		
		// 도서 dto 생성
		BookDTO dto = new BookDTO();					
		
		// 도서를 장바구니에 넣을 경우
		if(req.getParameter("b_num") != null){
			
			start 	= Integer.parseInt( req.getParameter("start") 	);		// 책번호
			b_num 	= Integer.parseInt( req.getParameter("b_num") 	);		// 책번호
			quan 	= Integer.parseInt( req.getParameter("quan") 	);		// 주문수량 */				
			state = "CART";
			
			// 도서 정보 가져오기
			dto = book.getbook(b_num);
			
			dto.setB_num(b_num);	// 도서 번호
			dto.setM_num(m_num);	// 회원 번호
			dto.setQuan(quan);		// 주문 수량
			dto.setState(state);	// 도서 상태
			dto.setReg_date( new Timestamp(System.currentTimeMillis()) );	
	
			 
			
			state_cnt = cart.insert_cart_01(dto);
			System.out.println("state_cnt step1 : " + state_cnt);
			
			state_cnt = cart.insert_cart_02(dto);
			System.out.println("state_cnt step2 : " + state_cnt);
			
			state_cnt = cart.insert_cart_03(dto);
			System.out.println("state_cnt step3 : " + state_cnt);
			
			
			
			System.out.println("  -> state_cnt 값 : " + state_cnt);
			System.out.println("  -> [ 1:정상처리 / 2:삭제오류 / 3:재고수량이 적음  / 4:장바구니 입력 오류 / 0:재고없음 ] ");
			
			
			// state_cnt가 1일 때만 진행
			if(state_cnt == 1){
				
				if(req.getParameter("c_num") != null){
					
					c_num = Integer.parseInt( req.getParameter("c_num") );	// 카트번호
					quan = 	Integer.parseInt( req.getParameter("ordernum") 	);	// 주문수량
					
					model.addAttribute("c_num", c_num);
					model.addAttribute("quan", quan);
					model.addAttribute("state_cnt", state_cnt);
				}
			}

		}
		
		// 변수 넘김
		model.addAttribute("b_num", b_num);
		model.addAttribute("quan",quan);
		model.addAttribute("pageNum", req.getParameter("param.pageNum"));
		model.addAttribute("state_cnt", state_cnt);
		
	}

	
	@Override
	public void cart_list_pro(Model model) throws Exception {
		
		System.out.println("  -> Cart_Pro_Service");
		
		Map<String,Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		
		
		// 변수 받기
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
		int cnt = cart.cnt_cart();				// 목록 불러오기
		System.out.println("  -> cnt 값 " + cnt);
		
		
		
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
		
		
		
		// 카트 테이블을 불러옴 
		List<CartDTO> carts = null;
		
		Map<String,Object> daoMap = new HashMap<>();
		daoMap.put("start", start);
		daoMap.put("end", end);
		
		carts = cart.get_carts(daoMap);			// 큰 바구니에 범위만큼 값을 대입
		
		
		// 위에 받은 변수 : 총 6개
		// m_num / b_num / start / ordernum / state / pageNum
		
		// 넘기는 변수 : 기본 
				
		model.addAttribute("cnt", cnt);			// 게시물 개수
		model.addAttribute("number", number);		// 현제 페이지 번호
		model.addAttribute("pageNum", pageNum);	// pageNum -> currentPage
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		
		
		if(cnt >0 ){
			
			model.addAttribute("startPage", startPage);		// 시작 페이지
			model.addAttribute("endPage", endPage);			// 끝 페이지
			model.addAttribute("pageBlock", pageBlock);		// 게시글 표시 개수
			model.addAttribute("pageCount", pageCount);		// 페이지 총 개수
			model.addAttribute("currentPage", currentPage);	// 현제 페이지 번호
			
			model.addAttribute("carts", carts);				// 큰 바구니 속성 설정
			
			
		} else {
			System.out.println("  -> 장바구니에 담긴 서적이 없습니다.");
		}
		
		
		// 상태값에 따른 변수 처리
		if ( req.getAttribute("quan_cnt") != null ){
			System.out.println("  _> quan_cnt : " + req.getAttribute("quan_cnt"));
			int quan_cnt = (Integer) req.getAttribute("quan_cnt");
			model.addAttribute("quan_cnt", quan_cnt);
		}
		
		if ( req.getAttribute("orderCnt") != null ){
			System.out.println("  _> orderCnt : " + req.getAttribute("orderCnt"));
			int orderCnt = (Integer) req.getAttribute("orderCnt");
			model.addAttribute("orderCnt", orderCnt);
		}
		
			
		if ( req.getAttribute("cartall") != null ){
			int cartall = (Integer) req.getAttribute("cartall");
			model.addAttribute("cartall", cartall);
		}
	}


	
	
	@Override
	public void cart_cart_cal(Model model) throws Exception {

		System.out.println("  -> Cart_Cartnum_Service");
		
		Map<String,Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
	
		int quan=0;
		int c_num=0;
		int b_num=0;
		int quan_cnt=0;
		
		
		// 변수 받기
		if ( req.getParameter("quan") != null ){
			quan = Integer.parseInt(req.getParameter("quan"));
			c_num = Integer.parseInt(req.getParameter("c_num"));
			b_num = Integer.parseInt(req.getParameter("b_num"));
		}
		
		/*
		System.out.println("ordernum 값  : " + ordernum);
		System.out.println("b_num 값  : " + b_num);
		System.out.println("c_num 값  : " + c_num);
		*/

		
		// 재고 파악 
		int book_num = cart.cnt_bookquan(b_num);
		
		// 주문 수량 파악 
		int cart_num = cart.cnt_cartquan(c_num);
		
		
		int cart_quan = 0;
		int quan_abs = 0;
		
		// 주문수량이 재고보다 작아야 가능
		if ( quan <= book_num ){
			
			quan_abs = Math.abs(cart_num-quan);
			
			Map<String,Object> daoMap = new HashMap<>();
			daoMap.put("quan_abs",quan_abs);
			daoMap.put("c_num",c_num);
			
			if ( cart_num > quan ){
				
				System.out.println("  -> 주문 수량 감소 : 재고 추가");
				
				cart_quan = cart.quanminus_cart_01(daoMap);
				cart_quan = cart.quanminus_cart_02(daoMap);
				
				
				
			} else if( cart_num < quan ){
				
				System.out.println("  -> 주문 수량 증가 : 재고 감소");
				
				cart_quan = cart.quanplus_cart_01(daoMap);
				cart_quan = cart.quanplus_cart_02(daoMap);
				
			} else {
				System.out.println("  -> 주문 수량 변동 없음");
				cart_quan = -1;
			}
			
			System.out.println("  -> cart_quan 값 : " + cart_quan );
			
			
		} else {
			quan_cnt = 0;
		}
		model.addAttribute("cart_quan", cart_quan);
		model.addAttribute("book_num", book_num);
		}


	
	
	@Override
	public void cart_delete(Model model) throws Exception {
		System.out.println("  -> Cart_Cartdel_Service");

		Map<String,Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
	
		// 변수 받기
		int c_num =  Integer.parseInt(req.getParameter("c_num")) ;
	
		// 수량 조절 
		int cnt = cart.delete_cart_byc_num(c_num);
		
		model.addAttribute("cnt", cnt);
		
	}


	
	
	@Override
	public void cart_toorder(Model model) throws Exception {

		System.out.println("  -> Cart_Order_Service");
		
		Map<String,Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		
		// 변수 받기
			int quan = Integer.parseInt(req.getParameter("quan")) ;
			int c_num =  Integer.parseInt(req.getParameter("c_num")) ;
			int orderCnt =0;
		

			// 정보 불러오기
			Map<String,Object> daoMap = new HashMap<>();
			daoMap.put("c_num",c_num);
			daoMap.put("quan",quan);
			
			CartDTO cartdto = cart.get_cart( daoMap );
			
			
			// 정보 대입하기
			orderCnt = cart.insert_order(cartdto);
			
			
			// 주문목록으로 이동 성공시
			if ( orderCnt > 0 ){
				
				// 장바구니 목록 삭제
				cart.delete_cart_byc_num(c_num);
				
			}
			
			model.addAttribute("orderCnt", orderCnt);
			System.out.println("  -> orderCnt : " + orderCnt);
			
			
		
	}


	
	
	@Override
	public void cart_allorder(Model model) throws Exception {
		
		System.out.println("  -> Cart_Allorder_Service");
		
		Map<String,Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		
		int cartall = Integer.parseInt(req.getParameter("cartall"));
		
		// 변수 받기 : 회원번호
		int m_num = (Integer)req.getSession().getAttribute("m_num");
		
		
		if(cartall == 0){
			// 카트 정보 불러오기 -> 리스트에 담기
			List<CartDTO> carts = cart.getall_cart( m_num );
			
			for( int i=0; i<carts.size() ; i++ ){
				/*System.out.println("  -> " + carts.get(i).toString() );*/
				
				cartall += cart.allinsert_order( carts.get(i) );
				
			}
			
			// 주문 테이블에 담기
			System.out.println("다담았다!");
		}
		
		// 카트 테이블 비우기 id 검색
		if ( cartall > 0 ) {
			
			cart.delete_cart_bym_num(m_num);
			
		}
		
		model.addAttribute("cartall", cartall);
		
		
	}
	
	
	
}
