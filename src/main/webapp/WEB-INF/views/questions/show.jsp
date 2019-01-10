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
<title><fmt:message key="questions.show"></fmt:message></title>
<%@ include file="/WEB-INF/views/layouts/common.jsp"%>
<link href="static/stylesheets/questions.css" rel="stylesheet">
<script src="static/javascripts/questions.js"></script>
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
							<fmt:message key="questions.show"></fmt:message>
						</h1>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<div class="row">
								<div class="col-sm-1">
									<c:choose>
										<c:when test="${question.type == 1 }">
											<a href="questions/${question.id }/multiple/edit"
												class="btn btn-success"><fmt:message key="btn.edit"></fmt:message></a>
										</c:when>
										<c:otherwise>
											<a href="questions/${question.id }/essay/edit"
												class="btn btn-success"><fmt:message key="btn.edit"></fmt:message></a>
										</c:otherwise>
									</c:choose>
								</div>
								<div class="col-sm-1">
									<a href="questions" type="button" class="btn btn-warning"><fmt:message
											key="btn.back"></fmt:message></a>
								</div>
							</div>
						</div>
						<div class="panel-body">
							<table class="table table-bordered">
								<thead>
									<th><fmt:message key="subject.name"></fmt:message></th>
									<th><fmt:message key="level.name"></fmt:message></th>
									<th><fmt:message key="examPoint.name"></fmt:message></th>
									<th><fmt:message key="question.createTime"></fmt:message></th>
									<th><fmt:message key="question.utilityTime"></fmt:message></th>
									<th><fmt:message key="question.status"></fmt:message></th>
								</thead>
								<tbody>
									<tr>
										<td><c:if test="${!empty question.subject }">
												${ question.subject.name }
											</c:if></td>
										<td><c:if test="${!empty question.level }">
												${ question.level.name }
											</c:if></td>
										<td><c:if test="${!empty question.examPoint }">
												${ question.examPoint.name }
											</c:if></td>
										<td>${question.createTime }</td>
										<td>${question.utilityTime }</td>
										<td><c:choose>
												<c:when test="${ question.status == 0 }">
													<fmt:message key="question.status.pending.title"></fmt:message>
												</c:when>
												<c:otherwise>
													<fmt:message key="question.status.approved.title"></fmt:message>
												</c:otherwise>
											</c:choose></td>
									</tr>
								</tbody>
							</table>
							<p>${question.title }</p>
							<p>
								<c:if test="${!empty question.attachments }">
									<c:forEach items="${ question.attachments }" var="file">
										<img id="blah" src="${file.file }" class="img-rounded">
									</c:forEach>
								</c:if>
							</p>
							<p>${question.content }</p>
							<p>${question.answer }</p>
							<P>${question.analysis }</P>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>