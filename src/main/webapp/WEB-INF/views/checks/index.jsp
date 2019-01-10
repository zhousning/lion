<%@ page isELIgnored="false" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<title><fmt:message key="questions.index"></fmt:message></title>
<%@ include file="/WEB-INF/views/layouts/common.jsp"%>
<link href="static/stylesheets/questions.css" rel="stylesheet">
<script src="static/javascripts/questions.js"></script>



</head>
<body class="">
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
							<fmt:message key="questions.index"></fmt:message>
						</h1>
					</div>
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
								<th data-field="title" data-filter-control="input"><fmt:message
										key="question.title"></fmt:message></th>
								<th data-field="subject.name" data-filter-control="select"><fmt:message
										key="subject.name"></fmt:message></th>
								<th data-field="level.name" data-filter-control="select"><fmt:message
										key="level.name"></fmt:message></th>
								<th data-field="status" data-filter-control="select"><fmt:message
										key="question.status"></fmt:message></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${ !empty requestScope.questions }">
								<c:forEach items="${ requestScope.questions }" var="question"
									varStatus="status">
									<tr>
										<td>${ status.index + 1 }</td>
										<td>${fn:substring(question.title, 0, 50) }...</td>
										<td><c:if test="${!empty question.subject }">
												${ question.subject.name }
											</c:if></td>
										<td><c:if test="${!empty question.level }">
												${ question.level.name }
											</c:if></td>
										<td><c:choose>
												<c:when test="${ question.status == 0 }">
													<fmt:message key="question.status.pending.title"></fmt:message>
												</c:when>
												<c:otherwise>
													<fmt:message key="question.status.approved.title"></fmt:message>
												</c:otherwise>
											</c:choose></td>
										<td><a href="checks/${question.id }"
											class="btn btn-info"><fmt:message key="btn.check"></fmt:message></a>
											<a href="checks/${question.id}"
											class="delete btn btn-danger"><fmt:message
													key="btn.delete"></fmt:message></a></td>
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