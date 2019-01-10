<form:form action="users/sign_up" method="POST" modelAttribute="user"
	class="login-form">
	<c:if test="${user.id != null }">
		<form:hidden path="id" />
		<input type="hidden" name="_method" value="PUT" />
	</c:if>
	<div class="form-group">
		<label for="exampleInputName1" class="text-uppercase"><fmt:message key="user.name"></fmt:message></label>
		<form:input class="form-control" placeholder="" path="name" />
		<form:errors path="name"></form:errors>
	</div>
	<div class="form-group">
		<label for="exampleInputPhone1" class="text-uppercase"><fmt:message key="user.phone"></fmt:message></label>
		<form:input class="form-control" placeholder="" path="phone" />
		<form:errors path="phone"></form:errors>
	</div>
	<div class="form-group">
		<label for="exampleInputEmail1" class="text-uppercase"><fmt:message key="user.email"></fmt:message></label>
		<form:input class="form-control" placeholder="" path="email" />
		<form:errors path="email"></form:errors>
	</div>
	<div class="form-group">
		<label for="exampleInputPassword1" class="text-uppercase"><fmt:message key="user.password"></fmt:message></label>
		<form:input  type="password"  class="form-control" placeholder=""  path="password"/>
		<form:errors path="password"></form:errors>
	</div>
	<div class="form-group">
		<div class="col-md-4">
			<input type="submit" name="commit" value="Sign
			up"
				class="btn btn-block btn-login float-right">
		</div>
		<div class="col-md-4 col-md-offset-3">
			<a class="btn btn-success btn-block" href="users/sign_in">Sign in</a> <br>
		</div>
	</div>
</form:form>