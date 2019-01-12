<form:form action="${model.name?uncap_first}s" method="POST" modelAttribute="${model.name?uncap_first}"
	class="form-horizontal">
	<c:if test="${r'${'}${model.name?uncap_first}.id != null ${r'}'}">
		<form:hidden path="id" />
		<input type="hidden" name="_method" value="PUT" />
	</c:if>
	
	<#list model.attrTypes as type>
	<div class="form-group">
		<label for="${model.attrNames[type_index]}" class="col-sm-2 control-label"><fmt:message key="${model.name?uncap_first}.${model.attrNames[type_index]}"></fmt:message></label>
		<div class="col-sm-10">
			<#if type == "String">
			<form:input type="text" class="form-control" path="${model.attrNames[type_index]}" />
			<#elseif type == "Integer" || type == "Long">
			<form:input type="number" class="form-control" path="${model.attrNames[type_index]}" />
			<#elseif type == "Date">
			<form:input type="date" class="form-control" path="${model.attrNames[type_index]}" />
			</#if>
			<form:errors path="${model.attrNames[type_index]}"></form:errors>
		</div>
	</div>
	</#list>

	<#list model.associateObjects as associate>
	<div class="form-group">
		<label for="${associate?uncap_first}" class="col-sm-2 control-label"><fmt:message
			key="${associate?uncap_first}.name"></fmt:message></label>
		<div class="col-sm-10">
		<c:if test="${r'${'}!empty ${associate?uncap_first}s ${r'}'}">
		<#if model.widgets[associate_index] == "select">
		<form:select class="form-control" path="${associate?uncap_first}.id" items="${associate?uncap_first}s" itemLabel="name" itemValue="id"></form:select>
		<#elseif model.widgets[associate_index] == "radio">
		<form:radiobuttons items="${associate?uncap_first}s" path="${associate?uncap_first}.id" itemLabel="name" itemValue="id"/>
		<#elseif model.widgets[associate_index] == "checkbox">
		<form:checkboxes items="${associate?uncap_first}s" path="${associate?uncap_first}Ids" itemLabel="name" itemValue="id"/>
		</#if>
		</c:if>
		</div>
	</div>
	</#list>
	
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-4">
			<button type="submit" class="btn btn-success"><fmt:message key="btn.save"></fmt:message></button>
		</div>
		<div class="col-sm-offset-2 col-sm-4">
			<a href="${model.name?uncap_first}s" type="button" class="btn btn-warning"><fmt:message key="btn.back"></fmt:message></a>
		</div>
	</div>
</form:form>