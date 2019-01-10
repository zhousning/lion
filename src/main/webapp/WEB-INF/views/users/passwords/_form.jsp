<form:form action="users/reset_password" method="POST" modelAttribute="user"
	class="login-form">
	
	<div class="form-group">
		<label for="exampleInputEmail1" class="text-uppercase"><fmt:message key="user.email"></fmt:message></label>
		<form:input class="form-control" placeholder="" path="email" />
		<form:errors path="email"></form:errors>
	</div>
	<div class="form-group">
		<label for="exampleInputPassword1" class="text-uppercase"><fmt:message key="user.password.new"></fmt:message></label>
		<form:input  type="password"  class="form-control" placeholder=""  path="password"/>
		<form:errors path="password"></form:errors>
	</div>
	<div class="form-inline margin-bottom-15">
		<div class="form-group">
			<input  type="text"  class="form-control "  name="verification"/>
		</div>
		<button type="button" id="verification-btn" class="btn btn-primary"><fmt:message key="verification.code"></fmt:message></button>	
	</div>
	<div class="form-group">
		<div class="col-md-4">
			<input type="submit" name="commit" value="Reset"
				class="btn btn-block btn-login float-right">
		</div>
		<div class="col-md-4 col-md-offset-3">
			<a class="btn btn-success btn-block" href="users/sign_in">Sign in</a> <br>
		</div>
	</div>
</form:form>