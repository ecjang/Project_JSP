package com.mybatis.bms.service.a_main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.mybatis.bms.dao.a_main.MainDAO;
import com.mybatis.bms.dto.BookDTO;

@Service
public class MainServiceImpl implements MainService  {

	@Inject
	MainDAO dao;
	
	@Override
	public void main_serch(HttpServletRequest req , Model model) throws Exception {

		System.out.println(" -> Main_01_Search_Service ");
		
		
		int pageSize	= 10;	
		int pageBlock	= 5;	
		
		int cnt 		= 0;	
		int start 		= 0;	
		int end			= 0;	
		int number		= 0;
		String pageNum	= null;	
		int currentPage	= 0;	
		
		int pageCount	= 0;	
		int startPage	= 0;	
		int endPage		= 0;	
		
		
		// 검색어가 있으면 해당 검색어로 조회
		String str = req.getParameter("str");
		System.out.println("  -> str 값  : " + str );
		
		if( str == null ){
			cnt=8;
			
		} else {
			
			cnt = dao.cnt_search(str);
		}
		
		System.out.println("  -> 검색 개수  : " + cnt + "개");

		// 게시물 페이지 계산 
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
		
		
		// 변수값들 리턴
		model.addAttribute("cnt", cnt);			// 게시물 개수
		model.addAttribute("number", number);		// 현제 페이지 번호
		model.addAttribute("pageNum", pageNum);	// pageNum -> currentPage
		model.addAttribute("start", start);		// 현제페이지 시작번호
		model.addAttribute("end", end);			// 현제페이지 끝번호
		model.addAttribute("str", str);
		
		
		if(cnt >0 ){
			model.addAttribute("startPage", startPage);		// 시작 페이지
			model.addAttribute("endPage", endPage);			// 끝 페이지
			model.addAttribute("pageBlock", pageBlock);		// 게시글 표시 개수
			model.addAttribute("pageCount", pageCount);		// 페이지 총 개수
			model.addAttribute("currentPage", currentPage);	// 현제 페이지 번호
			
			
			List<BookDTO> dtos = null;

			// 검색어가 없으면 신간 목록 출력
			if( str == null ){
				dtos = dao.get_newbooks(cnt);

			// 검색어가 있으면 도서수량 조회
			} else{
				
				Map<String,Object> daoMap = new HashMap<String,Object>();
				
				daoMap.put("start",start);
				daoMap.put("end",end);
				daoMap.put("str",str);
				
				dtos = dao.get_searchbooks( daoMap );	
			}
			
			// 큰 바구니 속성 설정
			model.addAttribute("dtos", dtos);					
			
			// 임시 번호 생성 
			int n = cnt+1;
			model.addAttribute("n", n);
			
		} else {
			System.out.println("  -> 등록된 서적 없음");
		}
		
		
	}
	
	@Override
	public void main_detail(HttpServletRequest req, Model model) throws Exception {
		
		System.out.println("  -> Main_02_Detail_Service ");
		
		// 변수 받기 
		int b_num = Integer.parseInt( 	req.getParameter("b_num") );	// 도서 조회용 책 코드 번호
		int pageNum = Integer.parseInt( req.getParameter("pageNum") );	// 검색페이지 페이지 번호
		int start = Integer.parseInt( 	req.getParameter("start") );	// 검색페이지 순서 번호
		String str = req.getParameter("str");							// 검색페이지 검색어
		
		
		/*System.out.println(b_num+pageNum+start+str);*/
		
		
		// 생성자 
		BookDTO dto = new BookDTO();
		
		dto = dao.get_book(b_num);
		
		model.addAttribute("dto", dto);
		model.addAttribute("b_num", b_num);
		model.addAttribute("start", start);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("str", str);
		
	}
	
}
