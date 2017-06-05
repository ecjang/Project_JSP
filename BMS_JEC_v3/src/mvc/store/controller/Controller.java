package mvc.store.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Controller() {  super(); }
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException { ActionDo (req, res); }
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException { ActionDo (req, res);}
	
	protected void ActionDo(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String URI = req.getRequestURI();
		String CON = req.getContextPath();
		String URL = URI.substring(CON.length());
		String Page = null;
		
		if( URL.equals("/*.do") ){
			Page="/loginService.do";
		}
		
		if( URL.equals("/loginService.do") ){
			System.out.println("loginService.do가 입력되었습니다.");
			Page="/_Store/Member/03_Login2.jsp";
		}
		
		
		RequestDispatcher Move = req.getRequestDispatcher(Page);
		Move.forward(req, res);
		
		
		req.setAttribute("Move", Page);
	}
	
}
