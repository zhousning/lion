<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE hibernate-mapping PUBLIC
          "-//Hibernate/Hibernate Mapping DTD 3.0//EN"
          "http://hibernate.sourceforge.net /hibernate-mapping-3.0.dtd">
<hibernate-mapping package="app.models">

	<class name="${model.name}" table="${model.name?uncap_first}s">
		<id name="id">
			<generator class="native" />
		</id>
		<#list model.attrTypes as type>
		<#if type != "Set">
		<property name="${model.attrNames[type_index]}" />
		</#if>
		</#list>
		
		<#list model.associateTypes as associate>
		<#if associate == "one-to-many">
		<set name="${model.associateObjects[associate_index]?uncap_first}s" table="${model.associateObjects[associate_index]?uncap_first}s" inverse="false" lazy="false">
			<key column="${model.name?uncap_first}_id" />
			<one-to-many class="${model.associateObjects[associate_index]?cap_first}" />
		</set>
		<#elseif associate == "many-to-one">
		<many-to-one name="${model.associateObjects[associate_index]?uncap_first}" column="${model.associateObjects[associate_index]?uncap_first}_id" class="${model.associateObjects[associate_index]?cap_first}"
			lazy="false" />
		<#elseif associate == "many-to-many">
		<set name="${model.associateObjects[associate_index]?uncap_first}s" table="${model.name?uncap_first}s_${model.associateObjects[associate_index]?uncap_first}s" lazy="false"
			cascade="save-update">
			<key column="${model.name?uncap_first}_id"></key>
			<many-to-many class="${model.associateObjects[associate_index]?cap_first}" column="${model.associateObjects[associate_index]?uncap_first}_id">
			</many-to-many>
		</set>
		</#if>
		</#list>
	</class>
</hibernate-mapping>