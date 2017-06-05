$(function(){
	
	$(".book_thum").click(function(){
		
		var cover = $(this).attr("href");
		var num = $(this).attr("id");
		$("#book_line_full img").fadeOut(500,function(){
			$("#book_line_full img").attr("src",cover);
			/*alert(num);*/
			$(this).fadeIn(500);
		});
		return false;
	});
});

