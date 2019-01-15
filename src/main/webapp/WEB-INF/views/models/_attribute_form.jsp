<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="form-group">
	<div class="col-sm-1">
		<button type="button" class="btn btn-primary" id="btn-add-attribute">Attribute
			Add</button>
	</div>
</div>
<div id="attribute-ctn">
	<div class="form-group attr-content">
		<div class="col-sm-1">
			<select class="form-control" name="attrTypes">
				<c:forEach items="${attrTypes}" var="type">
					<option value="${type }">${type }</option>
				</c:forEach>
			</select>
		</div>
		<div class="col-sm-1">
			<input list="attLabelDataList" type="text" class="form-control"
				id="name-label" placeholder="label" name="labels" />
			<datalist id="attLabelDataList">
				<c:forEach items="${attLabelDataList }" var="data">
					<option value="${data }">${data }</option>
				</c:forEach>
			</datalist>
		</div>
		<div class="col-sm-1">
			<input list="attrNameList" type="text" class="form-control"
				placeholder="name" name="attrNames" value="name" />
			<datalist id="attrNameList">
				<c:forEach items="${attNameDataList }" var="data">
					<option value="${data }">${data }</option>
				</c:forEach>
			</datalist>
		</div>
		<div class="col-sm-2">
			<select id="js-name-constraint" class="form-control"
				name="constraints">
				<c:forEach items="${constraints}" var="constraint">
					<option value="${constraint }">${constraint }</option>
				</c:forEach>
			</select>
		</div>
		<div class="col-sm-1">
			<select class="form-control" name="attrWidgets">
				<c:forEach items="${attrWidgets}" var="attrWidget">
					<option value="${attrWidget }">${attrWidget }</option>
				</c:forEach>
			</select>
		</div>
	</div>
	<div class="form-group attr-content">
		<div class="col-sm-1">
			<select class="form-control js-attrtype" name="attrTypes">
				<c:forEach items="${attrTypes}" var="type">
					<option value="${type }">${type }</option>
				</c:forEach>
			</select>
		</div>
		<div class="col-sm-1">
			<input list="attLabelDataList" type="text"
				class="form-control js-attrlabel" placeholder="label" name="labels" />
		</div>
		<div class="col-sm-1">
			<input list="attrNameList" type="text" class="form-control"
				placeholder="name" name="attrNames" onchange="setAttrDefault(this)" />
		</div>
		<div class="col-sm-2">
			<select class="form-control js-attr-constraint" name="constraints">
				<c:forEach items="${constraints}" var="constraint">
					<option value="${constraint }">${constraint }</option>
				</c:forEach>
			</select>
		</div>
		<div class="col-sm-1">
			<select class="form-control js-attrwidget" name="attrWidgets">
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