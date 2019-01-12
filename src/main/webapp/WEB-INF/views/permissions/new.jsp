<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/views/layouts/jsp_header.jsp"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title><fmt:message key="permissions.new"></fmt:message></title>
<%@include file="/WEB-INF/views/layouts/common.jsp"%>
<link href="static/stylesheets/permissions.css" rel="stylesheet">
<script src="static/javascripts/permissions.js"></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/layouts/header.jsp"%>
	<div class="container-fluid body-container">
		<div class="row body-box">
			<div class="col-md-2 sidebar-container">
				<%@ include file="/WEB-INF/views/layouts/sidebar.jsp"%>
			</div>
			<div class="col-md-10 main-container">
				<div class="container-fluid">
					<div class="page-header">
						<h1>
							<fmt:message key="permissions.new"></fmt:message>
						</h1>
					</div>
					<div class="row">
						<div class="col-md-8 col-md-offset-2">
							<%@ include file="/WEB-INF/views/permissions/_form.jsp"%>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>