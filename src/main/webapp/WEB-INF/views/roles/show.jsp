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
<title><fmt:message key="roles.show"></fmt:message></title>
<%@ include file="/WEB-INF/views/layouts/common.jsp"%>
<link href="static/stylesheets/roles.css" rel="stylesheet">
<script src="static/javascripts/roles.js"></script>
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
							<fmt:message key="roles.show"></fmt:message>
						</h1>
					</div>
					<div class="row">
						<div class="col-md-4 col-md-offset-4">
							<form class="form-horizontal">
								<div class="form-group">
									<div class="col-sm-4 info-title"><fmt:message
											key="role.name"></fmt:message>：</div>
									<div class="col-sm-8 info-ctn">${requestScope.role.name }</div>
								</div>
								<div class="form-group">
								<div class="col-sm-offset-2 col-sm-4">
										<a href="roles/${requestScope.role.id }/edit" type="button" class="btn btn-info"><fmt:message
												key="btn.edit"></fmt:message></a>
									</div>
									<div class="col-sm-offset-2 col-sm-4">
										<a href="roles" type="button" class="btn btn-warning"><fmt:message
												key="btn.back"></fmt:message></a>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>