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

import mvc.store.service.a_main.handler.Main_01_Serch_Service;
import mvc.store.service.a_main.handler.Main_02_Detail_Service;
import mvc.store.service.b_login.handler.Login_02_Join_Form_Service;
import mvc.store.service.b_login.handler.Login_03_ConfirmId_Service;
import mvc.store.service.b_login.handler.Login_04_Join_Pro_Service;
import mvc.store.service.b_login.handler.Login_05_Login_Service;
import mvc.store.service.b_login.handler.Login_06_Logout_Service;
import mvc.store.service.b_login.handler.Login_07_Delete_Service;
import mvc.store.service.b_login.handler.Login_08_Modify_View_Service;
import mvc.store.service.b_login.handler.Login_09_Modify_Pro_Service;
import mvc.store.service.d_book.handler.Book_01_List_Service;
import mvc.store.service.d_book.handler.Book_02_Detail_Service;
import mvc.store.service.d_book.handler.Book_03_Write_Form_Service;
import mvc.store.service.d_book.handler.Book_04_Write_Pro_Service;
import mvc.store.service.d_book.handler.Book_05_Modify_From_Service;
import mvc.store.service.d_book.handler.Book_06_Modify_Pro_Service;
import mvc.store.service.d_book.handler.Book_07_Delete_Service;
import mvc.store.service.e_board.handler.Board_01_List_Service;
import mvc.store.service.e_board.handler.Board_02_Detail_Service;
import mvc.store.service.e_board.handler.Board_03_Write_Form_Service;
import mvc.store.service.e_board.handler.Board_04_Write_Pro_Service;
import mvc.store.service.e_board.handler.Board_05_Modify_From_Service;
import mvc.store.service.e_board.handler.Board_06_Modify_View_Service;
import mvc.store.service.e_board.handler.Board_07_Modify_Pro_Service;
import mvc.store.service.e_board.handler.Board_08_Delete_From_Service;
import mvc.store.service.e_board.handler.Board_09_Delete_Pro_Service;



