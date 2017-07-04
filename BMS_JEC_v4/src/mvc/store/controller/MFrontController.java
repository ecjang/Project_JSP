package mvc.store.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import mvc.store.service.a_main.service.Main_01_Serch_Service;
import mvc.store.service.a_main.service.Main_02_Detail_Service;
import mvc.store.service.b_login.service.Login_02_Join_Form_Service;
import mvc.store.service.b_login.service.Login_03_ConfirmId_Service;
import mvc.store.service.b_login.service.Login_04_Join_Pro_Service;
import mvc.store.service.b_login.service.Login_05_Login_Service;
import mvc.store.service.b_login.service.Login_06_Logout_Service;
import mvc.store.service.b_login.service.Login_07_Delete_Service;
import mvc.store.service.b_login.service.Login_08_Modify_View_Service;
import mvc.store.service.b_login.service.Login_09_Modify_Pro_Service;
import mvc.store.service.c_order.service.Cart_01_View_Service;
import mvc.store.service.c_order.service.Cart_02_Pro_Service;
import mvc.store.service.c_order.service.Cart_03_Cartnum_Service;
import mvc.store.service.c_order.service.Cart_04_Cartdel_Service;
import mvc.store.service.c_order.service.Cart_05_Cartall_Service;
import mvc.store.service.c_order.service.Cart_06_Order_Service;
import mvc.store.service.c_order.service.Order_01_View_Service;
import mvc.store.service.c_order.service.Order_02_Pro_Service;
import mvc.store.service.c_order.service.Order_03_State_Service;
import mvc.store.service.d_book.service.Book_01_List_Service;
import mvc.store.service.d_book.service.Book_02_Detail_Service;
import mvc.store.service.d_book.service.Book_03_Write_Form_Service;
import mvc.store.service.d_book.service.Book_04_Write_Pro_Service;
import mvc.store.service.d_book.service.Book_05_Modify_From_Service;
import mvc.store.service.d_book.service.Book_06_Modify_Pro_Service;
import mvc.store.service.d_book.service.Book_07_Delete_Service;
import mvc.store.service.e_board.service.Board_01_List_Service;
import mvc.store.service.e_board.service.Board_02_Detail_Service;
import mvc.store.service.e_board.service.Board_03_Write_Form_Service;
import mvc.store.service.e_board.service.Board_04_Write_Pro_Service;
import mvc.store.service.e_board.service.Board_05_Modify_From_Service;
import mvc.store.service.e_board.service.Board_06_Modify_View_Service;
import mvc.store.service.e_board.service.Board_07_Modify_Pro_Service;
import mvc.store.service.e_board.service.Board_08_Delete_From_Service;
import mvc.store.service.e_board.service.Board_09_Delete_Pro_Service;
import mvc.store.service.f_admin.service.Admin_01_Pro_Service;



