package app.models;

import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ${model.name} {
	private Integer id;
<#list model.attrTypes as type>
	<#if model.constraints[type_index] != "none">
	${model.constraints[type_index]}
	</#if>
	<#if type == "Set">
	private ${type}<${model.attrNames[type_index]?cap_first}> ${model.attrNames[type_index]?uncap_first}s = new HashSet<${model.attrNames[type_index]?cap_first}>();
	<#else>
	private ${type} ${model.attrNames[type_index]};
	</#if>
	
</#list>

	public ${model.name}() {
		super();
	}
	
	public ${model.name}(
		<#list model.attrTypes as type>
		<#if type == "Set">
		${type}<${model.attrNames[type_index]?cap_first}> ${model.attrNames[type_index]?uncap_first}s${(type_index == model.attrTypes?size-1)?string('',',')}
		<#else>
		${type} ${model.attrNames[type_index]}${(type_index == model.attrTypes?size-1)?string('',',')}
		</#if>	
		</#list>
	) {
		super();
		<#list model.attrTypes as type>
		this.${model.attrNames[type_index]}${(type=="Set")?string('s','')} = ${model.attrNames[type_index]}${(type=="Set")?string('s','')};
		</#list>
	}
	
	<#list model.attrTypes as type>
	<#if type == "Set">
	public ${type}<${model.attrNames[type_index]?cap_first}> get${model.attrNames[type_index]?cap_first}s() {
		return ${model.attrNames[type_index]?uncap_first}s;
	}
	
	public void set${model.attrNames[type_index]?cap_first}s(${type}<${model.attrNames[type_index]?cap_first}> ${model.attrNames[type_index]}) {
		this.${model.attrNames[type_index]?uncap_first}s = ${model.attrNames[type_index]?uncap_first}s;
	}
	<#else>
	public ${type} get${model.attrNames[type_index]?cap_first}() {
		return ${model.attrNames[type_index]};
	}
	
	public void set${model.attrNames[type_index]?cap_first}(${type} ${model.attrNames[type_index]?uncap_first}) {
		this.${model.attrNames[type_index]} = ${model.attrNames[type_index]};
	}
	</#if>
	</#list>
	
	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
	
}