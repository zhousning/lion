$(document).ready(function() {
	$(".delete").click(function() {
		var href = $(this).attr("href");
		$("form").attr("action", href).submit();
		return false;
	});
	$('.newbtn').bind("click", function() {
		$(this).find('.pis').click();
	});
	$("#js-new-imagebtn").click(function(){
		var html = "<label class='newbtn'> " +
				"<img class='blah' src='static/images/image-upload.png'> " +
				"<input class='pis' onchange='readURL(this);' type='file' name='imageAttachments' /> " +
				"</label>";
		$(this).before(html);
	});
	
});

function readURL(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();

		reader.onload = function(e) {
			$(input).parentsUntil(".newbtn").find(".blah")
				.attr('src', e.target.result);
		};

		reader.readAsDataURL(input.files[0]);
	}
}