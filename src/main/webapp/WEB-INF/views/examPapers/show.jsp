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
<title><fmt:message key="examPapers.show"></fmt:message></title>
<%@ include file="/WEB-INF/views/layouts/common.jsp"%>
<link href="static/stylesheets/examPapers.css" rel="stylesheet">
<script src="static/javascripts/examPapers.js"></script>
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
							<fmt:message key="examPapers.show"></fmt:message>
						</h1>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<div class="row">
								<shiro:hasAnyRoles name="leader,teacher">
								<c:if test="${ examPaper.status != '2' }">
								<div class="col-sm-1">
									<a href="examPapers/${examPaper.id }/edit" type="button"
										class="btn btn-info"><fmt:message key="btn.edit"></fmt:message></a>
								</div>
								</c:if>
								</shiro:hasAnyRoles>
								<div class="col-sm-1">
									<a href="examPapers" type="button" class="btn btn-warning"><fmt:message
											key="btn.back"></fmt:message></a>
								</div>
								<shiro:hasRole name="leader">
								<c:if test="${ examPaper.status != '2' }">
								<div class="col-sm-1">
									<a href="checks/examPaper/${examPaper.id }/pass" type="button" class="btn btn-success"><fmt:message
											key="btn.pass"></fmt:message></a>
								</div>
								<div class="col-sm-1">
									<a href="checks/examPaper/${examPaper.id }/reject" type="button" class="btn btn-danger"><fmt:message
											key="btn.reject"></fmt:message></a>
								</div>
								</c:if>
								</shiro:hasRole>
								<shiro:hasRole name="admin">
								<div class="col-sm-1">
									<a href="checks/examPaper/${examPaper.id }/publish" type="button" class="btn btn-success"><fmt:message
											key="btn.publish"></fmt:message></a>
								</div>
								<div class="col-sm-1">
									<a href="checks/examPaper/${examPaper.id }/reject" type="button" class="btn btn-danger"><fmt:message
											key="btn.reject"></fmt:message></a>
								</div>
								</shiro:hasRole>
								<div class="col-sm-2 info-ctn">
								<c:choose>
										<c:when test="${ examPaper.status == 0 }">
											<fmt:message key="examPaper.status.pending.title"></fmt:message>
										</c:when>
										<c:when test="${ examPaper.status == 1 }">
											<fmt:message key="examPaper.status.approved.title"></fmt:message>
										</c:when>
										<c:otherwise>
											<fmt:message key="examPaper.status.publish.title"></fmt:message>
										</c:otherwise>
									</c:choose>
									
								</div>
							</div>
						</div>
						<div class="panel-body">
							<table class="table  table-bordered">
								<thead>
									<th><fmt:message key="examPaper.major"></fmt:message></th>
									<th><fmt:message key="examPaper.date"></fmt:message></th>
									<th><fmt:message key="examPaper.duration"></fmt:message></th>
									<th><fmt:message key="examPaper.space"></fmt:message></th>
									<th><fmt:message key="subject.name"></fmt:message></th>
								</thead>
								<tbody>
									<td>${examPaper.major }</td>
									<td>${examPaper.date }</td>
									<td>${examPaper.duration }</td>
									<td>${examPaper.space }</td>
									<td>${examPaper.subject.name }</td>
								</tbody>
							</table>
							<h3 class="text-center"><fmt:message key="examPaper.introduction"></fmt:message></h3>
							<p>${examPaper.introduction }</p>
							<h3 class="text-center"><fmt:message key="examPaper.partAInfo"></fmt:message></h3>
							<p class="text-center">${examPaper.partAInfo }</p>
							<c:if test="${!empty examPaper.questions }">
								<c:forEach items="${examPaper.questions }" var="question" varStatus="status">
									<c:if test="${question.type == '1' }">
										${ status.index + 1 }、${question.title }
									</c:if>
								</c:forEach>
							</c:if>
							<h3 class="text-center"><fmt:message key="examPaper.partBInfo"></fmt:message></h3>
							<p class="text-center">${examPaper.partBInfo }</p>
							<c:if test="${!empty examPaper.questions }">
								<c:forEach items="${examPaper.questions }" var="question"  varStatus="status">
									<c:if test="${question.type == '2' }">
										${ status.index + 1 }、${question.title }
									</c:if>
								</c:forEach>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>