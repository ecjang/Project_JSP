

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
		
	