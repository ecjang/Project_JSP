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
		
		int pageSize = 10;		// �������� �� ����
		int pageBlock = 10;		// ������ ����
		int cnt=0;				// ī��Ʈ
		int start=0;			// �Խù� ���� ��ȣ
		int end=0;				// �Խɹ� �� ��ȣ
		int number=0; 			// �Խù� ��ȣ
		int currentPage=0;		// ���� ������ ��ȣ
		int pageCount=0;		// ������ ��ü ��ȣ
		int startPage=0; 		// ���� ������ ��ȣ
		int endPage=0;			// �� ������ ��ȣ
		String pageNum=null;	// ������ ��ȣ ����
		
		cnt = dao.cnt_book();
		System.out.println("  -> cnt �� : " + cnt);
		
		
		/*-----------------------------------------------------*/
		
		pageNum = req.getParameter("pageNum"); 
		if(pageNum==null) pageNum="1";
		
		currentPage = Integer.parseInt(pageNum);			// ���� ������ ����
		
		start = (currentPage-1)*pageSize +1;				// �Խñ� ���۹�ȣ
		end   = start+pageSize -1;	if(end>cnt) end=cnt;	// �Խñ� ����ȣ
		number = cnt- (currentPage-1)*pageSize;				// �Խñ�  ��ȣ

		startPage=(currentPage/pageBlock)*pageBlock +1;		// �Խñ� ���� ��ȣ
		
		if(currentPage%pageBlock==0){						// �Խñ� ��ȣ�� ������ ��ϰ� ���� ���
			startPage = startPage-pageBlock ;
		}
		
		pageCount = (cnt/pageSize)+(cnt%pageSize>0?1:0);	// ������ �� ����
		
		endPage=startPage+pageBlock -1;						// �Խñ� �� ��ȣ
		if(endPage > pageCount) endPage = pageCount;		// ������ �������� ���
		
		/*-----------------------------------------------------*/
		
		model.addAttribute("cnt", cnt);			// �Խù� ����
		model.addAttribute("number", number);		// ���� ������ ��ȣ
		model.addAttribute("pageNum", pageNum);	// pageNum -> currentPage
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		
		
		if(cnt >0 ){
			model.addAttribute("startPage", startPage);		// ���� ������
			model.addAttribute("endPage", endPage);			// �� ������
			model.addAttribute("pageBlock", pageBlock);		// �Խñ� ǥ�� ����
			model.addAttribute("pageCount", pageCount);		// ������ �� ����
			model.addAttribute("currentPage", currentPage);	// ���� ������ ��ȣ
			
			
			ArrayList<BookDTO> dtos = null;
			
			Map<String,Object> daoMap = new HashMap<String,Object>();
			daoMap.put("start",start);
			daoMap.put("end",end);
			dtos = dao.get_books( daoMap );
			model.addAttribute("dtos", dtos);					// ū �ٱ��� �Ӽ� ����
			
			int n = cnt+1;
			model.addAttribute("n", n);
			model.addAttribute("b_num", 0);
			
		} else {
			System.out.println("  -> ��ϵ� ������ �����ϴ�.");
			
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
		System.out.println("  -> b_num�� �� " + b_num);
		System.out.println("  -> number�� �� " + number);
		System.out.println("  -> pageNum�� �� " + pageNum);
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
		
		
		System.out.println("  -> b_num�� : " + b_num);
		
		
		int n = dao.cnt_book()+1;	// <- �߰��Ұ�
		System.out.println("  -> �ӽ� ��ȣ ���� : " + n);
		
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
		
		/*System.out.println( "  -> �Է¹��� ������ Ȯ�� \n "+ dto.toString() );*/
		
		int cnt = dao.insert_book(dto);
		
		System.out.println("  -> cnt�� : " + cnt);
		
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
		System.out.println("  -> cnt�� :" + cnt);
		
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
		System.out.println( "  -> �ӽù�ȣ ���� : " + n );
		
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