@WebServlet("*.do")  // 모든 jsp파일이 여기로 접근함
public class MFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MFrontController() { super(); }
    String view = null;
   
    
	// 00. actionDo
	public void actionDo(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
    	System.out.println("");	// blank
		req.setCharacterEncoding("UTF-8");
		
		String uri = req.getRequestURI();   				// /BMS_JEC_v3/test.do  
		String contextPath = req.getContextPath() ;   		// /BMS_JEC_v3
		String url = uri.substring(contextPath.length());  	// /test.do 가져오기   
		
		
		req.setAttribute("url",url);
		urlsplite(req,res);									// 	m1, header, main.do
		
		if ( req.getParameterNames() != null ){				// 	?num=20&pageNum=10....
			urlparam(req,res);		
		}
		
		

		String[] viewlist= (String[]) req.getAttribute("viewlist");
		
		// 유효성 검사 .do 붙어야만 진행 
		if( viewlist[viewlist.length-1].contains(".do") ){
		
			switch ( viewlist[1] ){
			
			case "index" :{			// m1_header_main.do
				System.out.println(" Select Index... ");
				index(req,res);	// index
				break;
				}
			
			case "header" : {		// m2_header_login.do
				System.out.println(" Select header... ");
				Header(viewlist,req,res); 
				break;
				}
			
			case "main" : {			// m1_header_main.do
				System.out.println(" Select main... ");
				Main(viewlist,req,res); 
				break;
				}
			
			case "login" : {		// m2_header_login.do
				System.out.println(" Select login... ");
				login(viewlist,req,res); 
				break;
				}
			
			case "book" : {			// 01_book_list.do
				System.out.println(" Select Book... ");
				Book(viewlist,req,res); 
				break;
				}
			
			case "board" : {		// 01_board_list.do
				System.out.println(" Select Board... ");
				Board(viewlist,req,res); 
				break;
				}
			
			case "order" : {		// 01_order_cart.do
				System.out.println(" Select Order... ");
				Order(viewlist,req,res); 
				break;
				}
			
			default:
	             System.out.println("Default...");
	             break;
				}
			
			} else {
				System.out.println("invalid URI...");
			}
		
		
		
		// m3_header_m3_order.do
		// 
		// m5_header_board.do
		// m6_header_admin
		
		} 
	
	
	/*-----------------------------------------------------*/
	

	// 01. index
	public void index( HttpServletRequest req, HttpServletResponse res )
		throws ServletException , IOException {
		
		Main_01_Serch_Service service = new Main_01_Serch_Service();
		req.setAttribute("view", service.service(req, res));
		/*req.setAttribute("view", "/_Store/View/01_Main/01_Main_Serch.jsp");*/
		dp(req,res);
	}
	
	
	// 02. Header
	public void Header ( String[] viewlist , HttpServletRequest req, HttpServletResponse res )
		throws ServletException , IOException {
		
		/*System.out.println("header로 넘어옴 : " + viewlist[2] );*/
		
		switch( viewlist[2] ){	// m1, m2, m3, ...
		
		case "main.do" : {
			System.out.println(" m1_header_main.do");
			Main_01_Serch_Service service = new Main_01_Serch_Service();
			req.setAttribute("view", service.service(req, res));
			dp(req,res);
			break;
			}
		
		
		case "login.do" : {
			System.out.println(" m2_header_login.do");
			req.setAttribute("view", "/_Store/View/02_Login/01_Login_Main.jsp");
			dp(req,res);
			break;
			}
		
		case "cart.do" : {
			System.out.println(" m3_header_order.do");
			Cart_01_View_Service service = new Cart_01_View_Service();
			req.setAttribute("view", service.service(req, res));
			dp(req,res);
			break;
			}
		
		
		case "order.do" : {
			System.out.println(" m3_header_order.do");
			Order_01_View_Service service = new Order_01_View_Service();
			req.setAttribute("view", service.service(req, res));
			dp_view(req,res);
			break;
			}
		
		
		case "book.do" : {
			System.out.println(" m4_header_book.do");
			Book_01_List_Service service = new Book_01_List_Service();
			req.setAttribute("view", service.service(req, res));
			dp(req,res);
			break;
			}
		
		case "board.do" : {
			System.out.println(" m5_header_board.do");
			req.setAttribute("kind", "NOTICE");
			Board_01_List_Service service = new Board_01_List_Service();
			req.setAttribute("view", service.service(req, res));
			dp(req,res);
			break;
			}
		
		// 게시판 종류별로 분기 
		
		case "admin.do" : {
			System.out.println(" m6_header_admin.do");
			/*req.setAttribute("view", "/_Store/View/06_Admin/01_Admin_Main.jsp");*/
			
			Admin_01_Pro_Service service = new Admin_01_Pro_Service();
			req.setAttribute("view", service.service(req, res));
			dp(req,res);
			break;
			}
		

		case "board-m1.do" : {
			System.out.println(" m7_header_board-m1.do");
			req.setAttribute("kind", "NOTICE");
			Board_01_List_Service service = new Board_01_List_Service();
			req.setAttribute("view", service.service(req, res));
			dp(req,res);
			break;
			}
		
		case "board-m2.do" : {
			System.out.println(" m8_header_board-m2.do");
			req.setAttribute("kind", "QnA");
			Board_01_List_Service service = new Board_01_List_Service();
			req.setAttribute("view", service.service(req, res));
			dp(req,res);
			break;
			}
		
		case "board-m3.do" : {
			System.out.println(" m9_header_board-m3.do");
			req.setAttribute("kind", "FAQ");
			Board_01_List_Service service = new Board_01_List_Service();
			req.setAttribute("view", service.service(req, res));
			dp(req,res);
			break;
			}
		
		case "board-m4.do" : {
			System.out.println(" m10_header_board-m4.do");
			req.setAttribute("kind", "EVENT");
			Board_01_List_Service service = new Board_01_List_Service();
			req.setAttribute("view", service.service(req, res));
			dp(req,res);
			break;
			}
		
		
		
		default :{
			System.out.println(" header 입력값 오류 ");
			break;
			}
		}
		
	}
	
	
	// 03. Main
	private void Main(String[] viewlist, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			
	switch( viewlist[2] ){	
		
		case "list.do" : {
			System.out.println(" 01_Main_Serch");
			Main_01_Serch_Service service = new Main_01_Serch_Service();
			req.setAttribute("view", service.service(req, res));
			dp(req,res);
			break;
			}
		
		case "detail.do" : {
			System.out.println(" 02_Main_Detail");
			Main_02_Detail_Service service = new Main_02_Detail_Service();
			req.setAttribute("view", service.service(req, res));
			dp(req,res);
			break;
			}
	
		default :{
			System.out.println(" main 입력값 오류 ");
			break;
			}
		}
		
	}
	
	
	// 04. login
	private void login(String[] viewlist, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		switch( viewlist[2] ){	
		
		case "join.do" : {
			System.out.println(" Login_02_Join_Form_Service");
			Login_02_Join_Form_Service service = new Login_02_Join_Form_Service();
			req.setAttribute("view", service.service(req, res));
			dp(req,res);
			break;
			}
		
		
		case "confirmId.do" : {
			System.out.println(" 03_Login_Join_ConfirmId");
			Login_03_ConfirmId_Service service = new Login_03_ConfirmId_Service();
			req.setAttribute("view", service.service(req, res) );
			dp(req,res);
			break;
			}
		
		
		case "joinpro.do" : {
			System.out.println(" 03_Login_Join_ConfirmId");
			Login_04_Join_Pro_Service service = new Login_04_Join_Pro_Service();
			req.setAttribute("view", service.service(req, res));
			dp(req,res);
			break;
			}
		
		
		case "loginpro.do" : {
			System.out.println(" Login_03_Join_Pro_Service");
			Login_05_Login_Service service = new Login_05_Login_Service();
			req.setAttribute("view", service.service(req, res));
			dp(req,res);
			break;
			}
		
		case "logout.do" : {
			System.out.println(" Login_04_Logout_Service");
			Login_06_Logout_Service service = new Login_06_Logout_Service();
			req.setAttribute("view", service.service(req, res));
			dp(req,res);
			break;
			}
		
		
		case "deleteform.do" : {
			System.out.println(" 07_Login_Modify_Form");
			req.setAttribute("view", "/_Store/View/02_Login/05_Login_Delete_Form.jsp" );
			dp(req,res);
			break;
			}


		case "deletepro.do" : {
			System.out.println(" Login_05_Delete_Service");
			Login_07_Delete_Service service = new Login_07_Delete_Service();
			req.setAttribute("view", service.service(req, res));
			dp(req,res);
			break;
			}
		
		
		case "modifyform.do" : {
			System.out.println(" 07_Login_Modify_Form");
			req.setAttribute("view", "/_Store/View/02_Login/07_Login_Modify_Form.jsp" );
			dp(req,res);
			break;
			}

		case "modifyView.do" : {
			System.out.println(" Login_06_Modify_View_Service");
			Login_08_Modify_View_Service service = new Login_08_Modify_View_Service();
			req.setAttribute("view", service.service(req, res));
			dp(req,res);
			break;
			}
	
		case "modifyPro.do" : {
			System.out.println(" Login_07_Modify_Pro_Service");
			Login_09_Modify_Pro_Service service = new Login_09_Modify_Pro_Service();
			req.setAttribute("view", service.service(req, res));
			dp(req,res);
			break;
			}
		
		
		default :{
			System.out.println(" login 입력값 오류 ");
			break;
			}
		}
		
	
	}
	
	
	// 05. Order
	private void Order(String[] viewlist, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	switch( viewlist[2] ){	
		
		case "cart.do" : {
			System.out.println(" 02_Main_Detail ");
			Cart_01_View_Service service = new Cart_01_View_Service();
			req.setAttribute("view", service.service(req, res));
			dp(req,res);
			break;
			}
		
		case "cartpro.do" : {
			System.out.println(" Order_02_Cart_Pro_Service ");
			Cart_02_Pro_Service service = new Cart_02_Pro_Service();
			req.setAttribute("view", service.service(req, res));
			dp_view(req,res);
			break;
			}
		
		case "cartnum.do" : {
			System.out.println(" 01_Order_Cart ");
			Cart_03_Cartnum_Service service = new Cart_03_Cartnum_Service();
			req.setAttribute("view", service.service(req, res));
			dp(req,res);
			break;
			}
		
		case "cartdel.do" : {
			System.out.println(" 01_Order_Cart ");
			Cart_04_Cartdel_Service service = new Cart_04_Cartdel_Service();
			req.setAttribute("view", service.service(req, res));
			dp(req,res);
			break;
			}
		

		case "cartall.do" : {
			System.out.println(" 01_Order_Cart ");
			Cart_05_Cartall_Service service = new Cart_05_Cartall_Service();
			req.setAttribute("view", service.service(req, res));
			dp(req,res);
			break;
			}
		
		case "cartclean.do" : {
			System.out.println(" 01_Order_Cart ");
			Cart_05_Cartall_Service service = new Cart_05_Cartall_Service();
			req.setAttribute("view", service.service(req, res));
			dp(req,res);
			break;
			}
		
		case "toorder.do" : {
			System.out.println(" 01_Order_Cart ");
			Cart_06_Order_Service service = new Cart_06_Order_Service();
			req.setAttribute("view", service.service(req, res));
			dp(req,res);
			break;
			}
		
		case "order.do" : {
			System.out.println(" 01_Order_Cart ");
			
			/*
			if(req.getAttribute("state")!= null){
				String state =  (String) req.getAttribute("state");
				System.out.println(state);
				req.setAttribute("state", state);
			}
			*/
			
			Order_01_View_Service service = new Order_01_View_Service();
			req.setAttribute("view", service.service(req, res));
			dp_view(req,res);
			break;
			}
		
		case "orderpro.do" : {
			System.out.println(" Order_01_View_Service ");
			Order_02_Pro_Service service = new Order_02_Pro_Service();
			req.setAttribute("view", service.service(req, res));
			dp_view(req,res);
			break;
			}
		
		case "orderproview.do" : {
			System.out.println(" Order_02_Pro_Service ");
			req.setAttribute("view", "_Store/View/03_Order/02_Order_Order.jsp");
			break;
			}
		
		case "state.do" : {
			System.out.println(" 01_Order_Cart ");
			Order_03_State_Service service = new Order_03_State_Service();
			req.setAttribute("view", service.service(req, res));
			dp_view(req,res);
			break;
			}
		
		default :{
			System.out.println(" main 입력값 오류 ");
			break;
			}
		}
	
	}
	
	
	// 06. Book
	private void Book(String[] viewlist, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			
	switch( viewlist[2] ){	
		
		case "list.do" : {
			System.out.println(" Book_01_List_Service ");
			Book_01_List_Service service = new Book_01_List_Service();
			req.setAttribute("view", service.service(req, res));
			dp(req,res);
			break;
			}
		
		case "detail.do" : {
			System.out.println(" 01_Book_List_Main.jsp");
			Book_02_Detail_Service service = new Book_02_Detail_Service();
			req.setAttribute("view", service.service(req, res));
			dp(req,res);
			break;
			}
		
		case "write.do" : {
			System.out.println(" 01_Book_List.jsp");
			Book_03_Write_Form_Service service = new Book_03_Write_Form_Service();
			req.setAttribute("view", service.service(req, res));
			dp(req,res);
			break;
			}
		
		case "writepro.do" : {
			System.out.println(" 03_Book_Write_Form.jsp");
			Book_04_Write_Pro_Service service = new Book_04_Write_Pro_Service();
			req.setAttribute("view", service.service(req, res));
			dp(req,res);
			break;
			}
		
		case "modify.do" : {
			System.out.println(" 01_Book_List_Main.jsp");
			Book_05_Modify_From_Service service = new Book_05_Modify_From_Service();
			req.setAttribute("view", service.service(req, res));
			dp(req,res);
			break;
			}
		
		case "modifypro.do" : {
			System.out.println(" Modify_01_From_Handler.jsp");
			Book_06_Modify_Pro_Service service = new Book_06_Modify_Pro_Service();
			req.setAttribute("view", service.service(req, res));
			dp(req,res);
			break;
			}
		
		case "delete.do" : {
			System.out.println(" Modify_01_From_Handler.jsp");
			Book_07_Delete_Service service = new Book_07_Delete_Service();
			req.setAttribute("view", service.service(req, res));
			dp(req,res);
			break;
			}
		
		
		
		
		
		
		
		
		
		default :{
			System.out.println(" main 입력값 오류 ");
			break;
			}
		}
		
	}
	
	
	// 07. Board
	private void Board(String[] viewlist, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String kind = req.getParameter("kind");
		req.setAttribute("kind",kind);
		
		switch( viewlist[2] ){	
		
			case "list.do" : {
				System.out.println(" Board_01_List_Service ");
				Board_01_List_Service service = new Board_01_List_Service();
				req.setAttribute("view", service.service(req, res));
				dp(req,res);
				break;
				}
			
			case "detail.do" : {
				System.out.println(" 01_Board_List");
				Board_02_Detail_Service service = new Board_02_Detail_Service();
				req.setAttribute("view", service.service(req, res));
				dp(req,res);
				break;
				}
		
			case "write.do" : {
				System.out.println(" 01_Board_List & 02_Board_Detail");
				Board_03_Write_Form_Service service = new Board_03_Write_Form_Service();
				req.setAttribute("view", service.service(req, res));
				dp(req,res);
				break;
				}
			
			case "wrtiepro.do" : {
				System.out.println(" 01_Board_List & 02_Board_Detail");
				Board_04_Write_Pro_Service service = new Board_04_Write_Pro_Service();
				req.setAttribute("view", service.service(req, res));
				dp(req,res);
				break;
				}
			
			case "modify.do" : {
				System.out.println(" 02_Board_Detail");
				Board_05_Modify_From_Service service = new Board_05_Modify_From_Service();
				req.setAttribute("view", service.service(req, res));
				dp(req,res);
				break;
				}
			
			case "modifyview.do" : {
				System.out.println(" 05_Board_Modify_From ");
				Board_06_Modify_View_Service service = new Board_06_Modify_View_Service();
				req.setAttribute("view", service.service(req, res));
				dp(req,res);
				break;
				}
			
			case "modifypro.do" : {
				System.out.println(" 06_Board_Modify_View ");
				Board_07_Modify_Pro_Service service = new Board_07_Modify_Pro_Service();
				req.setAttribute("view", service.service(req, res));
				dp(req,res);
				break;
				}
			
			case "delete.do" : {
				System.out.println(" 06_Board_Modify_View ");
				Board_08_Delete_From_Service service = new Board_08_Delete_From_Service();
				req.setAttribute("view", service.service(req, res));
				dp(req,res);
				break;
				}
			

			case "deletepro.do" : {
				System.out.println(" 06_Board_Modify_View ");
				Board_09_Delete_Pro_Service service = new Board_09_Delete_Pro_Service();
				req.setAttribute("view", service.service(req, res));
				dp(req,res);
				break;
				}
			
			
			default :{
				System.out.println(" main 입력값 오류 ");
				break;
				}
			}
			
		}

	
	
	
	
	

	// 98. Dispatcher_view
	public void dp_view( HttpServletRequest req, HttpServletResponse res )
			throws ServletException, IOException {
		
		view = (String) req.getAttribute("view");
		req.setAttribute("view", view);
		System.out.println(" dp view : " + view);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher(view);
		dispatcher.forward(req,res);
		
	}
	
	
	// 99. Dispatcher_main
	public void dp( HttpServletRequest req, HttpServletResponse res )
			throws ServletException, IOException {
		
		/*System.out.println("dispatcher 실행 : " + view);*/
		
		view = (String) req.getAttribute("view");
		req.setAttribute("view", view);
		System.out.println(" dp view : " + view);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("_Store/Main.jsp");
		dispatcher.forward(req,res);
		
		/*
		RequestDispatcher dispatcher;
		
		if( view.contains("order_orderproview") ){
			dispatcher = 
			req.getRequestDispatcher("_Store/View/03_Order/02_Order_Order.jsp");  
		
		}else{
			dispatcher = 
			req.getRequestDispatcher("_Store/Main.jsp");  
		}
		
		dispatcher.forward(req,res);
		*/
	}
	
	
	/*-----------------------------------------------------*/
	
	
    // url 01 : Splite
    public void urlsplite( HttpServletRequest req, HttpServletResponse res) throws UnsupportedEncodingException {
    
		
		String url = (String)req.getAttribute("url");   
		String[] viewlist = url.split("_");					
		
		//split 테스트
		for( String test_viewpage : viewlist  ){
			System.out.print(test_viewpage+", ");
		} 	
		
		req.setAttribute("viewlist", viewlist);

    }
    
    
    // url 02 : Parameter
    public void urlparam( HttpServletRequest req, HttpServletResponse res)  throws UnsupportedEncodingException {
    	
    	String url = (String)req.getAttribute("url");   
    	
		Enumeration<String> paramtemp1 = req.getParameterNames();	// param splite 
		String paramtemp2 = "";
		
		while( paramtemp1.hasMoreElements() ){
			String name = (String)paramtemp1.nextElement();
			String value = req.getParameter(name);
			paramtemp2 += name + "=" + value + "&" ;
		}
		
		String viewvalue = "?" + paramtemp2;						
		viewvalue = viewvalue.substring(0,viewvalue.length()-1);	// 뒤에 ? 제거
		
		// 확인용 
		System.out.println(viewvalue);								// ?num=20&pageNum=10....
		
		req.setAttribute("viewvalue", viewvalue);
		
		
    }
    
    
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		actionDo(req, res);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		actionDo(req, res);
	}
	

	
	/*-----------------------------------------------------*/
	

	
}



