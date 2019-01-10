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
<title><fmt:message key="subjects.show"></fmt:message></title>
<%@ include file="/WEB-INF/views/layouts/common.jsp"%>
<link href="static/stylesheets/subjects.css" rel="stylesheet">
<script src="static/javascripts/subjects.js"></script>
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
							<fmt:message key="subjects.show"></fmt:message>
						</h1>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<div class="row">
								<div class="col-sm-1">
									<a href="subjects/${requestScope.subject.id }/edit"
										type="button" class="btn btn-info"><fmt:message
											key="btn.edit"></fmt:message></a>
								</div>
								<div class="col-sm-1">
									<a href="subjects" type="button" class="btn btn-warning"><fmt:message
											key="btn.back"></fmt:message></a>
								</div>
							</div>
						</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-md-12">
									<form class="form-horizontal">
										<div class="form-group">
											<div class="col-sm-2 info-title">
												<fmt:message key="subject.name"></fmt:message>
												：
											</div>
											<div class="col-sm-8 info-ctn">${requestScope.subject.name }
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-2 info-title">
												<fmt:message key="subject.code"></fmt:message>
												：
											</div>
											<div class="col-sm-8 info-ctn">${requestScope.subject.code }
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-2 info-title">
												<fmt:message key="subject.leader"></fmt:message>
												：
											</div>
											<div class="col-sm-8 info-ctn">${requestScope.leader }</div>
										</div>
										<div class="form-group">
											<div class="col-sm-2 info-title">
												<fmt:message key="subject.group"></fmt:message>
												：
											</div>
											<div class="col-sm-8 info-ctn">
												<c:if test="${!empty requestScope.subject.users }">
													<c:forEach items="${requestScope.subject.users }"
														var="user">
												${user.name }
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