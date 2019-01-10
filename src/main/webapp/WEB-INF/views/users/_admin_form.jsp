<form:form action="users" method="POST" modelAttribute="user"
	class="form-horizontal">
	<c:if test="${user.id != null }">
		<form:hidden path="id" />
		<input type="hidden" name="_method" value="PUT" />
	</c:if>
	<div class="col-md-10 col-md-offset-2">
		<div class="form-group">
			<label for="name" class="col-sm-2 control-label"><fmt:message
					key="subject.type"></fmt:message></label>
			<div class="col-sm-10">
				<form:checkboxes path="subjectIds" items="${requestScope.subjects}"
					element="label class='checkbox-inline'" itemLabel="name"
					itemValue="id" />
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-4">
				<div class="col-sm-offset-1 col-sm-4">
					<button type="submit" class="btn btn-success">
						<fmt:message key="btn.save"></fmt:message>
					</button>
				</div>
			</div>
		</div>
</form:form>