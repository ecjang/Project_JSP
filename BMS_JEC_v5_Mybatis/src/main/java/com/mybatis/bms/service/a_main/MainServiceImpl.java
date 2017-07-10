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
		
		
		// �˻�� ������ �ش� �˻���� ��ȸ
		String str = req.getParameter("str");
		System.out.println("  -> str ��  : " + str );
		
		if( str == null ){
			cnt=8;
			
		} else {
			
			cnt = dao.cnt_search(str);
		}
		
		System.out.println("  -> �˻� ����  : " + cnt + "��");

		// �Խù� ������ ��� 
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
		
		
		// �������� ����
		model.addAttribute("cnt", cnt);			// �Խù� ����
		model.addAttribute("number", number);		// ���� ������ ��ȣ
		model.addAttribute("pageNum", pageNum);	// pageNum -> currentPage
		model.addAttribute("start", start);		// ���������� ���۹�ȣ
		model.addAttribute("end", end);			// ���������� ����ȣ
		model.addAttribute("str", str);
		
		
		if(cnt >0 ){
			model.addAttribute("startPage", startPage);		// ���� ������
			model.addAttribute("endPage", endPage);			// �� ������
			model.addAttribute("pageBlock", pageBlock);		// �Խñ� ǥ�� ����
			model.addAttribute("pageCount", pageCount);		// ������ �� ����
			model.addAttribute("currentPage", currentPage);	// ���� ������ ��ȣ
			
			
			List<BookDTO> dtos = null;

			// �˻�� ������ �Ű� ��� ���
			if( str == null ){
				dtos = dao.get_newbooks(cnt);

			// �˻�� ������ �������� ��ȸ
			} else{
				
				Map<String,Object> daoMap = new HashMap<String,Object>();
				
				daoMap.put("start",start);
				daoMap.put("end",end);
				daoMap.put("str",str);
				
				dtos = dao.get_searchbooks( daoMap );	
			}
			
			// ū �ٱ��� �Ӽ� ����
			model.addAttribute("dtos", dtos);					
			
			// �ӽ� ��ȣ ���� 
			int n = cnt+1;
			model.addAttribute("n", n);
			
		} else {
			System.out.println("  -> ��ϵ� ���� ����");
		}
		
		
	}
	
	@Override
	public void main_detail(HttpServletRequest req, Model model) throws Exception {
		
		System.out.println("  -> Main_02_Detail_Service ");
		
		// ���� �ޱ� 
		int b_num = Integer.parseInt( 	req.getParameter("b_num") );	// ���� ��ȸ�� å �ڵ� ��ȣ
		int pageNum = Integer.parseInt( req.getParameter("pageNum") );	// �˻������� ������ ��ȣ
		int start = Integer.parseInt( 	req.getParameter("start") );	// �˻������� ���� ��ȣ
		String str = req.getParameter("str");							// �˻������� �˻���
		
		
		/*System.out.println(b_num+pageNum+start+str);*/
		
		
		// ������ 
		BookDTO dto = new BookDTO();
		
		dto = dao.get_book(b_num);
		
		model.addAttribute("dto", dto);
		model.addAttribute("b_num", b_num);
		model.addAttribute("start", start);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("str", str);
		
	}
	
}
