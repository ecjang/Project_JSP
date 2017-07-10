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
		
		// ��ٱ��Ͽ� ������ ���
		int cnt = 0;		// ī��Ʈ
		int state_cnt = 0;	// ���°�
		
		int start = 0;		// ���۹�ȣ
		int m_num = 0;		// ȸ����ȣ
		int b_num = 0;		// ������ȣ
		int c_num = 0;		// ��ٱ��� ��ȣ
		int quan = 0;		// ��ٱ��� �ֹ�����
		String state = null;	// ���� ����
		
		m_num = (Integer)req.getSession().getAttribute("m_num"); 
		System.out.println("    : �α����� ȸ����ȣ : " + m_num );
		
		
		// ���� dto ����
		BookDTO dto = new BookDTO();					
		
		// ������ ��ٱ��Ͽ� ���� ���
		if(req.getParameter("b_num") != null){
			
			start 	= Integer.parseInt( req.getParameter("start") 	);		// å��ȣ
			b_num 	= Integer.parseInt( req.getParameter("b_num") 	);		// å��ȣ
			quan 	= Integer.parseInt( req.getParameter("quan") 	);		// �ֹ����� */				
			state = "CART";
			
			// ���� ���� ��������
			dto = book.getbook(b_num);
			
			dto.setB_num(b_num);	// ���� ��ȣ
			dto.setM_num(m_num);	// ȸ�� ��ȣ
			dto.setQuan(quan);		// �ֹ� ����
			dto.setState(state);	// ���� ����
			dto.setReg_date( new Timestamp(System.currentTimeMillis()) );	
	
			 
			
			state_cnt = cart.insert_cart_01(dto);
			System.out.println("state_cnt step1 : " + state_cnt);
			
			state_cnt = cart.insert_cart_02(dto);
			System.out.println("state_cnt step2 : " + state_cnt);
			
			state_cnt = cart.insert_cart_03(dto);
			System.out.println("state_cnt step3 : " + state_cnt);
			
			
			
			System.out.println("  -> state_cnt �� : " + state_cnt);
			System.out.println("  -> [ 1:����ó�� / 2:�������� / 3:�������� ����  / 4:��ٱ��� �Է� ���� / 0:������ ] ");
			
			
			// state_cnt�� 1�� ���� ����
			if(state_cnt == 1){
				
				if(req.getParameter("c_num") != null){
					
					c_num = Integer.parseInt( req.getParameter("c_num") );	// īƮ��ȣ
					quan = 	Integer.parseInt( req.getParameter("ordernum") 	);	// �ֹ�����
					
					model.addAttribute("c_num", c_num);
					model.addAttribute("quan", quan);
					model.addAttribute("state_cnt", state_cnt);
				}
			}

		}
		
		// ���� �ѱ�
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
		
		
		// ���� �ޱ�
		String pageNum = req.getParameter("pageNum");	
		if(pageNum==null) pageNum="1";	// ������ ��ȣ
		
		
		// ������ ���� 
		int pageSize = 10;		// �������� �� ����
		int pageBlock = 10;		// ������ ����
		int start=0;			// �Խù� ���� ��ȣ
		int end=0;				// �Խɹ� �� ��ȣ
		int number=0; 			// �Խù� ��ȣ
		int currentPage=0;		// ���� ������ ��ȣ
		int pageCount=0;		// ������ ��ü ��ȣ
		int startPage=0; 		// ���� ������ ��ȣ
		int endPage=0;			// �� ������ ��ȣ
		
		
		
		// ��ٱ��� ��� ���� �ҷ�����
		int cnt = cart.cnt_cart();				// ��� �ҷ�����
		System.out.println("  -> cnt �� " + cnt);
		
		
		
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
		
		
		
		// īƮ ���̺��� �ҷ��� 
		List<CartDTO> carts = null;
		
		Map<String,Object> daoMap = new HashMap<>();
		daoMap.put("start", start);
		daoMap.put("end", end);
		
		carts = cart.get_carts(daoMap);			// ū �ٱ��Ͽ� ������ŭ ���� ����
		
		
		// ���� ���� ���� : �� 6��
		// m_num / b_num / start / ordernum / state / pageNum
		
		// �ѱ�� ���� : �⺻ 
				
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
			
			model.addAttribute("carts", carts);				// ū �ٱ��� �Ӽ� ����
			
			
		} else {
			System.out.println("  -> ��ٱ��Ͽ� ��� ������ �����ϴ�.");
		}
		
		
		// ���°��� ���� ���� ó��
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
		
		
		// ���� �ޱ�
		if ( req.getParameter("quan") != null ){
			quan = Integer.parseInt(req.getParameter("quan"));
			c_num = Integer.parseInt(req.getParameter("c_num"));
			b_num = Integer.parseInt(req.getParameter("b_num"));
		}
		
		/*
		System.out.println("ordernum ��  : " + ordernum);
		System.out.println("b_num ��  : " + b_num);
		System.out.println("c_num ��  : " + c_num);
		*/

		
		// ��� �ľ� 
		int book_num = cart.cnt_bookquan(b_num);
		
		// �ֹ� ���� �ľ� 
		int cart_num = cart.cnt_cartquan(c_num);
		
		
		int cart_quan = 0;
		int quan_abs = 0;
		
		// �ֹ������� ����� �۾ƾ� ����
		if ( quan <= book_num ){
			
			quan_abs = Math.abs(cart_num-quan);
			
			Map<String,Object> daoMap = new HashMap<>();
			daoMap.put("quan_abs",quan_abs);
			daoMap.put("c_num",c_num);
			
			if ( cart_num > quan ){
				
				System.out.println("  -> �ֹ� ���� ���� : ��� �߰�");
				
				cart_quan = cart.quanminus_cart_01(daoMap);
				cart_quan = cart.quanminus_cart_02(daoMap);
				
				
				
			} else if( cart_num < quan ){
				
				System.out.println("  -> �ֹ� ���� ���� : ��� ����");
				
				cart_quan = cart.quanplus_cart_01(daoMap);
				cart_quan = cart.quanplus_cart_02(daoMap);
				
			} else {
				System.out.println("  -> �ֹ� ���� ���� ����");
				cart_quan = -1;
			}
			
			System.out.println("  -> cart_quan �� : " + cart_quan );
			
			
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
	
		// ���� �ޱ�
		int c_num =  Integer.parseInt(req.getParameter("c_num")) ;
	
		// ���� ���� 
		int cnt = cart.delete_cart_byc_num(c_num);
		
		model.addAttribute("cnt", cnt);
		
	}


	
	
	@Override
	public void cart_toorder(Model model) throws Exception {

		System.out.println("  -> Cart_Order_Service");
		
		Map<String,Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		
		// ���� �ޱ�
			int quan = Integer.parseInt(req.getParameter("quan")) ;
			int c_num =  Integer.parseInt(req.getParameter("c_num")) ;
			int orderCnt =0;
		

			// ���� �ҷ�����
			Map<String,Object> daoMap = new HashMap<>();
			daoMap.put("c_num",c_num);
			daoMap.put("quan",quan);
			
			CartDTO cartdto = cart.get_cart( daoMap );
			
			
			// ���� �����ϱ�
			orderCnt = cart.insert_order(cartdto);
			
			
			// �ֹ�������� �̵� ������
			if ( orderCnt > 0 ){
				
				// ��ٱ��� ��� ����
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
		
		// ���� �ޱ� : ȸ����ȣ
		int m_num = (Integer)req.getSession().getAttribute("m_num");
		
		
		if(cartall == 0){
			// īƮ ���� �ҷ����� -> ����Ʈ�� ���
			List<CartDTO> carts = cart.getall_cart( m_num );
			
			for( int i=0; i<carts.size() ; i++ ){
				/*System.out.println("  -> " + carts.get(i).toString() );*/
				
				cartall += cart.allinsert_order( carts.get(i) );
				
			}
			
			// �ֹ� ���̺� ���
			System.out.println("�ٴ�Ҵ�!");
		}
		
		// īƮ ���̺� ���� id �˻�
		if ( cartall > 0 ) {
			
			cart.delete_cart_bym_num(m_num);
			
		}
		
		model.addAttribute("cartall", cartall);
		
		
	}
	
	
	
}
