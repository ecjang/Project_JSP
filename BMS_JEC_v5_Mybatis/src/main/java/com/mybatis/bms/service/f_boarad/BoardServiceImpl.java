package com.mybatis.bms.service.f_boarad;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.mybatis.bms.dao.b_member.MemberDAO;
import com.mybatis.bms.dao.b_member.MemberDAOImpl;
import com.mybatis.bms.dao.d_board.BoardDAO;
import com.mybatis.bms.dto.BoardDTO;
import com.mybatis.bms.dto.MemberDTO;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Inject
	BoardDAO dao;
	
	@Inject
	MemberDAO memberdao;
	
	@Override
	public void board_list(Model model) throws Exception {
		
		System.out.println("  -> Board_01_List_Service ");
		
		Map<String,Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");

		
		String kind = "";
		if( req.getParameter("kind") != null ){
			
			kind = req.getParameter("kind");
			
		} else {
			kind = "NOTICE";
		}
		
		System.out.println("  -> kind �� : " + kind);
		
		
		
		
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
		
		
		cnt = dao.cnt_board(kind);
		System.out.println("  -> cnt �� : " + cnt);
		
		/*-----------------------------------------------------*/
		
		pageNum = req.getParameter("pageNum"); 
		if(pageNum==null || pageNum.equals("0") ) pageNum="1";
		
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
		model.addAttribute("kind", kind);
		
		if(cnt >0 ){
			model.addAttribute("startPage", startPage);		// ���� ������
			model.addAttribute("endPage", endPage);			// �� ������
			model.addAttribute("pageBlock", pageBlock);		// �Խñ� ǥ�� ����
			model.addAttribute("pageCount", pageCount);		// ������ �� ����
			model.addAttribute("currentPage", currentPage);	// ���� ������ ��ȣ
			
			
			List<BoardDTO> dtos = null;
			
			Map<String,Object> daoMap = new HashMap<>();
			
			daoMap.put("start",start);
			daoMap.put("end", end);
			daoMap.put("kind",kind);
			
			dtos = dao.get_boards(daoMap);
			
			model.addAttribute("dtos", dtos);					// ū �ٱ��� �Ӽ� ����
			
			
			int n = cnt+1;
			model.addAttribute("n", n);
			
		} else {
			System.out.println("  -> �ۼ��� �Խù��� ����");
		}
		
	}
	
	@Override
	public void board_detail(Model model) throws Exception {

		System.out.println("  -> Board_Detail_Service ");
		
		Map<String,Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		
		int no = 0;
		int pageNum = 0;
		int number = 0;
		String kind = null;
		
		
		if( req.getParameter("no") != null ){
			
			no = Integer.parseInt( req.getParameter("no") );
			pageNum = Integer.parseInt( req.getParameter("pageNum") );
			kind = (String) req.getAttribute("kind");
			
			if ( req.getParameter("kind")!=null ){
				kind = req.getParameter("kind");
			}
			
		}
		
		
		/*
		System.out.println("  -> kind : " + kind);
		System.out.println("  -> no : " + no);
		System.out.println("  -> number : " + number);
		System.out.println("  -> pageNum : " + pageNum);
		*/
		
		BoardDTO dto = new BoardDTO();
		
		dto = dao.get_board(no);
		
		
		// ������ üũ �� ��ȸ�� ����
		try {
			String ip = InetAddress.getLocalHost().getHostAddress();
			System.out.println("   : ������ ip�ּ� : " + ip);
		
			if( !dto.getIP().equals(ip) ){
				System.out.println("  -> �ۼ��� ip�ּҿ� �ٸ� : ��ȸ�� ���� ");
				dao.add_viewcnt(no);
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		
		model.addAttribute("dto", dto);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("no", no);
		model.addAttribute("kind", kind);
		
	}

	
	@Override
	public void board_write_form(Model model) throws Exception {
		
		System.out.println("  -> Board_Write_Form_Service ");
		
		Map<String,Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		
		// ȸ���� �� �ۼ��� ���� 
		if( Integer.parseInt(req.getParameter("Mnum")) >= 0 ){
		
			int Mnum = Integer.parseInt( req.getParameter("Mnum") );
			
			/*
			int number=0;		// �Խù� ��ȣ
			*/
			
			// �ʱ�ȭ ( ������ ��� )
			int no=0 ;			// ��ȣ
			int pageNum=0 ;		// ������ ��ȣ
			int ref=0 ;			// ��� �׷��� ��ȣ
			int ref_step=0 ;	// ��� �鿩����
			int ref_level=0; 	// ��� ����
			String kind = null;	// �Խ��� ���� ( view���� ����ڰ� ���� )
			
			// ��۷� �ۼ�
			if ( req.getParameter("no")!= null ){
				
				no 			= Integer.parseInt	( req.getParameter("no") 		);
				pageNum 	= Integer.parseInt	( req.getParameter("pageNum") 	);
				ref 		= Integer.parseInt	( req.getParameter("ref")		);
				ref_step 	= Integer.parseInt	( req.getParameter("ref_step")	);
				ref_level	= Integer.parseInt	( req.getParameter("ref_level")	);
				kind 		= (String) req.getParameter("kind");
				
				System.out.println("  -> ����� �� kind : " + kind);
				
			}
			
			model.addAttribute("no", no);
			model.addAttribute("Mnum", Mnum);
			model.addAttribute("pageNum", pageNum);
			model.addAttribute("ref", ref);
			model.addAttribute("ref_step", ref_step);
			model.addAttribute("ref_level", ref_level);
			model.addAttribute("kind", kind);
			
			
			int n = dao.cnt_board(kind) + 1;
			System.out.println("  -> �ӽ� ��ȣ ���� : " + n);
			model.addAttribute("n", n);
			
		
		// ȸ���� �ƴ� ���
		} else {
			
			System.out.println("  -> ��ġ�ϴ� ȸ����ȣ�� ����");
			String back = "/Store/05_Board/04_Board_Write_Pro&cnt=3";
			model.addAttribute("back", back);
		}
		
	}

	
	@Override
	public void board_write_pro(Model model) throws Exception {
		
		
		System.out.println("  -> Board_Write_Pro_Service ");
		
		Map<String,Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");

	
		String kind = null;
		
		if ( req.getParameter("kind") != null ){
			kind = (String) req.getParameter("kind");
			
			System.out.println("  -> �Խ��� ���� Ȯ�� : " + kind);
		}
		
		
		// ������ ���ϱ�
		String ip = "";
		try {
			InetAddress local = InetAddress.getLocalHost();
			ip = local.getHostAddress();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		
		
		// �Է��� ���� �����ϱ�
		BoardDTO dto = new BoardDTO();
		
		dto.setNO( Integer.parseInt( req.getParameter("no")) );
		dto.setM_NUM( Integer.parseInt( req.getParameter("Mnum")) );
		dto.setTITLE( req.getParameter("title") );
		dto.setWRITER( req.getParameter("writer") );
		dto.setCONTENT( req.getParameter("content") );
		dto.setVIEWS(0);
		dto.setREF( Integer.parseInt( req.getParameter("ref")) );
		dto.setREF_STEP( Integer.parseInt( req.getParameter("ref_step")) );
		dto.setREF_LEVEL( Integer.parseInt( req.getParameter("ref_level")) );
		dto.setREG_DATE( new Timestamp(System.currentTimeMillis()) );
		dto.setKIND( req.getParameter("kind") );
		dto.setIP(ip);
		
		
		/*
		System.out.println( "  -> �Է¹��� ������ Ȯ�� \n "+ dto.toString() );
		*/
		
		
		// �Խù� ��� 
		
		int ref	= dto.getREF();
		int ref_step = dto.getREF_STEP();
		int cnt = 0;
		
		// �Խñ۷� �ۼ�
		if ( dto.getNO() == 0 ){
			
			int max = dao.insert_board_01();
			
			// ���� ������ max�� +1
			if( max > 0 ){
				System.out.println("  -> �Խñ� �ۼ� ");
				ref = max +1 ;
				
			// ������ ��� ref�� 1���� ����
			} else {
				System.out.println("  -> ���� �ۼ� ");
				ref = 1;
			}

			dto.setREF_STEP(0);
			dto.setREF_LEVEL(0);
			
			
		// ��۷� �ۼ� 
		} else {
			
			System.out.println("  -> ��� �Է� ");	
			
			
			
			Map<String,Object> daoMap = new HashMap<>();
			daoMap.put("ref", ref);
			daoMap.put("ref_step", ref_step);
			
			dao.insert_board_02(daoMap);
			
			dto.setREF_STEP( dto.getREF_STEP()+1 );
			dto.setREF_LEVEL( dto.getREF_LEVEL()+1 );
		}
		
		/*
		System.out.println("  -> Ȯ�ο� dto" + dto.toString() );
		*/
		
		// �Խù� �̳� ��� �Է�
		cnt = dao.insert_board_03(dto);
		
		
		model.addAttribute("cnt",cnt);
		model.addAttribute("kind", kind);
		
		int pageNum = Integer.parseInt(req.getParameter("pageNum")); 
		model.addAttribute("pageNum", pageNum );
			
		
	}

	
	@Override
	public void board_modify_check(Model model) throws Exception {
		
		System.out.println("  -> Board_Modify_From_Service");
		
		Map<String,Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		
		// ������ �� ��� �ٷ� �̵�
		if( req.getSession().getAttribute("memkind") != null ){
			
			if( (Integer) req.getSession().getAttribute("memkind") == 1 ){
				
				System.out.println("    : ������ �������� ���� "); 
				model.addAttribute("req", req);
				req.setAttribute("back","admin");
			}
		// �α��� �� ������ �α��� �������� �̵�
		} else {
			
			System.out.println("  -> �α��� �ʿ�");
			model.addAttribute("req", req);
			req.setAttribute("back","login");
			
		}
		
	}

	
	@Override
	public void board_modify_form(Model model) throws Exception {

		System.out.println("  -> Board_Modify_From_Service");
		/*System.out.println("KIND �� : " + kind);*/

		Map<String,Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		
		/*
		if( req.getParameter("pageNum")!=null ){
			System.out.println("pagenum1:"+ req.getParameter("pageNum"));
		}
		
		if( req.getAttribute("pageNum")!=null ){
			System.out.println("pagenum2:"+ req.getAttribute("pageNum"));
		}
		
		if( req.getParameter("admin")!=null ){
			System.out.println("admin1:" + req.getParameter("admin"));
		}
		
		if( req.getAttribute("admin")!=null ){
			System.out.println("admin2:" + req.getAttribute("admin"));
		}
		*/
		
		int n = 0;
		int cnt = 0;
		String kind	= req.getParameter("kind");
		int no	= Integer.parseInt(req.getParameter("no"));
		int pageNum	= Integer.parseInt(req.getParameter("pageNum"));
		
		
		if( req.getAttribute("admin") == null ){
			
			int Mnum	= Integer.parseInt(req.getParameter("Mnum"));
			String pwd	= req.getParameter("passwd");
			
			/*
			System.out.println(pageNum);
			System.out.println("Mnum - handler : " + (req.getParameter("Mnum")));
			System.out.println("Mnum - handler : " + Mnum);
			*/
			
			// ȸ���� ���� ȸ����ȣ�� ��й�ȣ Ȯ���� �̵�
			MemberDTO member = memberdao.check_login_bymnum(Mnum);
			
			
			if(member.getM_num() == Mnum){
				
				System.out.println("    : ���̵� ��ġ�մϴ�."); 
				
				if ( member.getPw().equals(pwd) ){ 
					
					System.out.println("    : ��й�ȣ�� ��ġ�մϴ�."); 
					cnt=1; 
				
				} else { 
					
					System.out.println("    : ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");  
					cnt=-1; 
				}
			
			
			}  else {
				System.out.println("    : ���̵� ��ġ���� �ʽ��ϴ�.");
			}
				
			
			model.addAttribute("cnt", cnt);
			model.addAttribute("Mnum", Mnum);
			
		}
		
		model.addAttribute("no", no);
		model.addAttribute("kind", kind);
		model.addAttribute("pageNum", pageNum);
		
		if( cnt == 1 || (Integer)req.getAttribute("cnt")==1 ){
			
			BoardDTO dto = dao.get_board(no);
			n = dao.cnt_board(kind);
			
			model.addAttribute("dto", dto);
			model.addAttribute("n", n);
			System.out.println("  -> �ӽù�ȣ ���� : " + n);
			
		}
	}

	
	@Override
	public void board_modify_pro(Model model) throws Exception {
		System.out.println("  -> Board_Modify_Pro_Service");
		/*System.out.println("kind �� : " + kind);*/

		Map<String,Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		
		
		int no = Integer.parseInt( req.getParameter("no") );
		int Mnum = Integer.parseInt( req.getParameter("Mnum") );
		int pageNum = Integer.parseInt( req.getParameter("pageNum") );
		
		
		String title 	= req.getParameter("title");
		String content 	= req.getParameter("content");
		String kind		= req.getParameter("kind");
		
		BoardDTO dto = new BoardDTO();
		
		dto.setNO(no);
		dto.setM_NUM(Mnum);
		dto.setTITLE(title);
		dto.setCONTENT(content);
		dto.setKIND(kind);

		int cnt = dao.upadte_board(dto);

		model.addAttribute("cnt", cnt);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("kind", kind);
		
	}
	
	
	@Override
	public void board_delte_check(Model model) throws Exception {
		
		System.out.println("  -> Board_delte_check_Service ");
		
		Map<String,Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		
		if( req.getSession().getAttribute("memkind") != null ){
			
			if( (Integer) req.getSession().getAttribute("memkind") == 1 ){
				
				System.out.println("    : ������ �������� ���� "); 
				req.setAttribute("back","admin");
			}
		} else {
			
			System.out.println("  -> �α��� �ʿ�");
			model.addAttribute("req", req);
			req.setAttribute("back","login");
			
		}
		
	}
	
	@Override
	public void board_delete_pro(Model model) throws Exception {

		System.out.println("  -> Board_Delete_Pro_Service ");

		Map<String,Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		
		int scnt = 0;
		int Mnum = 0;
		String pwd = null;
		MemberDTO member = null;
		/*
		System.out.println("scnt1 :" + req.getParameter("scnt"));
		System.out.println("scnt2 :" + req.getAttribute("scnt"));
		*/
		
		if( req.getAttribute("scnt") != null ){
			
			scnt = (Integer)req.getAttribute("scnt"); 
			System.out.println("  -> scnt �� : " + scnt);
			
		} else {
			Mnum = Integer.parseInt( req.getParameter("Mnum") );
			pwd = req.getParameter("pw");
			
			// ȸ������ ��ȸ
			member = dao.check_bym_num(Mnum);
			
			// ȸ����ȣ ��ġ�Ұ�� ���� ����
			if ( member.getM_num() == (Integer)req.getSession().getAttribute("m_num")  ){
				
				if ( member.getPw().equals(pwd) ){ 
					System.out.println("    : ��й�ȣ ��ġ"); 
					scnt=1; 
				
				} else { 
					System.out.println("    : ��й�ȣ ����ġ");  
					scnt=-1; 
				}
			
			} else {
				System.out.println("    : ��ġ�ϴ� ���̵� ����, �������� ����");
				scnt=-1; 
			}
			
			model.addAttribute("scnt", scnt);
			
		}
		
		// ȸ���̳� �����ڸ� �Խù� ���� �۾� ����
		if( scnt == 1 ){
			
			/*
			System.out.println("  -> �̰� �� no? : " + req.getParameter("no") );
			System.out.println("  -> �̰� �� Mnum? : " + req.getParameter("Mnum") );
			System.out.println("  -> �̰� �� pageNum? : " + req.getParameter("pageNum") );
			System.out.println("  -> �̰� �� pw? : " + req.getParameter("pw") );
			System.out.println("  -> �̰� �� kind? : " + req.getParameter("kind") );
			*/
			
			int no = Integer.parseInt( req.getParameter("no") );
			int pageNum = Integer.parseInt( req.getParameter("pageNum") );
			String kind = req.getParameter("kind");
			
			if( req.getAttribute("no") != null  ){
				no = (Integer) req.getAttribute("no") ;
				pageNum = (Integer) req.getAttribute("pageNum") ;
				kind = (String) req.getAttribute("kind");
				
			}
			
			model.addAttribute("pageNum", pageNum);
			model.addAttribute("kind", kind);
			
			int dcnt = 0;
			BoardDTO board = new BoardDTO();
			
			// �ش� ��ȣ�� �Խù� ���� �ҷ�����
			board = dao.delete_board_01(no);
			
			/*
			System.out.println("  -> �ҷ��� �Խ��� ����  : " + board.toString() );
			*/
			
			if( board.getNO() > 0 ){
				
				System.out.println("  -> ��� ���� Ȯ�� ");
				
				int ref = board.getREF();
				int ref_step = board.getREF_STEP();
				int ref_level = board.getREF_LEVEL();
				
				Map<String,Object> daoMap2 = new HashMap<>();
				daoMap2.put("ref", ref);
				daoMap2.put("ref_step", ref_step);
				daoMap2.put("ref_level", ref_level);
				
				List<BoardDTO> dtos = dao.delete_board_02(daoMap2);
				
				// ����� ���� ��� 
				if( dtos.size() == 0 ){
					
					System.out.println("  -> ��� ���� DELETE ���� ");
				
					dcnt = dao.delete_board_03(daoMap2);
					dcnt = dao.delete_board_04(no);
					
					model.addAttribute("dcnt", dcnt);
					
					System.out.println("  -> ������ ���� dcnt :" + dcnt );
				
				// ����� ���� ���
				} else if ( dtos.size() > 0 ){
					
					dcnt =-1;
					System.out.println("  -> ��� ����  :" + dtos.size() );
					
				} else {
					
					dcnt =-1;
					System.out.println("  -> ��� ���� Ȯ���� ���� �߻� " );
					
				}
			} 
			model.addAttribute("dcnt", dcnt);
		} 
		
		
	}

}
