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
		
		System.out.println("  -> kind 값 : " + kind);
		
		
		
		
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
		
		
		cnt = dao.cnt_board(kind);
		System.out.println("  -> cnt 값 : " + cnt);
		
		/*-----------------------------------------------------*/
		
		pageNum = req.getParameter("pageNum"); 
		if(pageNum==null || pageNum.equals("0") ) pageNum="1";
		
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
		model.addAttribute("kind", kind);
		
		if(cnt >0 ){
			model.addAttribute("startPage", startPage);		// 시작 페이지
			model.addAttribute("endPage", endPage);			// 끝 페이지
			model.addAttribute("pageBlock", pageBlock);		// 게시글 표시 개수
			model.addAttribute("pageCount", pageCount);		// 페이지 총 개수
			model.addAttribute("currentPage", currentPage);	// 현제 페이지 번호
			
			
			List<BoardDTO> dtos = null;
			
			Map<String,Object> daoMap = new HashMap<>();
			
			daoMap.put("start",start);
			daoMap.put("end", end);
			daoMap.put("kind",kind);
			
			dtos = dao.get_boards(daoMap);
			
			model.addAttribute("dtos", dtos);					// 큰 바구니 속성 설정
			
			
			int n = cnt+1;
			model.addAttribute("n", n);
			
		} else {
			System.out.println("  -> 작성된 게시물이 없음");
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
		
		
		// 아이피 체크 후 조회수 증가
		try {
			String ip = InetAddress.getLocalHost().getHostAddress();
			System.out.println("   : 접속한 ip주소 : " + ip);
		
			if( !dto.getIP().equals(ip) ){
				System.out.println("  -> 작성자 ip주소와 다름 : 조회수 증가 ");
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
		
		// 회원만 글 작성이 가능 
		if( Integer.parseInt(req.getParameter("Mnum")) >= 0 ){
		
			int Mnum = Integer.parseInt( req.getParameter("Mnum") );
			
			/*
			int number=0;		// 게시물 번호
			*/
			
			// 초기화 ( 새글일 경우 )
			int no=0 ;			// 번호
			int pageNum=0 ;		// 페이지 번호
			int ref=0 ;			// 댓글 그룹핑 번호
			int ref_step=0 ;	// 댓글 들여쓰기
			int ref_level=0; 	// 댓글 레벨
			String kind = null;	// 게시판 종류 ( view에서 사용자가 선택 )
			
			// 답글로 작성
			if ( req.getParameter("no")!= null ){
				
				no 			= Integer.parseInt	( req.getParameter("no") 		);
				pageNum 	= Integer.parseInt	( req.getParameter("pageNum") 	);
				ref 		= Integer.parseInt	( req.getParameter("ref")		);
				ref_step 	= Integer.parseInt	( req.getParameter("ref_step")	);
				ref_level	= Integer.parseInt	( req.getParameter("ref_level")	);
				kind 		= (String) req.getParameter("kind");
				
				System.out.println("  -> 답글일 때 kind : " + kind);
				
			}
			
			model.addAttribute("no", no);
			model.addAttribute("Mnum", Mnum);
			model.addAttribute("pageNum", pageNum);
			model.addAttribute("ref", ref);
			model.addAttribute("ref_step", ref_step);
			model.addAttribute("ref_level", ref_level);
			model.addAttribute("kind", kind);
			
			
			int n = dao.cnt_board(kind) + 1;
			System.out.println("  -> 임시 번호 생성 : " + n);
			model.addAttribute("n", n);
			
		
		// 회원이 아닐 경우
		} else {
			
			System.out.println("  -> 일치하는 회원번호가 없음");
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
			
			System.out.println("  -> 게시판 종류 확인 : " + kind);
		}
		
		
		// 아이피 구하기
		String ip = "";
		try {
			InetAddress local = InetAddress.getLocalHost();
			ip = local.getHostAddress();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		
		
		// 입력할 정보 설정하기
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
		System.out.println( "  -> 입력받은 값들을 확인 \n "+ dto.toString() );
		*/
		
		
		// 게시물 등록 
		
		int ref	= dto.getREF();
		int ref_step = dto.getREF_STEP();
		int cnt = 0;
		
		// 게시글로 작성
		if ( dto.getNO() == 0 ){
			
			int max = dao.insert_board_01();
			
			// 글이 있으면 max값 +1
			if( max > 0 ){
				System.out.println("  -> 게시글 작성 ");
				ref = max +1 ;
				
			// 새글일 경우 ref는 1부터 시작
			} else {
				System.out.println("  -> 새글 작성 ");
				ref = 1;
			}

			dto.setREF_STEP(0);
			dto.setREF_LEVEL(0);
			
			
		// 답글로 작성 
		} else {
			
			System.out.println("  -> 답글 입력 ");	
			
			
			
			Map<String,Object> daoMap = new HashMap<>();
			daoMap.put("ref", ref);
			daoMap.put("ref_step", ref_step);
			
			dao.insert_board_02(daoMap);
			
			dto.setREF_STEP( dto.getREF_STEP()+1 );
			dto.setREF_LEVEL( dto.getREF_LEVEL()+1 );
		}
		
		/*
		System.out.println("  -> 확인용 dto" + dto.toString() );
		*/
		
		// 게시물 이나 댓글 입력
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
		
		// 관리자 일 경우 바로 이동
		if( req.getSession().getAttribute("memkind") != null ){
			
			if( (Integer) req.getSession().getAttribute("memkind") == 1 ){
				
				System.out.println("    : 관리자 권한으로 수정 "); 
				model.addAttribute("req", req);
				req.setAttribute("back","admin");
			}
		// 로그인 안 했으면 로그인 페이지로 이동
		} else {
			
			System.out.println("  -> 로그인 필요");
			model.addAttribute("req", req);
			req.setAttribute("back","login");
			
		}
		
	}

	
	@Override
	public void board_modify_form(Model model) throws Exception {

		System.out.println("  -> Board_Modify_From_Service");
		/*System.out.println("KIND 값 : " + kind);*/

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
			
			// 회원일 경우는 회원번호와 비밀번호 확인후 이동
			MemberDTO member = memberdao.check_login_bymnum(Mnum);
			
			
			if(member.getM_num() == Mnum){
				
				System.out.println("    : 아이디가 일치합니다."); 
				
				if ( member.getPw().equals(pwd) ){ 
					
					System.out.println("    : 비밀번호가 일치합니다."); 
					cnt=1; 
				
				} else { 
					
					System.out.println("    : 비밀번호가 일치하지 않습니다.");  
					cnt=-1; 
				}
			
			
			}  else {
				System.out.println("    : 아이디가 일치하지 않습니다.");
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
			System.out.println("  -> 임시번호 생성 : " + n);
			
		}
	}

	
	@Override
	public void board_modify_pro(Model model) throws Exception {
		System.out.println("  -> Board_Modify_Pro_Service");
		/*System.out.println("kind 값 : " + kind);*/

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
				
				System.out.println("    : 관리자 권한으로 삭제 "); 
				req.setAttribute("back","admin");
			}
		} else {
			
			System.out.println("  -> 로그인 필요");
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
			System.out.println("  -> scnt 값 : " + scnt);
			
		} else {
			Mnum = Integer.parseInt( req.getParameter("Mnum") );
			pwd = req.getParameter("pw");
			
			// 회원인지 조회
			member = dao.check_bym_num(Mnum);
			
			// 회원번호 일치할경우 삭제 가능
			if ( member.getM_num() == (Integer)req.getSession().getAttribute("m_num")  ){
				
				if ( member.getPw().equals(pwd) ){ 
					System.out.println("    : 비밀번호 일치"); 
					scnt=1; 
				
				} else { 
					System.out.println("    : 비밀번호 불일치");  
					scnt=-1; 
				}
			
			} else {
				System.out.println("    : 일치하는 아이디 없음, 삭제권한 없음");
				scnt=-1; 
			}
			
			model.addAttribute("scnt", scnt);
			
		}
		
		// 회원이나 관리자면 게시물 삭제 작업 시작
		if( scnt == 1 ){
			
			/*
			System.out.println("  -> 이게 왜 no? : " + req.getParameter("no") );
			System.out.println("  -> 이게 왜 Mnum? : " + req.getParameter("Mnum") );
			System.out.println("  -> 이게 왜 pageNum? : " + req.getParameter("pageNum") );
			System.out.println("  -> 이게 왜 pw? : " + req.getParameter("pw") );
			System.out.println("  -> 이게 왜 kind? : " + req.getParameter("kind") );
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
			
			// 해당 번호의 게시물 정보 불러오기
			board = dao.delete_board_01(no);
			
			/*
			System.out.println("  -> 불러온 게시판 정보  : " + board.toString() );
			*/
			
			if( board.getNO() > 0 ){
				
				System.out.println("  -> 답급 여부 확인 ");
				
				int ref = board.getREF();
				int ref_step = board.getREF_STEP();
				int ref_level = board.getREF_LEVEL();
				
				Map<String,Object> daoMap2 = new HashMap<>();
				daoMap2.put("ref", ref);
				daoMap2.put("ref_step", ref_step);
				daoMap2.put("ref_level", ref_level);
				
				List<BoardDTO> dtos = dao.delete_board_02(daoMap2);
				
				// 답글이 없을 경우 
				if( dtos.size() == 0 ){
					
					System.out.println("  -> 답글 없음 DELETE 실행 ");
				
					dcnt = dao.delete_board_03(daoMap2);
					dcnt = dao.delete_board_04(no);
					
					model.addAttribute("dcnt", dcnt);
					
					System.out.println("  -> 삭제된 개수 dcnt :" + dcnt );
				
				// 답글이 있을 경우
				} else if ( dtos.size() > 0 ){
					
					dcnt =-1;
					System.out.println("  -> 답글 개수  :" + dtos.size() );
					
				} else {
					
					dcnt =-1;
					System.out.println("  -> 답글 여부 확인중 오류 발생 " );
					
				}
			} 
			model.addAttribute("dcnt", dcnt);
		} 
		
		
	}

}
