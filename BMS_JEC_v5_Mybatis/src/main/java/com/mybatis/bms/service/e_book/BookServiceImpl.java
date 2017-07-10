package com.mybatis.bms.service.e_book;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.mybatis.bms.dao.c_book.BookDAO;
import com.mybatis.bms.dto.BookDTO;

@Service
public class BookServiceImpl implements BookService {

	@Inject
	BookDAO dao;
	
	@Override
	public void book_list(Model model) throws Exception {
		
		Map<String,Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		
		System.out.println("  -> Book_01_List_Service ");
		
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
		
		cnt = dao.cnt_book();
		System.out.println("  -> cnt 값 : " + cnt);
		
		
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
			
			
			ArrayList<BookDTO> dtos = null;
			
			Map<String,Object> daoMap = new HashMap<String,Object>();
			daoMap.put("start",start);
			daoMap.put("end",end);
			dtos = dao.get_books( daoMap );
			model.addAttribute("dtos", dtos);					// 큰 바구니 속성 설정
			
			int n = cnt+1;
			model.addAttribute("n", n);
			model.addAttribute("b_num", 0);
			
		} else {
			System.out.println("  -> 등록된 서적이 없습니다.");
			
		}
		
		
		
	}

	
	@Override
	public void book_detail(Model model) throws Exception {

		System.out.println("  -> Book_Detail_Service ");
		
		Map<String,Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		
		int b_num = Integer.parseInt( req.getParameter("b_num") );
		int number = Integer.parseInt( req.getParameter("number") );
		int pageNum = Integer.parseInt( req.getParameter("pageNum") );
		
		BookDTO dto = new BookDTO();
		
		dto = dao.getbook(b_num);
		
		model.addAttribute("dto", dto);
		model.addAttribute("b_num", b_num);
		model.addAttribute("number", number);
		model.addAttribute("pageNum", pageNum);
		
		
		/* ??????
		model.addAttribute("b_num", 0);
		*/
		
		
		/*
		System.out.println("  -> b_num의 값 " + b_num);
		System.out.println("  -> number의 값 " + number);
		System.out.println("  -> pageNum의 값 " + pageNum);
		*/
	
		
	}

	
	@Override
	public void book_write_form(Model model) throws Exception {

		System.out.println("  -> Book_Write_Form_Service ");
		/*
		Map<String,Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		*/
		int b_num=0;
		int pageNum =0;
		
		/* ??????
		if( req.getAttribute("amincnt")!=null ){
			
			int amincnt = (Integer) req.getAttribute("amincnt");
			if( amincnt != 1 ){
				b_num = Integer.parseInt( req.getParameter("b_num") );
				pageNum = Integer.parseInt( req.getParameter("pageNum") );
			}
		
		}
		*/
		
		
		System.out.println("  -> b_num값 : " + b_num);
		
		
		int n = dao.cnt_book()+1;	// <- 추가할거
		System.out.println("  -> 임시 번호 생성 : " + n);
		
		model.addAttribute("n", n);
		model.addAttribute("b_num", b_num);
		model.addAttribute("pageNum", pageNum);
		
	}


	@Override
	public void book_write_pro(Model model) throws Exception {
		
		System.out.println("  -> Book_04_Write_Pro_Service ");
		
		Map<String,Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		
		int m_num = (Integer) req.getSession().getAttribute("m_num");
		
		BookDTO dto = new BookDTO();
		
		dto.setB_num( Integer.parseInt(req.getParameter("b_num")));
		dto.setM_num( m_num );
		dto.setTitle( req.getParameter("b_title") );
		dto.setSubtitle( req.getParameter("b_subtitle") );
		dto.setAuthor(req.getParameter("b_author") );
		dto.setPrice( Integer.parseInt(req.getParameter("b_price")));
		dto.setQuan( Integer.parseInt(req.getParameter("b_quan")));
		dto.setReg_date(new Timestamp( System.currentTimeMillis()));
		dto.setKind( req.getParameter("b_kind"));
		dto.setState("SALEABLE");
		
		/*System.out.println( "  -> 입력받은 값들을 확인 \n "+ dto.toString() );*/
		
		int cnt = dao.insert_book(dto);
		
		System.out.println("  -> cnt값 : " + cnt);
		
		model.addAttribute("cnt",cnt);
		
	}
	
	
	@Override
	public void book_delete_pro(Model model) throws Exception {
		
		System.out.println("  -> Book_Delete_Pro_Service ");
		
		Map<String,Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		
		int b_num = Integer.parseInt( req.getParameter("b_num") );
		int pageNum = Integer.parseInt( req.getParameter("pageNum") );
		
		int cnt = dao.delete_book(b_num);
		
		model.addAttribute("cnt", cnt);
		System.out.println("  -> cnt값 :" + cnt);
		
		model.addAttribute("cnt", cnt);
		model.addAttribute("pageNum", pageNum);
		
	}

	
	@Override
	public void book_modify_form(Model model) throws Exception {
		
		System.out.println("  -> Modify_From_Handler");
		
		Map<String,Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		
		int n = 0;
		int b_num	= Integer.parseInt(req.getParameter("b_num"));
		int pageNum	= Integer.parseInt(req.getParameter("pageNum"));
		
		BookDTO dto = new BookDTO();
		
		dto = dao.getbook(b_num);
		model.addAttribute("dto", dto);
		
		n = dao.cnt_book();
		model.addAttribute("n", n);
		System.out.println( "  -> 임시번호 설정 : " + n );
		
		model.addAttribute("num", b_num);
		model.addAttribute("pageNum", pageNum);
		
		
	}


	@Override
	public void book_modify_pro(Model model) throws Exception {

		System.out.println("  -> Book_Modify_Pro_Service ");
		
		Map<String,Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		
		int b_num 	= Integer.parseInt( req.getParameter("b_num") 	);
		int pageNum = Integer.parseInt( req.getParameter("pageNum") );
	
		String title = req.getParameter("b_title");
		String subtitle =  req.getParameter("b_subtitle");
		String author =  req.getParameter("b_author");
		int price = Integer.parseInt( req.getParameter("b_price") );
		int quan =Integer.parseInt( req.getParameter("b_quan") );
		String kind = req.getParameter("b_kind");
		
		BookDTO dto = new BookDTO();
		
		dto.setB_num(b_num);
		dto.setTitle(title);
		dto.setSubtitle(subtitle);
		dto.setAuthor(author);
		dto.setPrice(price);
		dto.setQuan(quan);
		dto.setKind(kind);
		
		int cnt = dao.upadte_book(dto);

		model.addAttribute("cnt", cnt);
		model.addAttribute("pageNum", pageNum);
		
	}
		
	
	
	
}
