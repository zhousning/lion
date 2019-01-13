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
	<div class="form-group">
		<div class="col-sm-1">
			<button type="button" class="btn btn-primary" id="btn-add-attribute">Add</button>
		</div>
	</div>
	<div id="attribute-ctn">
		<div class="form-group attr-content">
			<div class="col-sm-3">
				<select class="form-control" name="attrTypes">
					<c:forEach items="${attrTypes}" var="type">
						<option value="${type }">${type }</option>
					</c:forEach>
				</select>
			</div>
			<div class="col-sm-2">
				<input type="text" class="form-control" placeholder="label"
					name="labels" value="name" />
			</div>
			<div class="col-sm-2">
				<input type="text" class="form-control" placeholder="name"
					name="attrNames" value="name" />
			</div>
			<div class="col-sm-2">
				<select class="form-control" name="constraints">
					<c:forEach items="${constraints}" var="constraint">
						<option value="${constraint }">${constraint }</option>
					</c:forEach>
				</select>
			</div>
			<div class="col-sm-2">
				<select class="form-control" name="attrWidgets">
					<c:forEach items="${attrWidgets}" var="attrWidget">
						<option value="${attrWidget }">${attrWidget }</option>
					</c:forEach>
				</select>
			</div>
			<div class="col-sm-1">
				<button type="button" class="btn btn-danger btn-delete-attribute"
					onclick=" deleteAttr(this)">Delete</button>
			</div>
		</div>
		<div class="form-group attr-content">
			<div class="col-sm-3">
				<select class="form-control" name="attrTypes">
					<c:forEach items="${attrTypes}" var="type">
						<option value="${type }">${type }</option>
					</c:forEach>
				</select>
			</div>
			<div class="col-sm-2">
				<input type="text" class="form-control" placeholder="label"
					name="labels" />
			</div>
			<div class="col-sm-2">
				<input type="text" class="form-control" placeholder="name"
					name="attrNames" />
			</div>
			<div class="col-sm-2">
				<select class="form-control" name="constraints">
					<c:forEach items="${constraints}" var="constraint">
						<option value="${constraint }">${constraint }</option>
					</c:forEach>
				</select>
			</div>
			<div class="col-sm-2">
				<select class="form-control" name="attrWidgets">
					<c:forEach items="${attrWidgets}" var="attrWidget">
						<option value="${attrWidget }">${attrWidget }</option>
					</c:forEach>
				</select>
			</div>
			<div class="col-sm-1">
				<button type="button" class="btn btn-danger btn-delete-attribute"
					onclick=" deleteAttr(this)">Delete</button>
			</div>
		</div>
	</div>

	<div class="form-group">
		<div class="col-sm-1">
			<button type="button" class="btn btn-primary" id="btn-add-associate">Add</button>
		</div>
	</div>
	<div id="associate-ctn">
		<div class="form-group associate-content">
			<div class="col-sm-3">
				<select class="form-control" name="associateTypes">
					<c:forEach items="${associateTypes}" var="associateType">
						<option value="${associateType }">${associateType }</option>
					</c:forEach>
				</select>
			</div>
			<div class="col-sm-3">
				<input type="text" class="form-control" placeholder="object"
					name="associateObjects" />
			</div>
			<div class="col-sm-3">
				<select class="form-control" name="widgets">
					<c:forEach items="${widgets}" var="widget">
						<option value="${widget }">${widget }</option>
					</c:forEach>
				</select>
			</div>
			<div class="col-sm-1">
				<button type="button" class="btn btn-danger btn-delete-associate"
					onclick="deleteAssociate(this)">Delete</button>
			</div>
		</div>
	</div>

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