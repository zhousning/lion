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
<title><fmt:message key="${model.name?uncap_first}s.show"></fmt:message></title>
<%@ include file="/WEB-INF/views/layouts/common.jsp"%>
<link href="static/stylesheets/${model.name?uncap_first}s.css" rel="stylesheet">
<script src="static/javascripts/${model.name?uncap_first}s.js"></script>
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
							<fmt:message key="${model.name?uncap_first}s.show"></fmt:message>
						</h1>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<div class="row">
								<div class="col-sm-1">
									<a href="${model.name?uncap_first}s/${r'${'}${model.name?uncap_first}.id ${r'}'}/edit"
												class="btn btn-success"><fmt:message key="btn.edit"></fmt:message></a>
								</div>
								<div class="col-sm-1">
									<a href="${model.name?uncap_first}s/index" type="button" class="btn btn-warning"><fmt:message
											key="btn.back"></fmt:message></a>
								</div>
							</div>
						</div>
						<div class="panel-body">
							<table class="table table-bordered">
								<thead>
									<tr>
										<#list model.attrTypes as type>
										<th><fmt:message key="${model.name?uncap_first}.${model.attrNames[type_index]}"></fmt:message></th>
										</#list>
									</tr>
								</thead>
								<tbody>
									<tr>
										<#list model.attrTypes as type>
										<td>${r'${'}${model.name?uncap_first}.${model.attrNames[type_index]}${r'}'}</td>
										</#list>
									</tr>
								</tbody>
							</table>
							<#list model.pluginTypes as plugin>
							<#if plugin == "image">
							<p>
								<c:if test="${r'${'}!empty ${model.name?uncap_first}.imageAttachments ${r'}'}">
									<c:forEach items="${r'${'} ${model.name?uncap_first}.imageAttachments ${r'}'}" var="attachment">
										<img class="blah" src="${r'${'}attachment.url ${r'}'}" class="img-rounded">
									</c:forEach>
								</c:if>
							</p>
							</#if>
							</#list>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>