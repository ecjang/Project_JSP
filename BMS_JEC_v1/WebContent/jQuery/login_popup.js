

$(function() {
	//----- OPEN
	$('[data-popup-open]').on('click', function(e)  {
		var content = jQuery(this).attr('data-popup-open');
		$('[data-popup="' + content + '"]').fadeIn(350);


		e.preventDefault();
	});

	//----- CLOSE
	$('[data-popup-close]').on('click', function(e)  {
		var content = jQuery(this).attr('data-popup-close');
		$('[data-popup="' + content + '"]').fadeOut(350);

		e.preventDefault();
	});
});



$(function() {
	//----- OPEN
	
	$('[data-popup-open]').on('click', function(e)  {
		
		//booknum b1, b2
		
		var content = jQuery(this).attr('data-popup-open');
		
		$("#"+content+"content").removeClass("hiddencontent");
		$("#"+content+"content").addClass("showcontent");
		
			
		
		
		
		
		$('[data-popup="' + content + '"]').fadeIn(350);
		
		
		

		e.preventDefault();
	});
});