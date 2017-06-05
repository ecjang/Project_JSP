
$(document).ready(function(){
	
	// book.html 불러오기
	$('#main_display').load('../Book/book.html');

	// login_login.html 불러오기 : 로그인
	$('#login_login').load('../Login/login_login.html');
	
	// login_join.html 불러오기 : 회원가입
	$('#login_join').load('../Login/login_join.html');
	
	// board.html 불러오기
	$('#main_board').load('../Board/board.html');
	
	// connect.html 불러오기
	$('#main_connect').load('../Connect/connect.html');
});


/*-----------------------------------------------------*/


// 화면 스크롤 기능 구현
$(document).ready(function(){
	
	// 첫번째 화면 이동 : +0
	$("#Main").click(function(){
		$("body").animate({ scrollLeft:0},"slow");
	})
	
	// 두번째 화면 이동 : + 화면너비
	$("#Notice").click(function(){
		var wid = $(window).width();
		$("body").animate({ scrollLeft:wid},"slow");
	})
	
	// 세번째 화면 이동 : + 화면너비x2
	$("#Connect").click(function(){
		var wid = $(window).width();
		$("body").animate({ scrollLeft:wid+wid},"slow");
		
	})
}); 






