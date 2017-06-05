
// BMS_JEC_index_v2 에서 상단메뉴, 화면 표시 부분 불러오기


$(document).ready(function(){
	alert("메인 불러와 진다!");
});


	/*
	$("#Main_Top_Menu_01").click(function(){
		alert("클릭이 된다!");a
	})
	*/
	
	





//-----------------------------------------------------//

/*
$(document).ready(function(){
	
	var menu01 = $("#Main_Top_Menu_01").load('4_MENU/Main_Top_Menu.html');
	var menu02 = $("#Main_Top_Menu_03").load('4_MENU/Main_Top_Menu.html');
	var menu03 = $("#Main_Top_Menu_04").load('4_MENU/Main_Top_Menu.html');
	var screen = $("#Main_Screen_Page_All").load('4_MENU/Main_Screen.html')
	
	
	menu01.clik(function(){
		screen.animate({scrollLeft:0},"slow")
	});
	menu02.clik(function(){
		var scrollwidth = $(window).width();
		screen.animate({scrollLeft:scrollwidth},"slow")
	});
	menu03.clik(function(){
		var scrollwidth = $(window).width();
		screen.animate({scrollLeft:scrollwidth+scrollwidth},"slow")
	});

	return false;
});
*/
//-----------------------------------------------------//

/*
// Main_Screen 에서 화면 스크롤 기능 
$(document).ready(function(){
	$("#Main_Top_Menu_01").click(function(){
		$("#Main_body").animate({scrollLeft:0},"slow");
	});
	$("#Main_Top_Menu_03").click(function(){
		var scrollwidth = $(window).width();
		$("#Main_body").animate({scrollLeft:scrollwidth},"slow");
	});
	$("#Main_Top_Menu_04").click(function(){
		var scrollwidth = $(window).width();
		$("#Main_body").animate({scrollleft:scrollwidth*2},"slow");
	});
});


*/