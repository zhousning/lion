<form:form action="roles" method="POST" modelAttribute="role"
	class="form-horizontal">
	<!-- <form:errors path="*"></form:errors> -->
	<c:if test="${role.id != null }">
		<form:hidden path="id" />
		<input type="hidden" name="_method" value="PUT" />
	</c:if>
	<div class="form-group">
		<label for="name" class="col-sm-2 control-label"><fmt:message key="role.name"></fmt:message></label>
		<div class="col-sm-10">
			<form:input type="text" class="form-control" id="name"
				placeholder="name" path="name" />
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-4">
			<button type="submit" class="btn btn-success"><fmt:message key="btn.save"></fmt:message></button>
		</div>
		<div class="col-sm-offset-2 col-sm-4">
			<a href="roles" type="button" class="btn btn-warning"><fmt:message key="btn.back"></fmt:message></a>
		</div>
	</div>
</form:form>