$(document).ready(function() {
	$(".delete").click(function() {
		var href = $(this).attr("href");
		$("form").attr("action", href).submit();
		return false;
	});
	$('.newbtn').bind("click", function() {
		$(this).find('.pis').click(function(e){
			e.stopPropagation();
		});
	});
	$("#js-new-imagebtn").click(function(){
		var html = "<label class='newbtn'> " +
				"<img class='blah' src='static/images/image-upload.png'> " +
				"<input class='pis' onchange='readURL(this);' type='file' name='imagefiles' /> " +
				"</label>";
		$(this).before(html);
	});
	
});

function readURL(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();
		var that = $(input);

		reader.onload = function(e) {
			that.parent().find(".blah").attr('src', e.target.result);
			that.siblings(".hiddenImage").remove();
		};

		reader.readAsDataURL(input.files[0]);
	}
}