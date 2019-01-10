$(document).ready(function() {
	$("select[name='subject.id']").change(function() {
		var subject_id = $(this).val();
		var url = "subjects/" + subject_id + "/examPoints"
		$.get(url, function(result) {
			var examPoint = $("select[name='examPoint.id']");
			var option = "";
			$.each(result, function(key, value) {
				option += "<option value='" + key + "'>" + value + "</option>";
			});
			examPoint.html(option);
		});
	});

	$('.newbtn').bind("click", function() {
		$('#pic').click();
	});

	$("#js-semblance").click(function() {
		var url = "questions/semblance";
		var title = $.trim($("#title").val());
		if (title == "") {
			alert("without subject title");
			return;
		}
		var subjectId = $(".js-subjectid").val();
		if (!subjectId || subjectId == "-1") {
			alert("please select subject");
			return;
		}
		$.post(url, {
			text1 : title,
			subjectId : subjectId
		}, function(data) {
			var score = data["score"];
			$("#js-semblance-result").html(score);
		});

	});

	$("#randomForm").submit(function() {
		var data = "";
		$.post("examPapers/random_selector", $(this).serialize(),
			function (result) {
				if ($.isEmptyObject(result)) {
					$("#question-ctn").html("without result");
					$("#random-submit").attr("disabled", "disabled");
				} else {
					$.each(result, function(key, value){
						var id = key;
						var title = value;
						
						data += "<p>" + title + "</p>" + "<input type='hidden' name='questionIds' value='" + id + "' />";
					});
					$("#random-submit").removeAttr("disabled");
					$("#question-ctn").html(data);
				}	
			},
			"json"
		);
		return false;
	});
});

function readURL(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();

		reader.onload = function(e) {
			$('#blah')
				.attr('src', e.target.result);
		};

		reader.readAsDataURL(input.files[0]);
	}
}