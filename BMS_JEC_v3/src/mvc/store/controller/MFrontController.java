package mvc.store.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.store.board.handler.Board_Delete_02_Pro_Handler;
import mvc.store.admin.handler.Admin_02_Write_Pro_Handler;
import mvc.store.admin.handler.Admin_03_Modify_Pro_Handler;
import mvc.store.admin.handler.Admin_04_Delete_Pro_Handler;
import mvc.store.admin.handler.Admin_05_Pay_List_Pro_Handler;
import mvc.store.admin.handler.Admin_01_main_Pro_Handler;
import mvc.store.board.handler.Board_Delete_01_From_Handler;
import mvc.store.board.handler.Board_Detail_01_Pro_Handler;
import mvc.store.board.handler.Board_List_01_Pro_Handler;
import mvc.store.board.handler.Board_Modify_01_From_Handler;
import mvc.store.board.handler.Board_Modify_02_View_Handler;
import mvc.store.board.handler.Board_Modify_03_Pro_Handler;
import mvc.store.board.handler.Board_Write_01_Form_Handler;
import mvc.store.board.handler.Board_Write_02_Pro_Handler;
import mvc.store.book.handler.Delete_01_Pro_Handler;
import mvc.store.book.handler.Detail_01_Form_Handler;
import mvc.store.book.handler.List_01_Pro_Handler;
import mvc.store.book.handler.Modify_01_From_Handler;
import mvc.store.book.handler.Modify_02_Pro_Handler;
import mvc.store.book.handler.Modify_01_From_Handler;
import mvc.store.book.handler.Write_01_From_Handler;
import mvc.store.book.handler.Write_02_Pro_Handler;
import mvc.store.guest.handler.Guest_Cart_02_Pro_Handler;
import mvc.store.guest.handler.Guest_Cart_03_Ordernum_Pro_Handler;
import mvc.store.guest.handler.Guest_Cart_04_Orderdel_Pro_Handler;
import mvc.store.guest.handler.Guest_Cart_05_Orderconfirm_Pro_Handler;
import mvc.store.guest.handler.Guest_Cart_06_allbuy_Pro_Handler;
import mvc.store.guest.handler.Guest_Order_01_View_Handler;
import mvc.store.guest.handler.Guest_Order_02_Pro_Handler;
import mvc.store.guest.handler.Guest_Cart_01_View_Handler;
import mvc.store.main.handler.Main_Search_01_main_Pro_Handler;
import mvc.store.main.handler.Main_Search_02_Detail_Pro_Handler;
import mvc.store.member.handler.ConfirmId_Handler;
import mvc.store.member.handler.Delete_Pro_Handler;
import mvc.store.member.handler.Input_Form_Handler;
import mvc.store.member.handler.Input_Pro_Handler;
import mvc.store.member.handler.Login_Pro_Handler;
import mvc.store.member.handler.Logout_Pro_Handler;
import mvc.store.member.handler.Modify_Pro_Handler;
import mvc.store.member.handler.Modify_View_Handler;


