<form:form action="${model.name?uncap_first}s" method="POST" modelAttribute="${model.name?uncap_first}"
	class="form-horizontal">
	<c:if test="${r'${'}${model.name?uncap_first}.id != null ${r'}'}">
		<form:hidden path="id" />
		<input type="hidden" name="_method" value="PUT" />
	</c:if>
	
	<#list model.attrTypes as type>
	<#if model.attrWidgets[type_index] != "none">
	<div class="form-group">
		<label for="${model.attrNames[type_index]}" class="col-sm-2 control-label"><fmt:message key="${model.name?uncap_first}.${model.attrNames[type_index]}"></fmt:message></label>
		<div class="col-sm-10">
			<form:input  type="${model.attrWidgets[type_index]}" class="form-control" path="${model.attrNames[type_index]}" <#if model.attrWidgets[type_index]=="textarea">rows="10" cols="20"</#if> />
			<form:errors path="${model.attrNames[type_index]}"></form:errors>
		</div>
	</div>
	</#if>
	</#list>

	<#list model.associateObjects as associate>
	<#if (model.widgets[associate_index] != "none")>
	<div class="form-group">
		<label for="${associate?uncap_first}" class="col-sm-2 control-label"><fmt:message
			key="${model.title}"></fmt:message></label>
		<div class="col-sm-10 <#if model.widgets[associate_index] == 'radio'>radio<#elseif model.widgets[associate_index] == 'checkbox'>checkbox</#if>">
		<c:if test="${r'${'}!empty ${associate?uncap_first}s ${r'}'}">
		<#if model.widgets[associate_index] == "select">
		<form:select class="form-control" path="${associate?uncap_first}.id" items="${r'${'}${associate?uncap_first}s${r'}'}" itemLabel="name" itemValue="id"></form:select>
		<#elseif model.widgets[associate_index] == "radio">
		<form:radiobuttons path="${associate?uncap_first}.id" items="${r'${'}${associate?uncap_first}s${r'}'}" itemLabel="name" itemValue="id"  element="label class='associate-widget-style'"/>
		<#elseif model.widgets[associate_index] == "checkbox">
		<form:checkboxes path="${associate?uncap_first}Ids" items="${r'${'}${associate?uncap_first}s${r'}'}" itemLabel="name" itemValue="id"  element="label class='associate-widget-style'"/>
		</#if>
		</c:if>
		</div>
	</div>
	</#if>
	</#list>
	
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-1">
			<button type="submit" class="btn btn-success"><fmt:message key="btn.save"></fmt:message></button>
		</div>
		<div class="col-sm-offset-1 col-sm-1">
			<a href="${model.name?uncap_first}s/index" type="button" class="btn btn-warning"><fmt:message key="btn.back"></fmt:message></a>
		</div>
	</div>
</form:form>