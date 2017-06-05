<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-------------------------------------------------------  -->
	
	<!-- 1.CSS/JS불러오기  -->
	<link href="3_CSS_JS/BMS_JEC_css.css"	rel="stylesheet">
	<script src="3_CSS_JS/jquery-1.11.3.js"	type="text/javascript"></script>
	<!-- <script src="3_CSS_JS/BMS_JEC_js.js"	type="text/javascript"></script>  -->
	
	<!-------------------------------------------------------  -->
	
	<script type="text/javascript">
	$("#Main_body").ready(function(){
		$("#Main_Top_Menu").load('4_MENU/Main_Top_Menu.html');
		$("#Main_Screen").load('4_MENU/Main_Screen.html');
	});
	</script>
	
	
<!-------------------------------------------------------  -->
</head>
<body id="Main_body">
	<!-------------------------------------------------------  -->
	<!-- 2. 상단 메뉴 부분    -->
	<div id="Main_Top_Menu"></div>
	<!-- 3. 화면 표시 부분    -->
	<div id="Main_Screen"></div>
	<!-------------------------------------------------------  -->
</body>
</html>		
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		
<!-------------------------------------------------------  -->
<!-- 메인 종료  -->		
		
		
		
		<!-- 
		<table id="menu">
			<tr>
				<td id="Main">
					<span class="link" > 
						<img src="../Image/Main.png"
						onmouseover='this.src="../Image/Main_2.png"'
						onmouseout='this.src="../Image/Main.png"'>
						  메인화면  </span>
				</td>
				<td id="login" >
					<span class="link" data-popup-open="popup1" href="" > 
						<img src="../Image/Login.png"
						onmouseover='this.src="../Image/Login_2.png"'
						onmouseout='this.src="../Image/Login.png"'>
						 로그인 </span>
				</td>
				
				<td id="login" >
					<span class="link" data-popup-open="popup2" href="" > 
						<img src="../Image/Login.png"
						onmouseover='this.src="../Image/Login_2.png"'
						onmouseout='this.src="../Image/Login.png"'>
						 회원가입 </span>
				</td>
				<td id="Notice">
					<span class="link" > 
						<img src="../Image/Notice.png"
						onmouseover='this.src="../Image/Notice_2.png"'
						onmouseout='this.src="../Image/Notice.png"'>
						 공지사항 </span>
				</td>
				<td id="Connect">
					<span class="link" > 
						<img src="../Image/Connect.png"
						onmouseover='this.src="../Image/Connect_2.png"'
						onmouseout='this.src="../Image/Connect.png"'>
						  연락처 </span>
				</td>
			</tr>
		</table>

		----------------------------------------------------- 
		
		화면 내용 불러옴 
		<div id="main_content">
			<div class="content" id="c1" >
				<h1> 메인페이지</h1>
				
					------------------------------------------ 
					검색 상자 
					
					<table id="search_bar">
						<tr>
							<td id="bar_left">
								<input type="text" id="bar_left_text" 
								placeholder="  원하는 책을 입력하세요." >
							</td>
							<td id="bar_rigth"> 검색
				
								<img src="../Image/search.png" 
								onmouseover='this.src="../Image/search_2.png"'
								onmouseout='this.src="../Image/search.png"'
								style="width: 50%" >
							</td>
						</tr>
					</table>
					
					------------------------------------------ 
					도서 디스플레이 
					<div id="main_display" align="center"></div>
					
					------------------------------------------ 

			</div>
			<div class="content" id="c2">
				<h1> 공지사항</h1>
				
				------------------------------------------ 
				게시판 
				<div id="main_board"></div>
				------------------------------------------ 
				
			</div>
			
			
			<div class="content" id="c3">
				<h1> 연락처</h1>
				
				------------------------------------------ 
				연락처 
				<div id="main_connect" align="center"></div>
				------------------------------------------ 
				
			</div>
		</div>
	</div>
	
	----------------------------------------------------- 
	
	로그인 팝업창   
	<div id="login_pop" data-popup="popup1"> 
		<div id="login_pop_in" >
			<p><div id="login_login"></div></p>
			<p><a id="login_pop_out1" data-popup-close="popup1">닫기</a></p>
			<a id="login_pop_out2" data-popup-close="popup1" >x</a>
		</div>
	</div>
	
	
	회원가입 팝업창   
	<div id="login_pop" data-popup="popup2"> 
		<div id="login_pop_in" >
			<p><div id="login_join"></div></p>
			<p><a id="login_pop_out1" data-popup-close="popup2">닫기</a></p>
			<a id="login_pop_out2" data-popup-close="popup2" >x</a>
		</div>
	</div>
	 -->
	
