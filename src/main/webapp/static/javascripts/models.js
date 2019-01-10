$(document).ready(function() {
	$("#btn-add-attribute").click(function() {
		var html = $(".attr-content").prop("outerHTML");
		$("#attribute-ctn").append(html);
	});
	$("#btn-add-associate").click(function() {
		var html = $(".associate-content").prop("outerHTML");
		$("#associate-ctn").append(html);
	});

});
function deleteAttr(that) {
	$(that).parentsUntil("#attribute-ctn").remove();
}

function deleteAssociate(that) {
	$(that).parentsUntil("#associate-ctn").remove();
}