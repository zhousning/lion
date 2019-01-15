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
<title><fmt:message key="jobs.show"></fmt:message></title>
<%@ include file="/WEB-INF/views/layouts/common.jsp"%>
<link href="static/stylesheets/jobs.css" rel="stylesheet">
<script src="static/javascripts/jobs.js"></script>
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
							<fmt:message key="jobs.show"></fmt:message>
						</h1>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<div class="row">
								<div class="col-sm-1">
									<a href="jobs/${job.id }/edit"
												class="btn btn-success"><fmt:message key="btn.edit"></fmt:message></a>
								</div>
								<div class="col-sm-1">
									<a href="jobs/index" type="button" class="btn btn-warning"><fmt:message
											key="btn.back"></fmt:message></a>
								</div>
							</div>
						</div>
						<div class="panel-body">
							<table class="table table-bordered">
								<thead>
									<tr>
										<th><fmt:message key="job.name"></fmt:message></th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>${job.name}</td>
									</tr>
								</tbody>
							</table>
							<p>
								<c:if test="${!empty job.imageAttachments }">
									<c:forEach items="${ job.imageAttachments }" var="attachment">
										<img class="blah" src="${attachment.url }" class="img-rounded">
									</c:forEach>
								</c:if>
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>