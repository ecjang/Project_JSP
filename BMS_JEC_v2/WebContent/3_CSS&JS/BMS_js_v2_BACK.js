/*메인페이지 스크롤 기능*/

$(document).ready(function(){
	$("#main_topmenu_table_td1").click(function(){		// 첫번째 화면 이동 
		$("main_body").animate({scrollLeft:0},"slow");
	})
	$("#main_topmenu_table_td4").click(function(){		// 두번째 화면 이동
		var wid=$(window).width();
		$("#main_body").animate({scrollLeft:wid},"slow");
	})
	$("#main_topmenu_table_td5").click(function(){		// 세번 째 화면 이동
		var wid=$(window).width();
		$("#main_body").animate({scrollLeft:wid+wid},"slow")	
	})
});


/*메인페이지 팝업 기능*/

$(function(){
	$('[data-popup-open]').on('click', function(e) {	// 팝업 열기
		var content = $(this).attr('data-popup-open');	
		$('[data-popup="' + content + '"]').fadeIn(350);
		e.preventDefault();
	})
	
	$('[data-popup-close]').on('click', function(e)  {	// 팝업 닫기
		var content = jQuery(this).attr('data-popup-close');
		$('[data-popup="' + content + '"]').fadeOut(350);

		e.preventDefault();
	})
});

