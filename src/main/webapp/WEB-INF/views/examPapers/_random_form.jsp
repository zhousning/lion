<form class="form-inline" id="randomForm" action="##" method="post">
	<input type="hidden" name="subjectId" value="${subjectId }" />
	<div class="row">
		<div class="col-md-3">
			<div class="form-group">
				<label for="levelId" class="col-sm-6 control-label"><fmt:message
						key="level.name"></fmt:message></label>
				<div class="col-sm-6">
					<select class="form-control" name="levelId">
						<c:forEach items="${levels}" var="level">
							<option value="${level.id }">${level.name }</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</div>
		<div class="col-md-3">
			<div class="form-group">
				<label for="singleCount" class="col-sm-4 control-label"><fmt:message
						key="questions.multiple"></fmt:message> Count</label>
				<div class="col-sm-6">
					<input type="number" class="form-control" name="multipleCount"
						min="1" value="5"/>
				</div>
			</div>
		</div>
		<div class="col-md-4">
			<div class="form-group">
				<label for="essayCount" class="col-sm-5 control-label"><fmt:message
						key="questions.essay"></fmt:message> Count</label>
				<div class="col-sm-6">
					<input type="number" class="form-control" name="essayCount" min="1"  value="5"/>
				</div>
			</div>
		</div>
	</div>

	<div class="row margin-top-15">
		<div class="col-md-4">
			<div class="form-group">
				<label class="col-sm-2 control-label"><fmt:message
						key="examPaper.date"></fmt:message></label>
				<div class="col-sm-4">
					<input type="date" class="form-control" name="start" />
				</div>
				<div class="col-sm-4 col-sm-offset-2">
					<input type="date" class="form-control" name="end" />
				</div>
			</div>
		</div>
		<div class="col-md-4">
			<button type="submit" class="btn btn-success">
				<fmt:message key="btn.random"></fmt:message>
			</button>
		</div>
	</div>
</form>