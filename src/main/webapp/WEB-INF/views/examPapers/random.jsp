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
						<%@ include file="/WEB-INF/views/examPapers/_random_form.jsp"%>
					</div>

					<form:form action="examPapers/sample_update" method="POST"
						modelAttribute="examPaper">
						<form:hidden path="id" />
						<input type="hidden" name="_method" value="PUT" />
						<div class="panel panel-default">
							<div class="panel-heading">
								<div class="row">
									<div class="col-md-1">
										<button id="random-submit" type="submit" class="btn btn-success" disabled="disabled">
											<fmt:message key="btn.save"></fmt:message>
										</button>
										</a>
									</div>
								</div>
							</div>
							<div id="question-ctn" class="panel-body"></div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>