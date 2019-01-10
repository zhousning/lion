<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layouts/jsp_header.jsp"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<title>init</title>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@ include file="/WEB-INF/views/layouts/common.jsp"%>

<script type="text/javascript">
 $(document).ready(function(){
 	$("#js-initdata").click(function(){
 		var url = "seeds/initdata";
 		$.get(url, function(result){
 			alert(result["status"]);
 		});
 	});
 });
 
</script>

</head>
<body>
	<button type="button" class="btn btn-primary" id="js-initdata">initdata</button>
</body>
</html>