<form:form action="${model.name?uncap_first}s" method="POST" modelAttribute="${model.name?uncap_first}"
	class="form-horizontal">
	<c:if test="${r'${'}${model.name?uncap_first}.id != null ${r'}'}">
		<form:hidden path="id" />
		<input type="hidden" name="_method" value="PUT" />
	</c:if>
	<div class="form-group">
	<#list model.attrTypes as type>
		<#if type == "Set">
		<#else>
		<label for="${model.attrNames[type_index]}" class="col-sm-2 control-label"><fmt:message key="${model.name?uncap_first}.${model.attrNames[type_index]}"></fmt:message></label>
		<div class="col-sm-10">
			<form:input type="text" class="form-control" path="${model.attrNames[type_index]}" />
			<form:errors path="${model.attrNames[type_index]}"></form:errors>
		</div>
		</#if>	
	</#list>
	</div>
	
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-4">
			<button type="submit" class="btn btn-success"><fmt:message key="btn.save"></fmt:message></button>
		</div>
		<div class="col-sm-offset-2 col-sm-4">
			<a href="${model.name?uncap_first}s" type="button" class="btn btn-warning"><fmt:message key="btn.back"></fmt:message></a>
		</div>
	</div>
</form:form>