<form:form
	action="questions/${question.id != null ? 'update' : 'create'}"
	method="POST" modelAttribute="question" class="form-horizontal"
	enctype="multipart/form-data">
	<c:if test="${question.id != null}">
		<form:hidden path="id" />
	</c:if>
	<form:hidden path="type" value="1" />
	<div class="form-group">
		<label for="subject" class="col-sm-2 control-label"><fmt:message
				key="subject.name"></fmt:message></label>
		<div class="col-sm-10">
			<form:select class="form-control js-subjectid" path="subject.id">
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
				items="${requestScope.examPoints}" itemLabel="name" itemValue="id"></form:select>
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
			<form:input class="form-control" id="title"
				placeholder="title" path="title"/>
			<form:errors path="title"></form:errors>
		</div>
	</div>
	<div class="form-group">
		<label for="content" class="col-sm-2 control-label"><fmt:message
				key="option.items"></fmt:message></label>
		<div class="col-sm-10">
			<form:textarea type="text" class="form-control" id="content"
				path="content" rows="5" />
			<form:errors path="content"></form:errors>
		</div>
	</div>
	<div class="form-group">
		<label for="attachment" class="col-sm-2 control-label"><fmt:message
				key="image.title"></fmt:message></label>
		<div class="col-sm-10">
			<label class=newbtn> <c:choose>
					<c:when test="${!empty question.attachments }">
						<c:forEach items="${question.attachments }" var="attachment">
							<img id="blah" src="${attachment.file}" />
						</c:forEach>
					</c:when>
					<c:otherwise>
						<img id="blah" src="static/images/image-upload.png">
					</c:otherwise>
				</c:choose> <input id="pic" class='pis' onchange="readURL(this);" type="file"
				name="attachment">
			</label>
		</div>
	</div>
	<div class="form-group">
		<label for="answer" class="col-sm-2 control-label"><fmt:message
				key="question.answer"></fmt:message></label>
		<div class="col-sm-10">
			<form:input type="answer" class="form-control" id="answer"
				placeholder="answer" path="answer" />
			<form:errors path="answer"></form:errors>
		</div>
	</div>
	<div class="form-group">
		<label for="analysis" class="col-sm-2 control-label"><fmt:message
				key="question.analysis"></fmt:message></label>
		<div class="col-sm-10">
			<form:textarea type="analysis" class="form-control" id="analysis"
				path="analysis" rows="5" />
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