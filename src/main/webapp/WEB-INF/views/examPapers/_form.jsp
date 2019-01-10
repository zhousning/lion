<form:form action="examPapers" method="POST" modelAttribute="examPaper"
	class="form-horizontal">
	<!-- <form:errors path="*"></form:errors> -->
	<c:if test="${examPaper.id != null }">
		<form:hidden path="id" />
		<input type="hidden" name="_method" value="PUT" />
	</c:if>
	<div class="form-group">
		<label for="major" class="col-sm-2 control-label"><fmt:message key="examPaper.major"></fmt:message></label>
		<div class="col-sm-10">
			<form:input type="text" class="form-control" id="major"
				placeholder="major" path="major" />
				<form:errors path="major"></form:errors>
		</div>
	</div>
	<div class="form-group">
		<label for="date" class="col-sm-2 control-label"><fmt:message key="examPaper.date"></fmt:message></label>
		<div class="col-sm-10">
			<form:input type="text" class="form-control" id="date"
				placeholder="date" path="date" />
				<form:errors path="date"></form:errors>
		</div>
	</div>
	<div class="form-group">
		<label for="duration" class="col-sm-2 control-label"><fmt:message key="examPaper.duration"></fmt:message></label>
		<div class="col-sm-10">
			<form:input type="text" class="form-control" id="duration"
				placeholder="duration" path="duration" />
			<form:errors path="duration"></form:errors>
		</div>
	</div>
	<div class="form-group">
		<label for="space" class="col-sm-2 control-label"><fmt:message key="examPaper.space"></fmt:message></label>
		<div class="col-sm-10">
			<form:input type="text" class="form-control" id="space"
				path="space" />
			<form:errors path="space"></form:errors>
		</div>
	</div>
	<div class="form-group">
		<label for="introduction" class="col-sm-2 control-label"><fmt:message key="examPaper.introduction"></fmt:message></label>
		<div class="col-sm-10">
			<form:textarea class="form-control" id="introduction"
				path="introduction" rows="10" cols="20"/>
			<form:errors path="introduction"></form:errors>
		</div>
	</div>
	<div class="form-group">
		<label for="partAInfo" class="col-sm-2 control-label"><fmt:message key="examPaper.partAInfo"></fmt:message></label>
		<div class="col-sm-10">
			<form:textarea class="form-control" id="partAInfo"
				path="partAInfo" />
			<form:errors path="partAInfo"></form:errors>
		</div>
	</div>
	<div class="form-group">
		<label for="partBInfo" class="col-sm-2 control-label"><fmt:message key="examPaper.partBInfo"></fmt:message></label>
		<div class="col-sm-10">
			<form:textarea class="form-control" id="partBInfo"
				path="partBInfo" />
			<form:errors path="partBInfo"></form:errors>
		</div>
	</div>
	<div class="form-group">
		<label for="subject" class="col-sm-2 control-label"><fmt:message key="subject.name"></fmt:message></label>
		<div class="col-sm-10">
			<form:select class="form-control" path="subject.id">
				<form:options items="${requestScope.subjects}" itemLabel="name"
					itemValue="id" />
			</form:select>
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-4">
			<button type="submit" class="btn btn-success"><fmt:message key="btn.save"></fmt:message></button>
		</div>
		<div class="col-sm-offset-2 col-sm-4">
			<a href="examPapers" type="button" class="btn btn-warning"><fmt:message key="btn.back"></fmt:message></a>
		</div>
	</div>
</form:form>