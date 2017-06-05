<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>


<!-------------------------------------------------------  -->

<!-- 1. 메타 데이터 정의  -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<!-- 2. 메인화면 CSS -->
<link href="CSS/index_css.css" type="text/css" rel="stylesheet" />

<!-- 3. 팝업 CSS  -->
<link href="CSS/login_css.css" rel="stylesheet" type="text/css">

<!-- 4. 게시판  CSS  -->
<link href="CSS/board_css.css" rel="stylesheet" type="text/css">


<!-------------------------------------------------------  -->

<!-- 1. jQuery 활성화 파일  -->
<script type="text/javascript" src="jQuery/jquery-1.11.3.js"></script>

<!-- 2. 메인 화면 스크롤 기능   -->
<script type="text/javascript" src="jQuery/index_scroll.js"></script>

<!-- 3. 팝업 구동 jquery -->
<script type="text/javascript" src="jQuery/jquery-min-2.13.js"></script>

<!-- 4. 팝업창 기능  -->
<script type="text/javascript" src="jQuery/login_popup.js"></script>


<!-------------------------------------------------------  -->


</head>
<body id="main_all">
	<div>
		<!-- 상단 메뉴 버튼   -->
		<table id="menu">
			<tr>
				<td id="Main"><span class="link"> <img
						src="Image/Main.png"
						onmouseover='this.src="Image/Main_2.png"'
						onmouseout='this.src="Image/Main.png"'> 메인화면
				</span></td>
				<td id="login"><span class="link" data-popup-open="popup1"
					href=""> <img src="Image/Login.png"
						onmouseover='this.src="Image/Login_2.png"'
						onmouseout='this.src="Image/Login.png"'> 로그인
				</span></td>

				<td id="login"><span class="link" data-popup-open="popup2"
					href=""> <img src="Image/Login.png"
						onmouseover='this.src="Image/Login_2.png"'
						onmouseout='this.src="Image/Login.png"'> 회원가입
				</span></td>
				<td id="Notice"><span class="link"> <img
						src="Image/Notice.png"
						onmouseover='this.src="Image/Notice_2.png"'
						onmouseout='this.src="Image/Notice.png"'> 공지사항
				</span></td>
				<td id="Connect"><span class="link"> <img
						src="Image/Connect.png"
						onmouseover='this.src="Image/Connect_2.png"'
						onmouseout='this.src="Image/Connect.png"'> 연락처
				</span></td>
			</tr>
		</table>

		<!-------------------------------------------------------  -->

		<!-- 화면 내용 불러옴  -->
		<div id="main_content">
			<div class="content" id="c1">
				<h1>메인페이지</h1>

				<!--------------------------------------------  -->
				<!-- 검색 상자  -->

				<table id="search_bar">
					<tr>
						<td id="bar_left"><input type="text" id="bar_left_text"
							placeholder="  원하는 책을 입력하세요."></td>
						<td id="bar_rigth">검색</td>
					</tr>
				</table>

				<!--------------------------------------------  -->
				<!-- 도서 디스플레이  -->
				
				<div id="main_display" align="center"></div>

				<!--------------------------------------------  -->

			</div>
			<div class="content" id="c2">
				<h1>공지사항</h1>

				<!--------------------------------------------  -->
				<!-- 게시판  -->
				<div id="main_board"></div>
				<!--------------------------------------------  -->

			</div>


			<div class="content" id="c3">
				<h1>연락처</h1>

				<!--------------------------------------------  -->
				<!-- 연락처  -->
				<div id="main_connect" align="center"></div>
				<!--------------------------------------------  -->

			</div>
		</div>
	</div>

	<!-------------------------------------------------------  -->

	<!-- 로그인 팝업창    -->
	<div id="login_pop" data-popup="popup1">
		<div id="login_pop_in">
			<p>
			<div id="login_login"></div>
			</p>
			<p>
				<a id="login_pop_out1" data-popup-close="popup1">닫기</a>
			</p>
			<a id="login_pop_out2" data-popup-close="popup1">x</a>
		</div>
	</div>



	<!-- 회원가입 팝업창    -->
	<div id="login_pop" data-popup="popup2">
		<div id="login_pop_in">
			<p>
			<div id="login_join"></div>
			</p>
			<p>
				<a id="login_pop_out1" data-popup-close="popup2">닫기</a>
			</p>
			<a id="login_pop_out2" data-popup-close="popup2">x</a>
		</div>
	</div>



</body>
</html>