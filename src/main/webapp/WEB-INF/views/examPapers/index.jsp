<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<title><fmt:message key="examPapers.index"></fmt:message></title>
<%@ include file="/WEB-INF/views/layouts/common.jsp"%>
<link href="static/stylesheets/examPapers.css" rel="stylesheet">
<script src="static/javascripts/examPapers.js"></script>



</head>
<body class=".examPapers.index">
	<%@ include file="/WEB-INF/views/layouts/header.jsp"%>
	<div class="container-fluid body-container">
		<div class="row body-box">
			<div class="col-md-2 sidebar-container">
				<%@ include file="/WEB-INF/views/layouts/sidebar.jsp"%>
			</div>
			<div class="col-md-10 main-container">
				<form action="" method="POST">
					<input type="hidden" name="_method" value="DELETE" />
				</form>
				<div class="container-fluid">
					<div class="page-header">
						<h1>
							<fmt:message key="examPapers.index"></fmt:message>
						</h1>
					</div>
					<shiro:lacksRole name = "admin">
					<div class="row">
						<div class="col-md-1">
							<a href="examPapers/new" class="btn btn-primary btn-block"> <fmt:message
									key="btn.add"></fmt:message>
							</a>
						</div>
					</div>
					</shiro:lacksRole>
					<table id="table" data-toggle="table" data-filter-control="true"
						data-height="600" data-pagination="true" data-page-size="20"
						data-page-list="[5,8,10]" data-pagination-first-text="First"
						data-pagination-pre-text="Previous"
						data-pagination-next-text="Next" data-pagination-last-text="Last"
						data-toolbar="#toolbar" data-search="true"
						data-trim-on-search="true" data-align="center"
						class="table table-hover text-center">
						<thead>
							<tr class="text-center">
								<th data-field="id">id</th>
								<th data-field="major" data-filter-control="input"><fmt:message
										key="examPaper.major"></fmt:message></th>
								<th data-field="subject" data-filter-control="select"><fmt:message
										key="subject.name"></fmt:message></th>
								<th data-field="phone" data-filter-control="select"><fmt:message
										key="examPaper.date"></fmt:message></th>
								<th data-field="email" data-filter-control="select"><fmt:message
										key="examPaper.status"></fmt:message></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${ !empty requestScope.examPapers }">
								<c:forEach items="${ requestScope.examPapers }" var="examPaper"
									varStatus="status">
									<tr>
										<td>${ status.index + 1 }</td>
										<td>${ examPaper.major }</td>
										<td>${ examPaper.subject.name }</td>
										<td>${ examPaper.date }</td>
										<td>
											<c:choose>
												<c:when test="${ examPaper.status == '0' }"><fmt:message key="examPaper.status.pending.title"></fmt:message></c:when>
												<c:when test="${ examPaper.status == '1' }"><fmt:message key="examPaper.status.approved.title"></fmt:message></c:when>
												<c:otherwise><fmt:message key="examPaper.status.publish.title"></fmt:message></c:otherwise>
											</c:choose>
										</td>
										<td><a href="examPapers/${examPaper.id }"
											class="btn btn-info"><fmt:message key="btn.info"></fmt:message></a>
											
											<shiro:hasAnyRoles name="leader,teacher">
												<c:if test="${ examPaper.status != '2' }">
													<a href="examPapers/${examPaper.id }/edit"
												class="btn btn-success"><fmt:message key="btn.edit"></fmt:message></a>
												</c:if>
											</shiro:hasAnyRoles>
											
											<c:if test="${ examPaper.status != '2' }">
												<a href="examPapers/${examPaper.id}"
											class="delete btn btn-danger"><fmt:message
													key="btn.delete"></fmt:message></a>
											</c:if>	
											
											<a href="examPapers/${examPaper.id}/export_doc"
											class="btn btn-primary"><fmt:message
													key="btn.export"></fmt:message></a>
										</td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>