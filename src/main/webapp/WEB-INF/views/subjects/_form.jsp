<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<form:form action="subjects" method="POST" modelAttribute="subject"
	class="form-horizontal">
	<!-- <form:errors path="*"></form:errors> -->
	<c:if test="${subject.id != null }">
		<form:hidden path="id" />
		<input type="hidden" name="leaderIdentity"
			value="${subject.leaderId }" />
		<input type="hidden" name="_method" value="PUT" />
	</c:if>
	<div class="col-md-4 col-md-offset-1">
		<div class="form-group">
			<label for="name" class="col-sm-2 control-label"><fmt:message
					key="subject.name"></fmt:message></label>
			<div class="col-sm-10">
				<form:input type="text" class="form-control" id="name"
					placeholder="name" path="name" />
				<form:errors path="name"></form:errors>
			</div>
		</div>
		<div class="form-group">
			<label for="code" class="col-sm-2 control-label"><fmt:message
					key="subject.code"></fmt:message></label>
			<div class="col-sm-10">
				<form:input type="text" class="form-control" id="code"
					placeholder="" path="code" />
				<form:errors path="code"></form:errors>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-4">
				<button type="submit" class="btn btn-success">
					<fmt:message key="btn.save"></fmt:message>
				</button>
			</div>
			<div class="col-sm-offset-2 col-sm-4">
				<a href="subjects" type="button" class="btn btn-warning"><fmt:message
						key="btn.back"></fmt:message></a>
			</div>
		</div>
	</div>
	<div class="col-md-4 col-md-offset-2">
		<c:if test="${subject.id != null }">
			<h3>
				<fmt:message key="subject.group"></fmt:message>
			</h3>
			<c:if test="${!empty requestScope.subject.users }">
				<c:forEach items="${ requestScope.subject.users }" var="user">
					<c:if test="${user.email != 'admin@admin.com' }">
						<c:choose>
							<c:when
								test="${ fn:length(user.roles) > 1 && user.id != requestScope.subject.leaderId}">
								<div class="radio disabled">
									<label> <form:radiobutton path="leaderId"
											value="${user.id }" disabled="true" label="${user.name}" />
									</label>
								</div>
							</c:when>
							<c:otherwise>
								<div class="radio">
									<label> <form:radiobutton path="leaderId"
											value="${user.id }" />${user.name}
									</label>
								</div>
							</c:otherwise>
						</c:choose>
					</c:if>
				</c:forEach>
			</c:if>
		</c:if>
	</div>

</form:form>