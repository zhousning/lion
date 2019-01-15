<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<form:form action="models" method="POST" modelAttribute="model"
	class="form-horizontal">

	<div class="form-group">
		<label for="name" class="col-sm-2 control-label">Model Name</label>
		<div class="col-sm-3">
			<form:input type="text" class="form-control" placeholder="name"
				path="name" />
		</div>
	</div>
	<div class="form-group">
		<label for="title" class="col-sm-2 control-label">Model Title</label>
		<div class="col-sm-3">
			<form:input type="text" class="form-control" placeholder="title"
				path="title" />
		</div>
	</div>
	
	<h2>
	如果不需要就删掉
	</h2>
	
	<%@ include file="/WEB-INF/views/models/_attribute_form.jsp"%>
	
	<%@ include file="/WEB-INF/views/models/_associate_form.jsp"%>
	
	<%@ include file="/WEB-INF/views/models/_plugin_form.jsp"%>
	

	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-4">
			<button type="submit" class="btn btn-success">
				<fmt:message key="btn.save"></fmt:message>
			</button>
		</div>
		<div class="col-sm-offset-2 col-sm-4">
			<a href="levels" type="button" class="btn btn-warning"><fmt:message
					key="btn.back"></fmt:message></a>
		</div>
	</div>
</form:form>