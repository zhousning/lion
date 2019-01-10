<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layouts/jsp_header.jsp"%>

<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>

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
<title>用户登录</title>
<%@include file="/WEB-INF/views/layouts/common.jsp"%>
<link href="static/stylesheets/shiros.css" rel="stylesheet">

</head>
<body>
	<section class="login-block">
		<div class="container">
			<div class="row">
				<div class="col-md-4 login-sec">
					<h2 class="text-center">Login Now</h2>
					<%@ include file="/WEB-INF/views/users/sessions/_form.jsp"%>
				</div>
				<div class="col-md-8 banner-sec">			
					</div>
				</div>
			</div>
	</section>
</body>
</html>