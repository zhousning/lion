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
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title><fmt:message key="home.index"></fmt:message></title>
<%@ include file="/WEB-INF/views/layouts/common.jsp"%>
<link href="static/stylesheets/home.css" rel="stylesheet">
<script src="static/javascripts/home.js"></script>

</head>
<body>
	<%@ include file="/WEB-INF/views/layouts/header.jsp"%>
	<div class="container-fluid body-container">
		<div class="row body-box">
			<div class="col-md-2 sidebar-container">
				<%@ include file="/WEB-INF/views/layouts/sidebar.jsp"%>
			</div>
			<div class="col-md-10 main-container">
				<div class="row">
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<div class="offer offer-success">
							<div class="shape">
								<div class="shape-text">1</div>
							</div>
							<div class="offer-content">
								<h3 class="lead">Subjects</h3>
								<h4>Total: ${ fn:length(user.subjects) }</h4>
							</div>
						</div>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<div class="offer offer-warning">
							<div class="shape">
								<div class="shape-text">2</div>
							</div>
							<div class="offer-content">
								<h3 class="lead">Questions</h3>
								<h4>Total: ${ questionCount }</h4>
							</div>
						</div>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<div class="offer offer-danger">
							<div class="shape">
								<div class="shape-text">3</div>
							</div>
							<div class="offer-content">
								<h3 class="lead">ExamPapers</h3>
								<h4>Total: ${ examPaperCount }</h4>
							</div>
						</div>
					</div>
				</div>
			<div class="row body-box">
			<div class="col-md-10 main-container">
				<div class="container-fluid">
					<div class="page-header">
						<h1>
							<fmt:message key="users.edit"></fmt:message>
						</h1>
					</div>
					<div class="row">
						<shiro:lacksRole name="admin">
							<%@ include file="/WEB-INF/views/users/_form.jsp"%>
						</shiro:lacksRole>
						<shiro:hasRole name="admin">
							<%@ include file="/WEB-INF/views/users/_admin_form.jsp"%>
						</shiro:hasRole>
					</div>
				</div>
			</div>
		</div>
			</div>
		</div>
		
	</div>
</body>
</html>