package com.mybatis.bms.service.d_order;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.mybatis.bms.dao.c_book.BookDAO;
import com.mybatis.bms.dao.f_order.OrderDAO;
import com.mybatis.bms.dto.BookDTO;
import com.mybatis.bms.dto.OrderDTO;

@Service
public class OrderServiceImpl implements OrderService {
	
	
	@Inject
	BookDAO bookdao;
	
	@Inject
	OrderDAO order;


	@Override
	public void order_view(Model model) throws Exception {

		System.out.println("  -> Order_View_Service ");
		
		Map<String,Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");

		
		int cnt = 0;		// 카운트
		int state_cnt = 0;	// 상태값
		
		int start = 0;		// 시작번호 
		int m_num = 0;		// 회원번호
		int b_num = 0;		// 서적번호
		int o_num = 0;		// 주문 번호
		int c_num = 0;		// 주문 번호
		int quan = 0;		// 주문 수량
		String state = null;	// 도서 상태
		
	
		m_num = (Integer)req.getSession().getAttribute("m_num"); 
		System.out.println("    : 로그인한 회원번호 : " + m_num );
		
		BookDTO dto = new BookDTO();					
		
		
		// 도서를 주문목록에 담는 경우
		if(req.getParameter("b_num") != null){	
			 
			start 	= Integer.parseInt( req.getParameter("start") 	);		// 책번호
			b_num 	= Integer.parseInt( req.getParameter("b_num") 	);		// 책번호
			quan 	= Integer.parseInt( req.getParameter("quan") 	);		// 주문수량 
			state = "ORDER";
			
			// 도서 정보 가져오기
			dto = bookdao.getbook(b_num);
			
			dto.setB_num(b_num);	// 도서 번호
			dto.setM_num(m_num);	// 회원 번호
			dto.setQuan(quan);		// 주문 수량
			dto.setState(state);	// 도서 상태
			dto.setReg_date( new Timestamp(System.currentTimeMillis()) );	
			 	
			
			
			state_cnt = order.change_orderstate_01(dto);
			System.out.println("  -> state_cnt step1 : " + state_cnt);
			

			state_cnt = order.change_orderstate_02(dto);
			System.out.println("  -> state_cnt step2 : " + state_cnt);
			

			state_cnt = order.change_orderstate_03(dto);
			System.out.println("  -> state_cnt step3 : " + state_cnt);
			
			
			System.out.println("  -> state_cnt 값 : " + state_cnt);
			System.out.println("  -> [ 1:정상처리 / 2:삭제오류 / 3:재고수량이 적음  / 4:장바구니 입력 오류 / 0:재고없음 ] ");
			
			
			// state_cnt가 1일 때만 진행
			if(state_cnt == 1){
				
				if(req.getParameter("o_num") != null){
					
					o_num = Integer.parseInt( req.getParameter("o_num") );	// 주문번호
					quan = 	Integer.parseInt( req.getParameter("ordernum") 	);	// 주문수량
					
					model.addAttribute("o_num", o_num);
					model.addAttribute("quan", quan);
					model.addAttribute("state_cnt", state_cnt);
				}
			}
		}

	}

	
	@Override
	public void order_pro(Model model) throws Exception {
		
		System.out.println("  -> Order_Pro_Service");
		
		Map<String,Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		
		// 페이지 설정 
		int pageSize = 10, pageBlock = 10;	// 한페이지 글 개수 / 페이지 개수
		int cnt=0, start=0, end=0, number=0; 
		int currentPage=0, pageCount=0, startPage=0, endPage=0;
		String pageNum=null;
		
		
		// 주문 목록 불러오기
		cnt = order.cnt_order();
		System.out.println("  -> cnt 값 : " + cnt);
		
		
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
		if(endPage > pageCount) endPage = pageCount;		// 마지막 페이지인 경우a
		
		
		
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
			
			List<OrderDTO> orders;
			
			
			// getArticles 를 분기문 작성 / 전페이지에서 변수를 설정하자
			
			Map<String, Object> daoMap = new HashMap<>();
			daoMap.put("start", start);
			daoMap.put("end", end);
			
			orders = order.get_orders(daoMap);
			
			model.addAttribute("orders", orders);					// 큰 바구니 속성 설정
			
			int sum = order.sum_order();
			model.addAttribute("sum", sum);
			
			/*
			int n = cnt+1;
			model.addAttribute("n", n);
			*/
			model.addAttribute("cnt",cnt);
			
			
		} else {
			System.out.println("  -> 작성된 게시물이 없음");
		}
		
		if ( (Integer)req.getAttribute("order_cnt") != null ){
			int order_cnt = (Integer)req.getAttribute("order_cnt");
			model.addAttribute("order_cnt", order_cnt);
		}
	}


	
	@Override
	public void order_state(Model model) throws Exception {

		System.out.println("  -> Order_State_Service");
		
		Map<String,Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		
		
		// 주문 상태 변환
		int o_num = Integer.parseInt( req.getParameter("o_num") );
		String state = req.getParameter("state");
		
		System.out.println( "  -> o_num 값 : " + o_num);
		System.out.println( "  -> state 값 : " + state);
		
		int cnt = 0;
		
		
		Map<String,Object> daoMap = new HashMap<>();
		
		daoMap.put("state",state);
		daoMap.put("o_num",o_num);

		// ORDER_CANCEL 에서 SALE로 변경시 도서목록에 되돌림
		if( state.equals("SALE") ){
			
			int quan = order.input_order_01(o_num);
			
			if( quan > 0 ){
				
				System.out.println("    : 주문량 : " + quan);
				daoMap.put("quan",quan);
			
				// quan 만큼 도서재고에 반환
				cnt = order.input_order_02(daoMap);
				
				if ( cnt > 0 ){
					
					System.out.println("    : 도서 재고로 반환 완료");
					
					cnt = order.input_order_03( o_num);
					
					if ( cnt > 0 ){
						
						System.out.println("    : 주문삭제 완료");
						cnt = 1;
						
					} else {
						
						System.out.println("    : 주문삭제중 오류 발생");
						cnt= 2;
					}
					
				} else { 
				
					System.out.println("    : 도서로 이동중 오류발생");
					cnt = 3;
				}
				
			} else {  // 주문 수량이 없을 때
				
				System.out.println("    : 주문량이 없음 ");
				cnt= 4;
			}
			
			
		} else {
			
			cnt = order.input_order_04(daoMap);
			
			if (cnt>0){
				System.out.println("    : 상태값 변경 완료");
				cnt = 1;
			
			} else {
				System.out.println("    : 상태값 변경 중 오류발생");
				cnt = 0;
			}
			
		}
			
		model.addAttribute("order_cnt", cnt);
		
		
	}
		
	
	
}
