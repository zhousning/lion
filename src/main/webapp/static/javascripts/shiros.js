$(document).ready(function(){
	$("#verification-btn").click(function(){
		var email = $.trim($("#email").val());
		if (email == "") {
			alert("邮箱不能为空");
			return;
		} else {
			var url = "users/passwords/" + email +"/code";
			$.get(url, function(result) {
				if (result["status"] == "success") {
					$("#verification-btn").html("已发送");
					$("#verification-btn").attr("disabled", true);
				}
			});
		}
	});
});
