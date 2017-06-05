
/*
$("#book_display").click(function(){
	

	var booknum = $(this).prop("src")
	var booknum2 = booknum.substr(41,2)
	while(booknum2.toString().length < 2){
		booknum2 = "0"+num;	}	// 01 , 02 , ... 11, 12
	alert("세번째 과정 : " + booknum2)
	
	
	$("#b+'booknum2'").removeClass("hiddencontent");
	$("#b+'booknum2'").addclass("showcontent");
	
});*/

//------------------------------------------------//
	
$(function(){	
	//----- OPEN
	$('[data-popup-open]').on('click', function(e) {
		
		var content = $(this).attr('data-popup-open');		//bookDisplay
		$('[data-popup="' + content + '"]').fadeIn(350);
		
		booknum2 = 0;
		var booknum = $(this).prop("src");
		var booknum2 = booknum.substr(40,3);
		while(booknum2.toString().length < 3){
			booknum2 = "0"+num;	}	// 01 , 02 , ... 11, 12
		alert("세번째 과정 : "+ booknum2);
		
		$("#b"+booknum2).removeClass("hiddencontent");
		$("#b"+booknum2).addClass("showcontent");
		
		
		/*$("#"+"booknum2").removeClass("hiddencontent");
		$("#"+"booknum2").addClass("showcontent");*/
		e.preventDefault();
	});
	

	//----- CLOSE
	$('[data-popup-close]').on('click', function(e)  {
		var content = jQuery(this).attr('data-popup-close');
		$('[data-popup="' + content + '"]').fadeOut(350);
		booknum2 = 0;
		var booknum = $(this).prop("src");
		var booknum2 = booknum.substr(40,3);
		while(booknum2.toString().length < 3){
			booknum2 = "0"+num;	}	// 01 , 02 , ... 11, 12
		alert("세번째 과정 : "+ booknum2);
		$("#b"+booknum2).removeClass("showcontent");
		$("#b"+booknum2).addClass("hiddencontent");
		
		e.preventDefault();
	});
});

