<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="form-group">
	<div class="col-sm-1">
		<button type="button" class="btn btn-primary" id="btn-add-associate">Associate
			Add</button>
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