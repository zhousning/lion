$(document).ready(function() {
	initData();
	$("#btn-add-attribute").click(function() {
		var html = $($(".attr-content")[1]).prop("outerHTML");
		$("#attribute-ctn").append(html);
	});
	$("#btn-add-associate").click(function() {
		var html = $($(".associate-content")[0]).prop("outerHTML");
		$("#associate-ctn").append(html);
	});
	$("#btn-add-plugin").click(function() {
		var html = $($(".plugin-content")[0]).prop("outerHTML");
		$("#plugin-ctn").append(html);
	});
});
function deleteAttr(that) {
	$(that).parentsUntil("#attribute-ctn").remove();
}

function deleteAssociate(that) {
	$(that).parentsUntil("#associate-ctn").remove();
}

function deletePlugin(that) {
	$(that).parentsUntil("#plugin-ctn").remove();
}

function initData(){
	$("#name-label").val("名称");
	$("#js-name-constraint").val("@NotBlank");
}
function setAttrDefault(object){
	var that = $(object);
	var val = that.val();
	var obj = {
			"title": {"type": "String", "label": "标题", "constraint": "none", "attrwidget": "text"},
			"content": {"type": "String", "label": "内容", "constraint": "none", "attrwidget": "text"},
			"status": {"type": "String", "label": "状态", "constraint": "none", "attrwidget": "text"},
			"image": {"type": "String", "label": "图片", "constraint": "none", "attrwidget": "text"},
			"url": {"type": "String", "label": "地址", "constraint": "none", "attrwidget": "text"},
			"phone": {"type": "Long", "label": "手机", "constraint": "@NotBlank", "attrwidget": "number"},
			"email": {"type": "String", "label": "邮箱", "constraint": "@NotBlank", "attrwidget": "text"},
			"password": {"type": "String", "label": "密码", "constraint": "@NotBlank", "attrwidget": "password"},
			"createTime": {"type": "Date", "label": "创建时间", "constraint": "none", "attrwidget": "date"},
			"updateTime": {"type": "Date", "label": "更新时间", "constraint": "none", "attrwidget": "date"}
	}
	that.parentsUntil("#attribute-ctn").find(".js-attrtype").val(obj[val]["type"]);
	that.parentsUntil("#attribute-ctn").find(".js-attrlabel").val(obj[val]["label"]);
	that.parentsUntil("#attribute-ctn").find(".js-attr-constraint").val(obj[val]["constraint"]);
	that.parentsUntil("#attribute-ctn").find(".js-attrwidget").val(obj[val]["attrwidget"]);
}