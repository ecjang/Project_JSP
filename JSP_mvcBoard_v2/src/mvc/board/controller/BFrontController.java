package mvc.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.board.handler.Content_01_Form_Handler;
import mvc.board.handler.Delete_01_From_Handler;
import mvc.board.handler.Delete_02_Pro_Handler;
import mvc.board.handler.List_01_Pro_Handler;
import mvc.board.handler.Write_01_From_Handler;
import mvc.board.handler.Write_02_Pro_Handler;
import mvc.board.handler.Modify_01_From_Handler;
import mvc.board.handler.Modify_02_View_Handler;
import mvc.board.handler.Modify_03_Pro_Handler;

@WebServlet("*.do")
public class BFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/* �⺻ ���� -----------------------------------------------------*/
	
    public BFrontController() { super(); }
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { ActionDo(req,res); }
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { ActionDo(req,res); }

	/*-----------------------------------------------------*/
	
	public void ActionDo( HttpServletRequest req , HttpServletResponse res ) throws ServletException, IOException {
		
		/* URL ���� -----------------------------------------------------*/
		req.setCharacterEncoding("UTF-8");				// UTF-8 Setting
		String URI 	= req.getRequestURI();				// JSP_mvcBoard/list.do
		String Path	= req.getContextPath();				// jsp_mvcBoard
		String URL	= URI.substring(Path.length());		// list.do
		String ViewPage = null;
		
		
		/*--------------------------------------------------------------------------------------*/
		
		/* ��Ʈ�� ������ -----------------------------------------------------*/
		if( URL.equals("/*.do") ){
			System.out.println("\n��Ʈ�� ���� : *.do -> list.do"); 
			ViewPage = "/list.do";
			
		/* �۸�� ������ (list.do) -----------------------------------------------------*/
		} else if ( URL.equals("/list.do") ){
			System.out.println("\n�۸�� �ҷ����� : list.do -> list_01_Pro_Handler.java");
			List_01_Pro_Handler han = new List_01_Pro_Handler();
			ViewPage = han.execute(req, res);
		
		/* ���������� (contentForm.do) -----------------------------------------------------*/
		} else if ( URL.equals("/contentForm.do") ){
			System.out.println("\n������������ �̵� : contentForm.do -> Content_01_Form_Handler.java");
			Content_01_Form_Handler han = new Content_01_Form_Handler();
			ViewPage = han.execute(req, res);
		
			
		/*--------------------------------------------------------------------------------------*/
			
		/*	
		������ư Ŭ��  
		modify.do -> modify_01_From_Handler -> modify_01_Form ->  
		modifyfrom.do -> modify_02_View_Handler -> modify_02_View ->
		modifyView.do -> modify_03_Pro_Handler -> modify_03_Pro
		*/
		/* ����������_1_����Է� (contentForm.do) -----------------------------------------------------*/
		} else if ( URL.equals("/modify.do") ){
			System.out.println("\n������ư Ŭ�� : content.jps-> Modify_01_From_Handler.do");
			Modify_01_From_Handler han = new Modify_01_From_Handler();
			ViewPage = han.execute(req, res);
		
		/* ����������_2_����ȣ�� (contentForm.do) -----------------------------------------------------*/
		} else if ( URL.equals("/modifyfrom.do") ){
			System.out.println("\n�������� �Է� : modify_01_Form.jsp -> Modify_02_View_Handler.do");
			Modify_02_View_Handler han = new Modify_02_View_Handler();
			ViewPage = han.execute(req, res);
		
		/* ����������_3_�� �Է� (contentForm.do) -----------------------------------------------------*/
		} else if ( URL.equals("/modifyView.do") ){
			System.out.println("\n�������� ���� : modify_02_View.jsp -> Modify_03_Pro_Handler.do");
			Modify_03_Pro_Handler han = new Modify_03_Pro_Handler();
			ViewPage = han.execute(req, res);
		
		
		/*--------------------------------------------------------------------------------------*/
		
		/*
		�ۼ� ��ư Ŭ��  
		write.do -> Write_01_From_Handler -> write_01_From ->
		writePro.do -> Write_02_Pro_Handler -> write_02_Pro
		*/
			
		/* �ۼ�������_1 ����Է� (contentForm.do) -----------------------------------------------------*/
		} else if ( URL.equals("/write.do") ){
			System.out.println("\n�ۼ���ư Ŭ�� : content.jsp -> Write_01_From_Handler.do");
			Write_01_From_Handler han = new Write_01_From_Handler();
			ViewPage = han.execute(req, res);
			
		} else if ( URL.equals("/writePro.do") ){
			System.out.println("\n����,��� ��� : write_01_From.jsp -> Write_02_Pro_Handler.do");
			Write_02_Pro_Handler han = new Write_02_Pro_Handler();
			ViewPage = han.execute(req, res);
			
			
		/*--------------------------------------------------------------------------------------*/
		
		/*
		������ư Ŭ��  
		delete.do -> Delete_01_From_Handler -> delete_01_From ->
		deletePro.do -> Delete_01_From_Handler -> delete_02_Pro
		*/
			
		/* ����������_1 ����Է� (contentForm.do) -----------------------------------------------------*/
		} else if ( URL.equals("/delete.do") ){
			System.out.println("\n������ư Ŭ�� : content.jsp -> Delete_01_Pro_Handler.do");
			Delete_01_From_Handler han = new Delete_01_From_Handler();
			ViewPage = han.execute(req, res);
		
		/* ����������_2 ���ǻ��� (contentForm.do) -----------------------------------------------------*/
		} else if ( URL.equals("/deletePro.do") ){
			System.out.println("\n�Խù� ���� : delete_01_From.jsp -> Delete_01_Pro_Handler.do");
			Delete_02_Pro_Handler han = new Delete_02_Pro_Handler();
			ViewPage = han.execute(req, res);
		}

		
		
		/* ViewPage�� �̵� -----------------------------------------------------*/
		RequestDispatcher dis = req.getRequestDispatcher(ViewPage);
		dis.forward( req,res );
		
	}	// ActionDo
	
	/*-----------------------------------------------------*/

}