@WebServlet("*.do")  // 모든 jsp파일이 여기로 접근함
public class MFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MFrontController() { super();  }

    //1단계. HTTP 요청 받음
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		actionDo(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		actionDo(req, res);
	}
	
	public void actionDo(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {

		System.out.println();
		req.setCharacterEncoding("UTF-8");
		
		String viewPage = null;
		String uri = req.getRequestURI();   				//	/BMS_JEC_v3/test.do  
		String contextPath = req.getContextPath() ;   		//  /BMS_JEC_v3
		String url = uri.substring(contextPath.length());  	//  /test.do 가져오기   
		
		
		/* 상단  메뉴   -----------------------------------------------------*/
	
		if (url.contains("/index.do")){
			System.out.println("메인으로 이동");
			viewPage = "_Store/Main.jsp";
		
		} else if ( url.contains("/header_m1.do") ){
			System.out.println("메뉴1. 메인으로 이동 ");
			viewPage = "/_Store/Main.jsp"; 
		
		} else if ( url.contains("/header_m2.do") ){
			System.out.println("메뉴2. 로그인으로 이동 ");
			viewPage = "/_Store/View/Login/01_Login_Main.jsp"; 
		
		} else if ( url.contains("/header_m3.do") ){
			System.out.println("메뉴3. 도서목록으로 이동 ");
			viewPage = "/_Store/View/Book/01_Book_List_Main.jsp"; 
		
		} else if ( url.contains("/header_m4.do") ){
			System.out.println("메뉴4. 게시판으로 이동 ");
			viewPage = "/_Store/View/Board/01_Board_Main.jsp"; 
		
		} else if ( url.contains("/header_m5.do") ){
			System.out.println("메뉴5. 관리자 메뉴로 이동  ");
			viewPage = "admin_mian.do"; 
			/*viewPage = "/_Store/View/Admin/01_Admin_Main.jsp"; */
		
		} else if ( url.contains("/main.do") ){
			System.out.println("메인으로 이동  ");
			viewPage = "/_Store/View/Main/01_Serch_Main.jsp"; 
		
		}
		
		
		/* 회원 가입   -----------------------------------------------------*/
		if (url.contains("/inputForm.do")){
			System.out.println("회원가입 버튼 클릭 -> 02_Login_Join_Form");
			Input_Form_Handler handler = new Input_Form_Handler();
			viewPage = handler.process(req, res);

		// 회원가입에서 아이디 중복확인 페이지
		} else if (url.contains("/confirmId.do")){
			System.out.println("중복확인 버튼 클릭 -> ConfirmIdHandler ");
			ConfirmId_Handler handler = new ConfirmId_Handler();
			viewPage = handler.process(req, res);
		
		// 회원 가입처리 페이지
		} else if ( url.equals("/inputPro.do")){
			System.out.println("회원가입 처리 -> Input_Pro_Handler");
			Input_Pro_Handler handler = new Input_Pro_Handler();
			viewPage = handler.process(req, res);

		// 회원 가입처리 페이지
		} else if ( url.equals("/mainSuccess.do")){
			System.out.println("회원가입 완료 -> 01_Login_Main");
			req.setAttribute("cnt", 1);		// 회원가입을 축하합니다.
			viewPage = "/_Store/View/Login/01_Login_Main.jsp"; 
			/*viewPage = "/_Store/View/View_login.jsp"; */
			
		/* 로그인   -----------------------------------------------------*/		
		} else if(url.contains("/loginPro.do")) { 
			System.out.println("로그인 버튼 클릭 : Login_Pro_Handler ");
			Login_Pro_Handler handler = new Login_Pro_Handler();
			viewPage = handler.process(req, res);	
		
		/* 회원탈퇴   -----------------------------------------------------*/		
		} else if(url.contains("/deleteForm.do")) { 
			System.out.println("회원탈퇴 버튼 클릭 -> 05_Login_Delete_Form");
			viewPage = "/_Store/View/Login/05_Login_Delete_Form.jsp"; 
		
		} else if(url.equals("/deletePro.do")) { 
			Delete_Pro_Handler handler = new Delete_Pro_Handler();
			viewPage = handler.process(req, res);	
		
		/* 정보수정   -----------------------------------------------------*/		
		} else if(url.contains("/modifyForm.do")) { 
			System.out.println("정보수정 버튼 클릭 -> 07_Login_Modify_Form");
			viewPage = "/_Store/View/Login/07_Login_Modify_Form.jsp"; 
			
		} else if(url.equals("/modifyView.do")) { 
			System.out.println("정보수정 양식  -> Modify_View_Handler");
			Modify_View_Handler handler = new Modify_View_Handler();
			viewPage = handler.process(req, res);	 
		
		} else if(url.equals("/modifyPro.do")) { 
			System.out.println("수정정보 양식  -> Modify_Pro_Handler");
			Modify_Pro_Handler handler = new Modify_Pro_Handler();
			viewPage = handler.process(req, res);	 
		
		/* 로그아웃 처리 페이지  -----------------------------------------------------*/
		} else if(url.equals("/logout.do")) {	// inputPro 에서 넘겨 받음
			System.out.println("로그아웃 버튼 클릭  -> Logout_Pro_Handler");
			Logout_Pro_Handler handler = new Logout_Pro_Handler();
			viewPage = handler.process(req, res);	//요청주소
		}
		
		
		/* 도서목록 게시판   -----------------------------------------------------*/
		if (url.contains("/book_list.do")){
			System.out.println("도서목록으로 이동 " );
			List_01_Pro_Handler handler = new List_01_Pro_Handler();
			viewPage = handler.execute(req, res);
		
		} else if ( url.contains("/book_content.do") ){
			System.out.println("세부페이지로 이동 ");
			Detail_01_Form_Handler han = new Detail_01_Form_Handler();
			viewPage = han.execute(req, res);
		
		// 도서 추가 
		} else if ( url.contains("/book_write.do") ){
			System.out.println("신간도서 입력" );
			Write_01_From_Handler han = new Write_01_From_Handler();
			viewPage = han.execute(req, res);
		
		} else if ( url.contains("/book_write_Pro.do") ){
			System.out.println("신간도서 추가" );
			Write_02_Pro_Handler han = new Write_02_Pro_Handler();
			viewPage = han.execute(req, res);
		
		// 도서 수정
		} else if ( url.contains("/book_modify_from.do") ){
			System.out.println("도서정보 수정 버튼 클릭 ");
			Modify_01_From_Handler han = new Modify_01_From_Handler();
			viewPage = han.execute(req, res);
		
		} else if ( url.contains("/book_modify_pro.do") ){
			System.out.println("도서정보 수정 ");
			Modify_02_Pro_Handler han = new Modify_02_Pro_Handler();
			viewPage = han.execute(req, res);
		
		// 도서 삭제
		} else if ( url.contains("/book_delete_pro.do") ){
			System.out.println("삭제버튼 클릭 ");
			Delete_01_Pro_Handler han = new Delete_01_Pro_Handler();
			viewPage = han.execute(req, res);	

		}
		
		
		/* 일반 게시판  : 헤드 메뉴 페이지 이동  -----------------------------------------------------*/
		
		if (url.contains("/Board_Kind_m1.do")){
			System.out.println("공지사항으로 이동" );
			Board_List_01_Pro_Handler handler = new Board_List_01_Pro_Handler();
			viewPage = handler.write(req, res , "NOTICE");
		
		} else if (url.contains("/Board_Kind_m2.do")){
			System.out.println("Q&A로 이동" );
			Board_List_01_Pro_Handler handler = new Board_List_01_Pro_Handler();
			viewPage = handler.write(req, res , "QnA");
			
		} else if (url.contains("/Board_Kind_m3.do")){
			System.out.println("FAQ로 이동" );
			Board_List_01_Pro_Handler handler = new Board_List_01_Pro_Handler();
			viewPage = handler.write(req, res , "FAQ");
			
		} else if (url.contains("/Board_Kind_m4.do")){
			System.out.println("EVENT로 이동" );
			Board_List_01_Pro_Handler handler = new Board_List_01_Pro_Handler();
			viewPage = handler.write(req, res , "EVENT");
		}
		
		
		/* 일반 게시판   -----------------------------------------------------*/
		
		// 게시판 리스트
		if (url.contains("/board_list.do")){
			System.out.println("전체 게시판으로 이동" );
			String kind = req.getParameter("kind");
			Board_List_01_Pro_Handler handler = new Board_List_01_Pro_Handler();
			viewPage = handler.write(req, res, kind);
		
		// 게시물 세부정보
		} else if (url.contains("/board_detail.do")){
			System.out.println("세부페이지로 이동" );
			String kind = req.getParameter("kind");
			Board_Detail_01_Pro_Handler handler = new Board_Detail_01_Pro_Handler();
			viewPage = handler.write(req, res , kind);	
		
		// 게시물 수정
		} else if (url.contains("/board_modify_form.do")){
			System.out.println("수정버튼 클릭" );
			String kind = req.getParameter("kind");
			Board_Modify_01_From_Handler handler = new Board_Modify_01_From_Handler();
			viewPage = handler.write(req, res , kind);	
		
		} else if (url.contains("/board_modify_view.do")){
			System.out.println("수정 정보 입력 페이지" );
			String kind = req.getParameter("kind");
			Board_Modify_02_View_Handler handler = new Board_Modify_02_View_Handler();
			viewPage = handler.write(req, res , kind);	
		
		} else if (url.contains("/board_modify_pro.do")){
			System.out.println("수정 결과 페이지" );
			String kind = req.getParameter("kind");
			Board_Modify_03_Pro_Handler handler = new Board_Modify_03_Pro_Handler();
			viewPage = handler.write(req, res , kind);		
		
		// 게시물 삭제
		} else if (url.contains("/board_delete_form.do")){
			System.out.println("삭제 버튼 클릭" );
			String kind = req.getParameter("kind");
			Board_Delete_01_From_Handler handler = new Board_Delete_01_From_Handler();
			viewPage = handler.write(req, res , kind);		
		
		} else if (url.contains("/board_delete_pro.do")){
			System.out.println("삭제 결과 페이지" );
			String kind = req.getParameter("kind");
			Board_Delete_02_Pro_Handler handler = new Board_Delete_02_Pro_Handler();
			viewPage = handler.write(req, res , kind);		
		
		// 게시물 작성	
		} else if (url.contains("/board_write_from.do")){
			System.out.println("작성버튼 클릭" );
			String kind = req.getParameter("kind");
			Board_Write_01_Form_Handler handler = new Board_Write_01_Form_Handler();
			viewPage = handler.write(req, res , kind);		
			
		} else if (url.contains("/board_write_pro.do")){
			System.out.println("작성 양식 페이지" );
			String kind = req.getParameter("kind");
			Board_Write_02_Pro_Handler handler = new Board_Write_02_Pro_Handler();
			viewPage = handler.write(req, res , kind);		
			
		}
		
		
		/* 검색바   -----------------------------------------------------*/
		// 도서 제목 검색
		if (url.contains("/search_list.do")){
			System.out.println("도서 제목 검색" );
			Main_Search_01_main_Pro_Handler handler = new Main_Search_01_main_Pro_Handler();
			viewPage = handler.mainexecute(req, res);
		
		// 검색도서 상세페이지	
		} else if (url.contains("/search_detail.do")){
			System.out.println("도서 검색 상세페이지" );
			Main_Search_02_Detail_Pro_Handler handler = new Main_Search_02_Detail_Pro_Handler();
			viewPage = handler.mainexecute(req, res);			
			
		}
		
		
		
		/* 고객메뉴   -----------------------------------------------------*/
		
		
		if (url.contains("/guest_cart.do")){
			System.out.println("장바구니로 이동" );
			Guest_Cart_01_View_Handler handler = new Guest_Cart_01_View_Handler();
			viewPage = handler.orderexecute(req, res);
	
		} else if (url.contains("/guest_cart_pro.do")){
			System.out.println("장바구니 출력" );
			Guest_Cart_02_Pro_Handler handler = new Guest_Cart_02_Pro_Handler();
			viewPage = handler.orderexecute(req, res);
		
		} else if (url.contains("/guest_cart_ordernum_pro.do")){
			System.out.println("장바구니 수량조절" );
			Guest_Cart_03_Ordernum_Pro_Handler handler = new Guest_Cart_03_Ordernum_Pro_Handler();
			viewPage = handler.orderexecute(req, res);
		
		} else if (url.contains("/guest_cart_orderdel_pro.do")){
			System.out.println("장바구니 삭제버튼" );
			Guest_Cart_04_Orderdel_Pro_Handler handler = new Guest_Cart_04_Orderdel_Pro_Handler();
			viewPage = handler.orderexecute(req, res);
		
		} else if (url.contains("/guest_cart_orderconfirm_pro.do")){
			System.out.println("장바구니 주문버튼" );
			Guest_Cart_05_Orderconfirm_Pro_Handler handler = new Guest_Cart_05_Orderconfirm_Pro_Handler();
			viewPage = handler.orderexecute(req, res);
		
		} else if (url.contains("/guest_cart_allbuy_pro.do")){
			System.out.println("전체 주문하기" );
			Guest_Cart_06_allbuy_Pro_Handler handler = new Guest_Cart_06_allbuy_Pro_Handler();
			viewPage = handler.orderexecute(req, res);
		
		}
		
		/* 고객메뉴 : 주문 테이블    -----------------------------------------------------*/
			
		if (url.contains("/guest_order.do")){
			System.out.println("주문 테이블 이동" );
			Guest_Order_01_View_Handler handler = new Guest_Order_01_View_Handler();
			viewPage = handler.orderexecute(req, res);
			
		} else if (url.contains("/guest_order_state.do")){
				System.out.println("주문상태 변환");
				Guest_Order_02_Pro_Handler handler = new Guest_Order_02_Pro_Handler();
				viewPage = handler.orderexecute(req, res);
		}
		
		
		
		
		
		/* 관리자메뉴   -----------------------------------------------------*/
		
		if (url.contains("/admin_mian.do")){
			System.out.println("관리자페이지로 이동" );
			Admin_01_main_Pro_Handler handler = new Admin_01_main_Pro_Handler();
			viewPage = handler.supervise(req, res);
		
		} else if (url.contains("/admin_write.do")){
				System.out.println("관리자에서 도서추가 기능" );
				Admin_02_Write_Pro_Handler handler = new Admin_02_Write_Pro_Handler();
				viewPage = handler.supervise(req, res);
			
		} else if (url.contains("/admin_modify.do")){
			System.out.println("관리자에서 도서수정 기능" );
			Admin_03_Modify_Pro_Handler handler = new Admin_03_Modify_Pro_Handler();
			viewPage = handler.supervise(req, res);
		
		} else if (url.contains("/admin_modify.do")){
			System.out.println("관리자에서 도서삭제 기능" );
			Admin_04_Delete_Pro_Handler handler = new Admin_04_Delete_Pro_Handler();
			viewPage = handler.supervise(req, res);
		
		} else if (url.contains("/admin_pay_list.do")){
			System.out.println("관리자에서 도서삭제 기능" );
			Admin_05_Pay_List_Pro_Handler handler = new Admin_05_Pay_List_Pro_Handler();
			viewPage = handler.supervise(req, res);
		
		}   
			
			
			
		/* viewPage로 페이지 이동 설정  -----------------------------------------------------*/
		
		RequestDispatcher dispatcher = 
				req.getRequestDispatcher(viewPage);  //Dispatcher도 객체를 얻어와야 사용 가능
		dispatcher.forward(req,res);
		}
	
	
}



