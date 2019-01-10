<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layouts/jsp_header.jsp"%>


<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>


<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title><fmt:message key="examPoints.edit"></fmt:message></title>
<%@ include file="/WEB-INF/views/layouts/common.jsp"%>
<link href="static/stylesheets/examPoints.css" rel="stylesheet">
<script src="static/javascripts/examPoints.js"></script>
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
						<fmt:message key="examPoints.edit"></fmt:message>
					</h1>
					</div>
					<div class="row">
						<div class="col-md-4 col-md-offset-4">
							<%@ include file="/WEB-INF/views/examPoints/_form.jsp"%>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>