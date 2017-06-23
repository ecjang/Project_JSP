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


@WebServlet("*.do")  // ��� jsp������ ����� ������
public class MFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MFrontController() { super();  }

    //1�ܰ�. HTTP ��û ����
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
		String url = uri.substring(contextPath.length());  	//  /test.do ��������   
		
		
		/* ���  �޴�   -----------------------------------------------------*/
	
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
			/*viewPage = "/_Store/View/Admin/01_Admin_Main.jsp"; */
		
		} else if ( url.contains("/main.do") ){
			System.out.println("�������� �̵�  ");
			viewPage = "/_Store/View/Main/01_Serch_Main.jsp"; 
		
		}
		
		
		/* ȸ�� ����   -----------------------------------------------------*/
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
			/*viewPage = "/_Store/View/View_login.jsp"; */
			
		/* �α���   -----------------------------------------------------*/		
		} else if(url.contains("/loginPro.do")) { 
			System.out.println("�α��� ��ư Ŭ�� : Login_Pro_Handler ");
			Login_Pro_Handler handler = new Login_Pro_Handler();
			viewPage = handler.process(req, res);	
		
		/* ȸ��Ż��   -----------------------------------------------------*/		
		} else if(url.contains("/deleteForm.do")) { 
			System.out.println("ȸ��Ż�� ��ư Ŭ�� -> 05_Login_Delete_Form");
			viewPage = "/_Store/View/Login/05_Login_Delete_Form.jsp"; 
		
		} else if(url.equals("/deletePro.do")) { 
			Delete_Pro_Handler handler = new Delete_Pro_Handler();
			viewPage = handler.process(req, res);	
		
		/* ��������   -----------------------------------------------------*/		
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
		
		/* �α׾ƿ� ó�� ������  -----------------------------------------------------*/
		} else if(url.equals("/logout.do")) {	// inputPro ���� �Ѱ� ����
			System.out.println("�α׾ƿ� ��ư Ŭ��  -> Logout_Pro_Handler");
			Logout_Pro_Handler handler = new Logout_Pro_Handler();
			viewPage = handler.process(req, res);	//��û�ּ�
		}
		
		
		/* ������� �Խ���   -----------------------------------------------------*/
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
		
		
		/* �Ϲ� �Խ���  : ��� �޴� ������ �̵�  -----------------------------------------------------*/
		
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
		
		
		/* �Ϲ� �Խ���   -----------------------------------------------------*/
		
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
		
		
		/* �˻���   -----------------------------------------------------*/
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
		
		
		
		/* ���޴�   -----------------------------------------------------*/
		
		
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
		
		/* ���޴� : �ֹ� ���̺�    -----------------------------------------------------*/
			
		if (url.contains("/guest_order.do")){
			System.out.println("�ֹ� ���̺� �̵�" );
			Guest_Order_01_View_Handler handler = new Guest_Order_01_View_Handler();
			viewPage = handler.orderexecute(req, res);
			
		} else if (url.contains("/guest_order_state.do")){
				System.out.println("�ֹ����� ��ȯ");
				Guest_Order_02_Pro_Handler handler = new Guest_Order_02_Pro_Handler();
				viewPage = handler.orderexecute(req, res);
		}
		
		
		
		
		
		/* �����ڸ޴�   -----------------------------------------------------*/
		
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
			
			
			
		/* viewPage�� ������ �̵� ����  -----------------------------------------------------*/
		
		RequestDispatcher dispatcher = 
				req.getRequestDispatcher(viewPage);  //Dispatcher�� ��ü�� ���;� ��� ����
		dispatcher.forward(req,res);
		}
	
	
}



