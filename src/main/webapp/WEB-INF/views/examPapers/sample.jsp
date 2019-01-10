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
				<div class="container-fluid">
					<div class="page-header">
						<h1>
							<fmt:message key="questions.index"></fmt:message>
						</h1>
					</div>
					<form:form action="examPapers/sample_update" method="POST"
						modelAttribute="examPaper">
						<form:hidden path="id" />
						<input type="hidden" name="_method" value="PUT" />
						<div class="row">
							<div class="col-md-1">
								<button type="submit" class="btn btn-success">
									<fmt:message key="btn.save"></fmt:message>
								</button>
								</a>
							</div>
							<div class="col-md-2">
								<a href="examPapers/${id }/random" class="btn btn-primary btn-block">
									<fmt:message key="btn.random"></fmt:message>
								</a>
							</div>
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
									<th data-field="examPoint.name" data-filter-control="input"><fmt:message
											key="examPoint.name"></fmt:message></th>
									<th data-field="type" data-filter-control="select"><fmt:message
											key="question.type"></fmt:message></th>
									<th data-field="level.name" data-filter-control="select"><fmt:message
											key="level.name"></fmt:message></th>
									<th data-field="question.utilityTime"
										data-filter-control="select"><fmt:message
											key="question.utilityTime"></fmt:message></th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${ !empty questions }">
									<c:forEach items="${ questions }" var="question"
										varStatus="status">
										<tr>
											<td><form:checkbox path="questionIds"
													value="${question.id }" /></td>
											<td>${fn:substring(question.title, 0, 50) }...</td>
											<td>${fn:substring(question.examPoint.name, 0, 50) }...</td>
											<td><c:choose>
													<c:when test="${question.type == 1 }">
														<fmt:message key="questions.multiple"></fmt:message>
													</c:when>
													<c:otherwise>
														<fmt:message key="questions.essay"></fmt:message>
													</c:otherwise>
												</c:choose></td>
											<td><c:if test="${!empty question.level }">
												${ question.level.name }
											</c:if></td>
											<td>${ question.utilityTime }</td>
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>