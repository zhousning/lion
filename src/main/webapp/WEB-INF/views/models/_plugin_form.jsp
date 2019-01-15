<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="form-group">
	<div class="col-sm-1">
		<button type="button" class="btn btn-primary" id="btn-add-plugin">Plugin
			Add</button>
	</div>
</div>
<div id="plugin-ctn">
	<div class="form-group plugin-content">
		<div class="col-sm-3">
			<select class="form-control" name="pluginTypes">
				<c:forEach items="${pluginTypes}" var="pluginType">
					<option value="${pluginType }">${pluginType }</option>
				</c:forEach>
			</select>
		</div>
		<div class="col-sm-3">
			<input type="text" class="form-control" placeholder="condition"
				name="pluginConditions" value="1"/>
		</div>
		<div class="col-sm-1">
			<button type="button" class="btn btn-danger btn-delete-plugin"
				onclick="deletePlugin(this)">Delete</button>
		</div>
	</div>
</div>