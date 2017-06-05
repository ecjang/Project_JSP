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
    
	/* 기본 설정 -----------------------------------------------------*/
	
    public BFrontController() { super(); }
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { ActionDo(req,res); }
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { ActionDo(req,res); }

	/*-----------------------------------------------------*/
	
	public void ActionDo( HttpServletRequest req , HttpServletResponse res ) throws ServletException, IOException {
		
		/* URL 설정 -----------------------------------------------------*/
		req.setCharacterEncoding("UTF-8");				// UTF-8 Setting
		String URI 	= req.getRequestURI();				// JSP_mvcBoard/list.do
		String Path	= req.getContextPath();				// jsp_mvcBoard
		String URL	= URI.substring(Path.length());		// list.do
		String ViewPage = null;
		
		
		/*--------------------------------------------------------------------------------------*/
		
		/* 인트로 페이지 -----------------------------------------------------*/
		if( URL.equals("/*.do") ){
			System.out.println("\n인트로 실행 : *.do -> list.do"); 
			ViewPage = "/list.do";
			
		/* 글목록 페이지 (list.do) -----------------------------------------------------*/
		} else if ( URL.equals("/list.do") ){
			System.out.println("\n글목록 불러오기 : list.do -> list_01_Pro_Handler.java");
			List_01_Pro_Handler han = new List_01_Pro_Handler();
			ViewPage = han.execute(req, res);
		
		/* 세부페이지 (contentForm.do) -----------------------------------------------------*/
		} else if ( URL.equals("/contentForm.do") ){
			System.out.println("\n세부페이지로 이동 : contentForm.do -> Content_01_Form_Handler.java");
			Content_01_Form_Handler han = new Content_01_Form_Handler();
			ViewPage = han.execute(req, res);
		
			
		/*--------------------------------------------------------------------------------------*/
			
		/*	
		수정버튼 클릭  
		modify.do -> modify_01_From_Handler -> modify_01_Form ->  
		modifyfrom.do -> modify_02_View_Handler -> modify_02_View ->
		modifyView.do -> modify_03_Pro_Handler -> modify_03_Pro
		*/
		/* 수정페이지_1_비번입력 (contentForm.do) -----------------------------------------------------*/
		} else if ( URL.equals("/modify.do") ){
			System.out.println("\n수정버튼 클릭 : content.jps-> Modify_01_From_Handler.do");
			Modify_01_From_Handler han = new Modify_01_From_Handler();
			ViewPage = han.execute(req, res);
		
		/* 수정페이지_2_정보호출 (contentForm.do) -----------------------------------------------------*/
		} else if ( URL.equals("/modifyfrom.do") ){
			System.out.println("\n수정내용 입력 : modify_01_Form.jsp -> Modify_02_View_Handler.do");
			Modify_02_View_Handler han = new Modify_02_View_Handler();
			ViewPage = han.execute(req, res);
		
		/* 수정페이지_3_값 입력 (contentForm.do) -----------------------------------------------------*/
		} else if ( URL.equals("/modifyView.do") ){
			System.out.println("\n수정내용 적용 : modify_02_View.jsp -> Modify_03_Pro_Handler.do");
			Modify_03_Pro_Handler han = new Modify_03_Pro_Handler();
			ViewPage = han.execute(req, res);
		
		
		/*--------------------------------------------------------------------------------------*/
		
		/*
		작성 버튼 클릭  
		write.do -> Write_01_From_Handler -> write_01_From ->
		writePro.do -> Write_02_Pro_Handler -> write_02_Pro
		*/
			
		/* 작성페이지_1 비번입력 (contentForm.do) -----------------------------------------------------*/
		} else if ( URL.equals("/write.do") ){
			System.out.println("\n작성버튼 클릭 : content.jsp -> Write_01_From_Handler.do");
			Write_01_From_Handler han = new Write_01_From_Handler();
			ViewPage = han.execute(req, res);
			
		} else if ( URL.equals("/writePro.do") ){
			System.out.println("\n새글,답글 등록 : write_01_From.jsp -> Write_02_Pro_Handler.do");
			Write_02_Pro_Handler han = new Write_02_Pro_Handler();
			ViewPage = han.execute(req, res);
			
			
		/*--------------------------------------------------------------------------------------*/
		
		/*
		삭제버튼 클릭  
		delete.do -> Delete_01_From_Handler -> delete_01_From ->
		deletePro.do -> Delete_01_From_Handler -> delete_02_Pro
		*/
			
		/* 삭제페이지_1 비번입력 (contentForm.do) -----------------------------------------------------*/
		} else if ( URL.equals("/delete.do") ){
			System.out.println("\n삭제버튼 클릭 : content.jsp -> Delete_01_Pro_Handler.do");
			Delete_01_From_Handler han = new Delete_01_From_Handler();
			ViewPage = han.execute(req, res);
		
		/* 삭제페이지_2 세션삭제 (contentForm.do) -----------------------------------------------------*/
		} else if ( URL.equals("/deletePro.do") ){
			System.out.println("\n게시물 삭제 : delete_01_From.jsp -> Delete_01_Pro_Handler.do");
			Delete_02_Pro_Handler han = new Delete_02_Pro_Handler();
			ViewPage = han.execute(req, res);
		}

		
		
		/* ViewPage로 이동 -----------------------------------------------------*/
		RequestDispatcher dis = req.getRequestDispatcher(ViewPage);
		dis.forward( req,res );
		
	}	// ActionDo
	
	/*-----------------------------------------------------*/

}
