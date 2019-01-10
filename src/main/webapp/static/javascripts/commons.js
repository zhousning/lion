$(".index").ready(function() {
	$(".delete").click(function() {
		var href = $(this).attr("href");
		$("form").attr("action", href).submit();
		return false;
	});

});