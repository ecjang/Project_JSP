<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<html>


<head> <title> BMS_JEC_V2_Main </title> 
	
	<!-- CSS & JS -->
	<link type="text/css" rel="stylesheet" href="3_CSS&JS/BMS_css_v2.css" >
	<script type="text/javascript" src="3_CSS&JS/jquery-min-2.13.js"></script>
	<script type="text/javascript" src="3_CSS&JS/BMS_js_v2.js"></script>
	
</head>

<body id="main_body">

<!-------------------------------------------------------  -->
	<div>
		<!-- 상단 메뉴 버튼  -->
	 	<table id= "main_topmenu_table">
			<tr>
			<td id="main_topmenu_table_td1"> 메인화면 </td>
			<td id="main_topmenu_table_td2"> 로그인 </td>
			<td id="main_topmenu_table_td3"> 회원가입 </td>
			<td id="main_topmenu_table_td4"> 공지사항 </td>
			<td id="main_topmenu_table_td5"> 오시는길 </td>
			</tr>
		</table> 
	
		<!------------------------------------------------- -->
	
		<!-- 화면 스크린 -->
		<div id="main_screen">
			<div class="main_screen_content">메인화면</div> 
			<div class="main_screen_content">공지사항</div> 
			<div class="main_screen_content">오시는길</div> 
		</div>
	</div>
	
	<!---------------------------------------------------- -->
	
	<!-- 로그인 팝업 내용-->
	<div class="popup" date-popup="popup1"><div id="popup_in">
		<p><div id="main_popup_login_content"></div></p>
		<p><a id="main_popup_login_out_text" date-popup-out="popup1"></a>
		<a id="main_popup_login_out_button" date-popup-out="popup1"></a>
	</div></div>
	
	
	<!---------------------------------------------------- -->
	
	<!-- 회원가입 팝업 내용-->
	<div class="popup" date-popup="popup2"><div id="popup_in">
		<p><div id="popup_in_content"></div></p>
		<p><a id="popup_out_text" date-popup-out="popup2"></a>
		<a id="popup_out_button" date-popup-out="popup2"></a>
	</div></div>
	
<!-------------------------------------------------------  -->	

</body>
</html>