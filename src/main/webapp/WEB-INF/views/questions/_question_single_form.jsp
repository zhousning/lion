<form:form action="questions" method="POST" modelAttribute="question"
	class="form-horizontal" enctype="multipart/form-data">
	<c:if test="${question.id != null }">
		<form:hidden path="id" />
		<input type="hidden" title="_method" value="PUT" />
	</c:if>
	<form:hidden path="type" value="0" />
	<div class="form-group">
		<label for="subject" class="col-sm-2 control-label"><fmt:message
				key="subject.name"></fmt:message></label>
		<div class="col-sm-10">
			<form:select class="form-control" path="subject.id">
				<form:option value="-1" label="--- Select ---" />
				<form:options items="${requestScope.subjects}" itemLabel="name"
					itemValue="id" />
			</form:select>
		</div>
	</div>
	<div class="form-group">
		<label for="examPoint" class="col-sm-2 control-label"><fmt:message
				key="examPoint.name"></fmt:message></label>
		<div class="col-sm-10">
			<form:select class="form-control" path="examPoint.id"
				items="${requestScope.question.examPoint}" itemLabel="name"
				itemValue="id"></form:select>
		</div>
	</div>
	<div class="form-group">
		<label for="examPoint" class="col-sm-2 control-label"><fmt:message
				key="level.name"></fmt:message></label>
		<div class="col-sm-10">
			<form:select class="form-control" path="level.id"
				items="${requestScope.levels}" itemLabel="name" itemValue="id"></form:select>
		</div>
	</div>
	<div class="form-group">
		<label for="title" class="col-sm-2 control-label"><fmt:message
				key="question.title"></fmt:message></label>
		<div class="col-sm-10">
			<form:input type="text" class="form-control" id="title"
				placeholder="title" path="title" />
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-10 col-sm-offset-2">
			<p class="info">
				<fmt:message key="options.split.title"></fmt:message>
			</p>
		</div>
	</div>
	<div class="form-group">
		<label for="content" class="col-sm-2 control-label"><fmt:message
				key="option.items"></fmt:message></label>
		<div class="col-sm-10">
			<form:textarea type="text" class="form-control" id="content"
				path="content" col="20" row="10" />
		</div>
	</div>
	<div class="form-group">
		<label for="attachment" class="col-sm-2 control-label"><fmt:message
				key="image.title"></fmt:message></label>
		<div class="col-sm-10">
			<label class=newbtn> <img id="blah"
				src="static/images/image-upload.png"> 
			<input id="pic" class='pis' onchange="readURL(this);" type="file" name="attachment">
			</label>
		</div>
	</div>
	<div class="form-group">
		<label for="answer" class="col-sm-2 control-label"><fmt:message
				key="question.answer"></fmt:message></label>
		<div class="col-sm-10">
			<form:input type="answer" class="form-control" id="answer"
				placeholder="answer" path="answer" />
		</div>
	</div>
	<div class="form-group">
		<label for="analysis" class="col-sm-2 control-label"><fmt:message
				key="question.analysis"></fmt:message></label>
		<div class="col-sm-10">
			<form:textarea type="analysis" class="form-control" id="analysis"
				path="analysis" row="20" />
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-4">
			<button type="submit" class="btn btn-success">
				<fmt:message key="btn.save"></fmt:message>
			</button>
		</div>
		<div class="col-sm-offset-2 col-sm-4">
			<a href="questions" type="button" class="btn btn-warning"><fmt:message
					key="btn.back"></fmt:message></a>
		</div>
	</div>
</form:form>