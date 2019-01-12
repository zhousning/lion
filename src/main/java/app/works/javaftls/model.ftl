package app.models;

import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

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
	private ${type} ${model.attrNames[type_index]};
	</#list>
	
	<#list model.associateTypes as associate>
	<#if associate == "one-to-many" || associate == "many-to-many">
	private Set<${model.associateObjects[associate_index]?cap_first}> ${model.associateObjects[associate_index]?uncap_first}s = new HashSet<${model.associateObjects[associate_index]?cap_first}>();
	private List<Integer> ${model.associateObjects[associate_index]?uncap_first}Ids = new ArrayList<Integer>();
	<#elseif associate == "many-to-one">
	private ${model.associateObjects[associate_index]?cap_first} ${model.associateObjects[associate_index]?uncap_first};	
	</#if>
	</#list>

	public ${model.name}() {
		super();
	}
	
	public ${model.name}(
		<#list model.attrTypes as type>
		${type} ${model.attrNames[type_index]}${(type_index == model.attrTypes?size-1)?string('',',')}
		</#list>
	) {
		super();
		<#list model.attrTypes as type>
		this.${model.attrNames[type_index]} = ${model.attrNames[type_index]};
		</#list>
	}
	
	<#list model.attrTypes as type>
	public ${type} get${model.attrNames[type_index]?cap_first}() {
		return ${model.attrNames[type_index]};
	}
	
	public void set${model.attrNames[type_index]?cap_first}(${type} ${model.attrNames[type_index]?uncap_first}) {
		this.${model.attrNames[type_index]} = ${model.attrNames[type_index]};
	}
	</#list>
	
	<#list model.associateTypes as associate>
	<#if associate == "one-to-many" || associate == "many-to-many">
	public Set<${model.associateObjects[associate_index]?cap_first}> get${model.associateObjects[associate_index]?cap_first}s() {
		return ${model.associateObjects[associate_index]?uncap_first}s;
	}
	
	public void set${model.associateObjects[associate_index]?cap_first}s(Set<${model.associateObjects[associate_index]?cap_first}> ${model.associateObjects[associate_index]?uncap_first}s) {
		this.${model.associateObjects[associate_index]?uncap_first}s = ${model.associateObjects[associate_index]?uncap_first}s;
	}
	
	public List<Integer> get${model.associateObjects[associate_index]?cap_first}Ids() {
		return ${model.associateObjects[associate_index]?uncap_first}Ids;
	}
	
	public void set${model.associateObjects[associate_index]?cap_first}Ids(List<Integer> ${model.associateObjects[associate_index]?uncap_first}Ids) {
		this.${model.associateObjects[associate_index]?uncap_first}Ids = ${model.associateObjects[associate_index]?uncap_first}Ids;
	}
	<#elseif associate == "many-to-one">
	public ${model.associateObjects[associate_index]?cap_first} get${model.associateObjects[associate_index]?cap_first}() {
		return ${model.associateObjects[associate_index]?uncap_first};
	}
	
	public void set${model.associateObjects[associate_index]?cap_first}(${model.associateObjects[associate_index]?cap_first} ${model.associateObjects[associate_index]?uncap_first}) {
		this.${model.associateObjects[associate_index]?uncap_first} = ${model.associateObjects[associate_index]?uncap_first};
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