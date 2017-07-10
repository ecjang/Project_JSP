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

		
		int cnt = 0;		// ī��Ʈ
		int state_cnt = 0;	// ���°�
		
		int start = 0;		// ���۹�ȣ 
		int m_num = 0;		// ȸ����ȣ
		int b_num = 0;		// ������ȣ
		int o_num = 0;		// �ֹ� ��ȣ
		int c_num = 0;		// �ֹ� ��ȣ
		int quan = 0;		// �ֹ� ����
		String state = null;	// ���� ����
		
	
		m_num = (Integer)req.getSession().getAttribute("m_num"); 
		System.out.println("    : �α����� ȸ����ȣ : " + m_num );
		
		BookDTO dto = new BookDTO();					
		
		
		// ������ �ֹ���Ͽ� ��� ���
		if(req.getParameter("b_num") != null){	
			 
			start 	= Integer.parseInt( req.getParameter("start") 	);		// å��ȣ
			b_num 	= Integer.parseInt( req.getParameter("b_num") 	);		// å��ȣ
			quan 	= Integer.parseInt( req.getParameter("quan") 	);		// �ֹ����� 
			state = "ORDER";
			
			// ���� ���� ��������
			dto = bookdao.getbook(b_num);
			
			dto.setB_num(b_num);	// ���� ��ȣ
			dto.setM_num(m_num);	// ȸ�� ��ȣ
			dto.setQuan(quan);		// �ֹ� ����
			dto.setState(state);	// ���� ����
			dto.setReg_date( new Timestamp(System.currentTimeMillis()) );	
			 	
			
			
			state_cnt = order.change_orderstate_01(dto);
			System.out.println("  -> state_cnt step1 : " + state_cnt);
			

			state_cnt = order.change_orderstate_02(dto);
			System.out.println("  -> state_cnt step2 : " + state_cnt);
			

			state_cnt = order.change_orderstate_03(dto);
			System.out.println("  -> state_cnt step3 : " + state_cnt);
			
			
			System.out.println("  -> state_cnt �� : " + state_cnt);
			System.out.println("  -> [ 1:����ó�� / 2:�������� / 3:�������� ����  / 4:��ٱ��� �Է� ���� / 0:������ ] ");
			
			
			// state_cnt�� 1�� ���� ����
			if(state_cnt == 1){
				
				if(req.getParameter("o_num") != null){
					
					o_num = Integer.parseInt( req.getParameter("o_num") );	// �ֹ���ȣ
					quan = 	Integer.parseInt( req.getParameter("ordernum") 	);	// �ֹ�����
					
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
		
		// ������ ���� 
		int pageSize = 10, pageBlock = 10;	// �������� �� ���� / ������ ����
		int cnt=0, start=0, end=0, number=0; 
		int currentPage=0, pageCount=0, startPage=0, endPage=0;
		String pageNum=null;
		
		
		// �ֹ� ��� �ҷ�����
		cnt = order.cnt_order();
		System.out.println("  -> cnt �� : " + cnt);
		
		
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
		if(endPage > pageCount) endPage = pageCount;		// ������ �������� ���a
		
		
		
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
			
			List<OrderDTO> orders;
			
			
			// getArticles �� �б⹮ �ۼ� / ������������ ������ ��������
			
			Map<String, Object> daoMap = new HashMap<>();
			daoMap.put("start", start);
			daoMap.put("end", end);
			
			orders = order.get_orders(daoMap);
			
			model.addAttribute("orders", orders);					// ū �ٱ��� �Ӽ� ����
			
			int sum = order.sum_order();
			model.addAttribute("sum", sum);
			
			/*
			int n = cnt+1;
			model.addAttribute("n", n);
			*/
			model.addAttribute("cnt",cnt);
			
			
		} else {
			System.out.println("  -> �ۼ��� �Խù��� ����");
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
		
		
		// �ֹ� ���� ��ȯ
		int o_num = Integer.parseInt( req.getParameter("o_num") );
		String state = req.getParameter("state");
		
		System.out.println( "  -> o_num �� : " + o_num);
		System.out.println( "  -> state �� : " + state);
		
		int cnt = 0;
		
		
		Map<String,Object> daoMap = new HashMap<>();
		
		daoMap.put("state",state);
		daoMap.put("o_num",o_num);

		// ORDER_CANCEL ���� SALE�� ����� ������Ͽ� �ǵ���
		if( state.equals("SALE") ){
			
			int quan = order.input_order_01(o_num);
			
			if( quan > 0 ){
				
				System.out.println("    : �ֹ��� : " + quan);
				daoMap.put("quan",quan);
			
				// quan ��ŭ ������� ��ȯ
				cnt = order.input_order_02(daoMap);
				
				if ( cnt > 0 ){
					
					System.out.println("    : ���� ���� ��ȯ �Ϸ�");
					
					cnt = order.input_order_03( o_num);
					
					if ( cnt > 0 ){
						
						System.out.println("    : �ֹ����� �Ϸ�");
						cnt = 1;
						
					} else {
						
						System.out.println("    : �ֹ������� ���� �߻�");
						cnt= 2;
					}
					
				} else { 
				
					System.out.println("    : ������ �̵��� �����߻�");
					cnt = 3;
				}
				
			} else {  // �ֹ� ������ ���� ��
				
				System.out.println("    : �ֹ����� ���� ");
				cnt= 4;
			}
			
			
		} else {
			
			cnt = order.input_order_04(daoMap);
			
			if (cnt>0){
				System.out.println("    : ���°� ���� �Ϸ�");
				cnt = 1;
			
			} else {
				System.out.println("    : ���°� ���� �� �����߻�");
				cnt = 0;
			}
			
		}
			
		model.addAttribute("order_cnt", cnt);
		
		
	}
		
	
	
}
