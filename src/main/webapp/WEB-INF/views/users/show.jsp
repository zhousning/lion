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
<title><fmt:message key="users.show"></fmt:message></title>
<%@ include file="/WEB-INF/views/layouts/common.jsp"%>
<link href="static/stylesheets/users.css" rel="stylesheet">
<script src="static/javascripts/users.js"></script>
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
							<fmt:message key="users.show"></fmt:message>
						</h1>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<div class="row">
								<%-- <div class="col-sm-1">
									<a href="users/${requestScope.user.id }/edit" type="button"
										class="btn btn-info"><fmt:message key="btn.edit"></fmt:message></a>
								</div>
								<div class="col-sm-1">
									<a href="users" type="button" class="btn btn-warning"><fmt:message
											key="btn.back"></fmt:message></a>
								</div> --%>
							</div>
						</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal">
										<div class="form-group">
											<div class="col-sm-2 info-title">
												<fmt:message key="user.name"></fmt:message>
												：
											</div>
											<div class="col-sm-8 info-ctn">${requestScope.user.name }</div>
										</div>
										<div class="form-group">
											<div class="col-sm-2 info-title">
												<fmt:message key="user.phone"></fmt:message>
												：
											</div>
											<div class="col-sm-8 info-ctn">${requestScope.user.phone }</div>
										</div>
										<div class="form-group">
											<div class="col-sm-2 info-title">
												<fmt:message key="user.email"></fmt:message>
												：
											</div>
											<div class="col-sm-8 info-ctn">${requestScope.user.email }</div>
										</div>
										<div class="form-group">
											<div class="col-sm-2 info-title">
												<fmt:message key="subject.type"></fmt:message>
												：
											</div>
											<div class="col-sm-8 info-ctn">
												<c:if test="${!empty requestScope.user.subjects }">
													<c:forEach items="${ requestScope.user.subjects }"
														var="subject">
												${subject.name }
											</c:forEach>
												</c:if>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>