@WebServlet("*.do")  // ��� jsp������ ����� ������
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
		String url = uri.substring(contextPath.length());  	// /test.do ��������   
		
		
		req.setAttribute("url",url);
		urlsplite(req,res);									// 	m1, header, main.do
		
		if ( req.getParameterNames() != null ){				// 	?num=20&pageNum=10....
			urlparam(req,res);		
		}
		
		

		String[] viewlist= (String[]) req.getAttribute("viewlist");
		
		// ��ȿ�� �˻� .do �پ�߸� ���� 
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
		
		req.setAttribute("view", "/_Store/View/01_Main/01_Main_Serch.jsp");
		dp(req,res);
	}
	
	
	// 02. Header
	public void Header ( String[] viewlist , HttpServletRequest req, HttpServletResponse res )
		throws ServletException , IOException {
		
		/*System.out.println("header�� �Ѿ�� : " + viewlist[2] );*/
		
		switch( viewlist[2] ){	// m1, m2, m3, ...
		
		case "main.do" : {
			System.out.println(" m1_header_main.do");
			
			Main_01_Serch_Service service = new Main_01_Serch_Service();
			req.setAttribute("view", service.service(req, res));
			/*req.setAttribute("view", "/_Store/View/01_Main/01_Main_Serch.jsp");*/
			dp(req,res);
			break;
			}
		
		case "login.do" : {
			System.out.println(" m2_header_login.do");
			req.setAttribute("view", "/_Store/View/02_Login/01_Login_Main.jsp");
			dp(req,res);
			break;
			}
		
		case "order.do" : {
			System.out.println(" m3_header_m3_order.do");
			req.setAttribute("view", "/_Store/View/03_Order/01_Order_Cart.jsp");
			dp(req,res);
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
		
		case "admin.do" : {
			System.out.println(" m6_header_admin.do");
			req.setAttribute("view", "/_Store/View/06_Admin/01_Admin_Main.jsp");
			dp(req,res);
			break;
			}
		
		default :{
			System.out.println(" header �Է°� ���� ");
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
			System.out.println(" main �Է°� ���� ");
			break;
			}
		}
		
	}
	
	
	// 04. login
	private void login(String[] viewlist, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String viewvalue = (String) req.getAttribute("viewvalue");
		
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
			System.out.println(" login �Է°� ���� ");
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
			System.out.println(" main �Է°� ���� ");
			break;
			}
		}
		
	}
	
	
	// 07. Board
	private void Board(String[] viewlist, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		

		String viewvalue = (String) req.getAttribute("viewvalue");
		
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
				System.out.println(" main �Է°� ���� ");
				break;
				}
			}
			
		}
		
	
	// 99. Dispatcher
	public void dp( HttpServletRequest req, HttpServletResponse res )
			throws ServletException, IOException {
		
		/*System.out.println("dispatcher ���� : " + view);*/
		
		view = (String) req.getAttribute("view");
		req.setAttribute("view", view);
		System.out.println(" dp view : " + view);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("_Store/Main.jsp");  
		dispatcher.forward(req,res);
		
	}
	
	
	/*-----------------------------------------------------*/
	
	
    // url 01 : Splite
    public void urlsplite( HttpServletRequest req, HttpServletResponse res) throws UnsupportedEncodingException {
    
		
		String url = (String)req.getAttribute("url");   
		String[] viewlist = url.split("_");					
		
		//split �׽�Ʈ
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
		viewvalue = viewvalue.substring(0,viewvalue.length()-1);	// �ڿ� ? ����
		
		// Ȯ�ο� 
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		/*
		
		
		 ���  �޴�   -----------------------------------------------------
	
		if (url.contains("/index.do")){
			System.out.println("�������� �̵�");
			viewPage = "_Store/Main.jsp";
		
		} else if ( url.contains("/header_m1.do") ){
			System.out.println("�޴�1. �������� �̵� ");
			viewPage = "/_Store/Main.jsp"; 
		
		} else if ( url.contains("/header_m2.do") ){
			System.out.println("�޴�2. �α������� �̵� ");
			viewPage = "/_Store/View/Login/01_Login_Main.jsp"; 
		
		} else if ( url.contains("/header_m3.do") ){
			System.out.println("�޴�3. ����������� �̵� ");
			viewPage = "/_Store/View/Book/01_Book_List_Main.jsp"; 
		
		} else if ( url.contains("/header_m4.do") ){
			System.out.println("�޴�4. �Խ������� �̵� ");
			viewPage = "/_Store/View/Board/01_Board_Main.jsp"; 
		
		} else if ( url.contains("/header_m5.do") ){
			System.out.println("�޴�5. ������ �޴��� �̵�  ");
			viewPage = "admin_mian.do"; 
			viewPage = "/_Store/View/Admin/01_Admin_Main.jsp"; 
		
		} else if ( url.contains("/main.do") ){
			System.out.println("�������� �̵�  ");
			viewPage = "/_Store/View/Main/01_Serch_Main.jsp"; 
		
		}
		
			
			
		/* viewPage�� ������ �̵� ����  -----------------------------------------------------*/
		
		
	/*
	// ����ø� ����� ���纻
	public void actionDo_old(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {

		System.out.println();
		req.setCharacterEncoding("UTF-8");
		
		String viewPage = null;
		String uri = req.getRequestURI();   				//	/BMS_JEC_v3/test.do  
		String contextPath = req.getContextPath() ;   		//  /BMS_JEC_v3
		String url = uri.substring(contextPath.length());  	//  /test.do ��������   
		
		
		 ���  �޴�   -----------------------------------------------------
	
		if (url.contains("/index.do")){
			System.out.println("�������� �̵�");
			viewPage = "_Store/Main.jsp";
		
		} else if ( url.contains("/header_m1.do") ){
			System.out.println("�޴�1. �������� �̵� ");
			viewPage = "/_Store/Main.jsp"; 
		
		} else if ( url.contains("/header_m2.do") ){
			System.out.println("�޴�2. �α������� �̵� ");
			viewPage = "/_Store/View/Login/01_Login_Main.jsp"; 
		
		} else if ( url.contains("/header_m3.do") ){
			System.out.println("�޴�3. ����������� �̵� ");
			viewPage = "/_Store/View/Book/01_Book_List_Main.jsp"; 
		
		} else if ( url.contains("/header_m4.do") ){
			System.out.println("�޴�4. �Խ������� �̵� ");
			viewPage = "/_Store/View/Board/01_Board_Main.jsp"; 
		
		} else if ( url.contains("/header_m5.do") ){
			System.out.println("�޴�5. ������ �޴��� �̵�  ");
			viewPage = "admin_mian.do"; 
			viewPage = "/_Store/View/Admin/01_Admin_Main.jsp"; 
		
		} else if ( url.contains("/main.do") ){
			System.out.println("�������� �̵�  ");
			viewPage = "/_Store/View/Main/01_Serch_Main.jsp"; 
		
		}
		
		
		 ȸ�� ����   -----------------------------------------------------
		if (url.contains("/inputForm.do")){
			System.out.println("ȸ������ ��ư Ŭ�� -> 02_Login_Join_Form");
			Input_Form_Handler handler = new Input_Form_Handler();
			viewPage = handler.process(req, res);

		// ȸ�����Կ��� ���̵� �ߺ�Ȯ�� ������
		} else if (url.contains("/confirmId.do")){
			System.out.println("�ߺ�Ȯ�� ��ư Ŭ�� -> ConfirmIdHandler ");
			ConfirmId_Handler handler = new ConfirmId_Handler();
			viewPage = handler.process(req, res);
		
		// ȸ�� ����ó�� ������
		} else if ( url.equals("/inputPro.do")){
			System.out.println("ȸ������ ó�� -> Input_Pro_Handler");
			Input_Pro_Handler handler = new Input_Pro_Handler();
			viewPage = handler.process(req, res);

		// ȸ�� ����ó�� ������
		} else if ( url.equals("/mainSuccess.do")){
			System.out.println("ȸ������ �Ϸ� -> 01_Login_Main");
			req.setAttribute("cnt", 1);		// ȸ�������� �����մϴ�.
			viewPage = "/_Store/View/Login/01_Login_Main.jsp"; 
			viewPage = "/_Store/View/View_login.jsp"; 
			
		 �α���   -----------------------------------------------------		
		} else if(url.contains("/loginPro.do")) { 
			System.out.println("�α��� ��ư Ŭ�� : Login_Pro_Handler ");
			Login_Pro_Handler handler = new Login_Pro_Handler();
			viewPage = handler.process(req, res);	
		
		 ȸ��Ż��   -----------------------------------------------------		
		} else if(url.contains("/deleteForm.do")) { 
			System.out.println("ȸ��Ż�� ��ư Ŭ�� -> 05_Login_Delete_Form");
			viewPage = "/_Store/View/Login/05_Login_Delete_Form.jsp"; 
		
		} else if(url.equals("/deletePro.do")) { 
			Delete_Pro_Handler handler = new Delete_Pro_Handler();
			viewPage = handler.process(req, res);	
		
		 ��������   -----------------------------------------------------		
		} else if(url.contains("/modifyForm.do")) { 
			System.out.println("�������� ��ư Ŭ�� -> 07_Login_Modify_Form");
			viewPage = "/_Store/View/Login/07_Login_Modify_Form.jsp"; 
			
		} else if(url.equals("/modifyView.do")) { 
			System.out.println("�������� ���  -> Modify_View_Handler");
			Modify_View_Handler handler = new Modify_View_Handler();
			viewPage = handler.process(req, res);	 
		
		} else if(url.equals("/modifyPro.do")) { 
			System.out.println("�������� ���  -> Modify_Pro_Handler");
			Modify_Pro_Handler handler = new Modify_Pro_Handler();
			viewPage = handler.process(req, res);	 
		
		 �α׾ƿ� ó�� ������  -----------------------------------------------------
		} else if(url.equals("/logout.do")) {	// inputPro ���� �Ѱ� ����
			System.out.println("�α׾ƿ� ��ư Ŭ��  -> Logout_Pro_Handler");
			Logout_Pro_Handler handler = new Logout_Pro_Handler();
			viewPage = handler.process(req, res);	//��û�ּ�
		}
		
		
		 ������� �Խ���   -----------------------------------------------------
		if (url.contains("/book_list.do")){
			System.out.println("����������� �̵� " );
			List_01_Pro_Handler handler = new List_01_Pro_Handler();
			viewPage = handler.execute(req, res);
		
		} else if ( url.contains("/book_content.do") ){
			System.out.println("������������ �̵� ");
			Detail_01_Form_Handler han = new Detail_01_Form_Handler();
			viewPage = han.execute(req, res);
		
		// ���� �߰� 
		} else if ( url.contains("/book_write.do") ){
			System.out.println("�Ű����� �Է�" );
			Write_01_From_Handler han = new Write_01_From_Handler();
			viewPage = han.execute(req, res);
		
		} else if ( url.contains("/book_write_Pro.do") ){
			System.out.println("�Ű����� �߰�" );
			Write_02_Pro_Handler han = new Write_02_Pro_Handler();
			viewPage = han.execute(req, res);
		
		// ���� ����
		} else if ( url.contains("/book_modify_from.do") ){
			System.out.println("�������� ���� ��ư Ŭ�� ");
			Modify_01_From_Handler han = new Modify_01_From_Handler();
			viewPage = han.execute(req, res);
		
		} else if ( url.contains("/book_modify_pro.do") ){
			System.out.println("�������� ���� ");
			Modify_02_Pro_Handler han = new Modify_02_Pro_Handler();
			viewPage = han.execute(req, res);
		
		// ���� ����
		} else if ( url.contains("/book_delete_pro.do") ){
			System.out.println("������ư Ŭ�� ");
			Delete_01_Pro_Handler han = new Delete_01_Pro_Handler();
			viewPage = han.execute(req, res);	

		}
		
		
		 �Ϲ� �Խ���  : ��� �޴� ������ �̵�  -----------------------------------------------------
		
		if (url.contains("/Board_Kind_m1.do")){
			System.out.println("������������ �̵�" );
			Board_List_01_Pro_Handler handler = new Board_List_01_Pro_Handler();
			viewPage = handler.write(req, res , "NOTICE");
		
		} else if (url.contains("/Board_Kind_m2.do")){
			System.out.println("Q&A�� �̵�" );
			Board_List_01_Pro_Handler handler = new Board_List_01_Pro_Handler();
			viewPage = handler.write(req, res , "QnA");
			
		} else if (url.contains("/Board_Kind_m3.do")){
			System.out.println("FAQ�� �̵�" );
			Board_List_01_Pro_Handler handler = new Board_List_01_Pro_Handler();
			viewPage = handler.write(req, res , "FAQ");
			
		} else if (url.contains("/Board_Kind_m4.do")){
			System.out.println("EVENT�� �̵�" );
			Board_List_01_Pro_Handler handler = new Board_List_01_Pro_Handler();
			viewPage = handler.write(req, res , "EVENT");
		}
		
		
		 �Ϲ� �Խ���   -----------------------------------------------------
		
		// �Խ��� ����Ʈ
		if (url.contains("/board_list.do")){
			System.out.println("��ü �Խ������� �̵�" );
			String kind = req.getParameter("kind");
			Board_List_01_Pro_Handler handler = new Board_List_01_Pro_Handler();
			viewPage = handler.write(req, res, kind);
		
		// �Խù� ��������
		} else if (url.contains("/board_detail.do")){
			System.out.println("������������ �̵�" );
			String kind = req.getParameter("kind");
			Board_Detail_01_Pro_Handler handler = new Board_Detail_01_Pro_Handler();
			viewPage = handler.write(req, res , kind);	
		
		// �Խù� ����
		} else if (url.contains("/board_modify_form.do")){
			System.out.println("������ư Ŭ��" );
			String kind = req.getParameter("kind");
			Board_Modify_01_From_Handler handler = new Board_Modify_01_From_Handler();
			viewPage = handler.write(req, res , kind);	
		
		} else if (url.contains("/board_modify_view.do")){
			System.out.println("���� ���� �Է� ������" );
			String kind = req.getParameter("kind");
			Board_Modify_02_View_Handler handler = new Board_Modify_02_View_Handler();
			viewPage = handler.write(req, res , kind);	
		
		} else if (url.contains("/board_modify_pro.do")){
			System.out.println("���� ��� ������" );
			String kind = req.getParameter("kind");
			Board_Modify_03_Pro_Handler handler = new Board_Modify_03_Pro_Handler();
			viewPage = handler.write(req, res , kind);		
		
		// �Խù� ����
		} else if (url.contains("/board_delete_form.do")){
			System.out.println("���� ��ư Ŭ��" );
			String kind = req.getParameter("kind");
			Board_Delete_01_From_Handler handler = new Board_Delete_01_From_Handler();
			viewPage = handler.write(req, res , kind);		
		
		} else if (url.contains("/board_delete_pro.do")){
			System.out.println("���� ��� ������" );
			String kind = req.getParameter("kind");
			Board_Delete_02_Pro_Handler handler = new Board_Delete_02_Pro_Handler();
			viewPage = handler.write(req, res , kind);		
		
		// �Խù� �ۼ�	
		} else if (url.contains("/board_write_from.do")){
			System.out.println("�ۼ���ư Ŭ��" );
			String kind = req.getParameter("kind");
			Board_Write_01_Form_Handler handler = new Board_Write_01_Form_Handler();
			viewPage = handler.write(req, res , kind);		
			
		} else if (url.contains("/board_write_pro.do")){
			System.out.println("�ۼ� ��� ������" );
			String kind = req.getParameter("kind");
			Board_Write_02_Pro_Handler handler = new Board_Write_02_Pro_Handler();
			viewPage = handler.write(req, res , kind);		
			
		}
		
		
		 �˻���   -----------------------------------------------------
		// ���� ���� �˻�
		if (url.contains("/search_list.do")){
			System.out.println("���� ���� �˻�" );
			Main_Search_01_main_Pro_Handler handler = new Main_Search_01_main_Pro_Handler();
			viewPage = handler.mainexecute(req, res);
		
		// �˻����� ��������	
		} else if (url.contains("/search_detail.do")){
			System.out.println("���� �˻� ��������" );
			Main_Search_02_Detail_Pro_Handler handler = new Main_Search_02_Detail_Pro_Handler();
			viewPage = handler.mainexecute(req, res);			
			
		}
		
		
		
		 ���޴�   -----------------------------------------------------
		
		
		if (url.contains("/guest_cart.do")){
			System.out.println("��ٱ��Ϸ� �̵�" );
			Guest_Cart_01_View_Handler handler = new Guest_Cart_01_View_Handler();
			viewPage = handler.orderexecute(req, res);
	
		} else if (url.contains("/guest_cart_pro.do")){
			System.out.println("��ٱ��� ���" );
			Guest_Cart_02_Pro_Handler handler = new Guest_Cart_02_Pro_Handler();
			viewPage = handler.orderexecute(req, res);
		
		} else if (url.contains("/guest_cart_ordernum_pro.do")){
			System.out.println("��ٱ��� ��������" );
			Guest_Cart_03_Ordernum_Pro_Handler handler = new Guest_Cart_03_Ordernum_Pro_Handler();
			viewPage = handler.orderexecute(req, res);
		
		} else if (url.contains("/guest_cart_orderdel_pro.do")){
			System.out.println("��ٱ��� ������ư" );
			Guest_Cart_04_Orderdel_Pro_Handler handler = new Guest_Cart_04_Orderdel_Pro_Handler();
			viewPage = handler.orderexecute(req, res);
		
		} else if (url.contains("/guest_cart_orderconfirm_pro.do")){
			System.out.println("��ٱ��� �ֹ���ư" );
			Guest_Cart_05_Orderconfirm_Pro_Handler handler = new Guest_Cart_05_Orderconfirm_Pro_Handler();
			viewPage = handler.orderexecute(req, res);
		
		} else if (url.contains("/guest_cart_allbuy_pro.do")){
			System.out.println("��ü �ֹ��ϱ�" );
			Guest_Cart_06_allbuy_Pro_Handler handler = new Guest_Cart_06_allbuy_Pro_Handler();
			viewPage = handler.orderexecute(req, res);
		
		}
		
		 ���޴� : �ֹ� ���̺�    -----------------------------------------------------
			
		if (url.contains("/guest_order.do")){
			System.out.println("�ֹ� ���̺� �̵�" );
			Guest_Order_01_View_Handler handler = new Guest_Order_01_View_Handler();
			viewPage = handler.orderexecute(req, res);
			
		} else if (url.contains("/guest_order_state.do")){
				System.out.println("�ֹ����� ��ȯ");
				Guest_Order_02_Pro_Handler handler = new Guest_Order_02_Pro_Handler();
				viewPage = handler.orderexecute(req, res);
		}
		
		
		
		
		
		 �����ڸ޴�   -----------------------------------------------------
		
		if (url.contains("/admin_mian.do")){
			System.out.println("�������������� �̵�" );
			Admin_01_main_Pro_Handler handler = new Admin_01_main_Pro_Handler();
			viewPage = handler.supervise(req, res);
		
		} else if (url.contains("/admin_write.do")){
				System.out.println("�����ڿ��� �����߰� ���" );
				Admin_02_Write_Pro_Handler handler = new Admin_02_Write_Pro_Handler();
				viewPage = handler.supervise(req, res);
			
		} else if (url.contains("/admin_modify.do")){
			System.out.println("�����ڿ��� �������� ���" );
			Admin_03_Modify_Pro_Handler handler = new Admin_03_Modify_Pro_Handler();
			viewPage = handler.supervise(req, res);
		
		} else if (url.contains("/admin_modify.do")){
			System.out.println("�����ڿ��� �������� ���" );
			Admin_04_Delete_Pro_Handler handler = new Admin_04_Delete_Pro_Handler();
			viewPage = handler.supervise(req, res);
		
		} else if (url.contains("/admin_pay_list.do")){
			System.out.println("�����ڿ��� �������� ���" );
			Admin_05_Pay_List_Pro_Handler handler = new Admin_05_Pay_List_Pro_Handler();
			viewPage = handler.supervise(req, res);
		
		}   
			
			
			
		 viewPage�� ������ �̵� ����  -----------------------------------------------------
		
		RequestDispatcher dispatcher = 
				req.getRequestDispatcher(viewPage);  //Dispatcher�� ��ü�� ���;� ��� ����
		dispatcher.forward(req,res);
		}
	
	
	*/
}



