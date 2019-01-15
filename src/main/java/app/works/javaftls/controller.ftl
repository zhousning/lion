package app.controllers;

import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Map;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import app.models.ImageAttachment;
import app.services.ImageAttachmentService;

import app.models.${model.name};
<#list model.associateTypes as associate>
import app.models.${model.associateObjects[associate_index]?cap_first};
</#list>
import app.services.${model.name}Service;
<#list model.associateTypes as associate>
import app.services.${model.associateObjects[associate_index]?cap_first}Service;
</#list>

 <#if (model.pluginTypes?size>0) >
import app.works.UtilTool;
 </#if> 

@Controller
@RequestMapping("/${model.name?uncap_first}s")
public class ${model.name}Controller extends BaseController {

	@Autowired
	${model.name}Service ${model.name?uncap_first}Service;
	<#list model.associateTypes as associate>
	@Autowired
	${model.associateObjects[associate_index]?cap_first}Service ${model.associateObjects[associate_index]?uncap_first}Service;
	</#list>
	
	<#list model.pluginTypes as plugin>
	@Autowired
	${plugin?cap_first}AttachmentService ${plugin?uncap_first}AttachmentService;
	</#list>
	
	@ModelAttribute
	public void get${model.name?uncap_first}(@RequestParam(value="id", required=false) Integer id, Map<String, Object> map) {
		if (id != null) {
			map.put("${model.name?uncap_first}", ${model.name?uncap_first}Service.findById(id));
		}
	}
	
	@RequestMapping("/index")
	public String index(Map<String, Object> map) {
		map.put("${model.name?uncap_first}s", ${model.name?uncap_first}Service.findAll());
		return "${model.name?uncap_first}s/index";
	}
	
	@RequestMapping("/new")
	public String fresh(Map<String, Object> map) {
		<#if (model.associateObjects?size > 0) >
		prepareData(map, null);
		</#if>
		map.put("${model.name?uncap_first}", new ${model.name}());
		return "${model.name?uncap_first}s/new";
	}
		
	@RequestMapping("/{id}/edit")
	public String edit(@PathVariable("id") Integer id, Map<String, Object> map) {
		${model.name?cap_first} ${model.name?uncap_first} = ${model.name?uncap_first}Service.findById(id);
		<#if (model.associateObjects?size > 0)>
		prepareData(map, ${model.name?uncap_first});
		</#if>
		map.put("${model.name?uncap_first}", ${model.name?uncap_first});
		return "${model.name?uncap_first}s/edit";
	}
	
	@RequestMapping(value="/{id}")
	public String show(@PathVariable("id") Integer id, Map<String, Object> map) {
		map.put("${model.name?uncap_first}", ${model.name?uncap_first}Service.findById(id));
		return "${model.name?uncap_first}s/show";
	}

	@RequestMapping(value="/{id}/destroy", method=RequestMethod.DELETE)
	public String destroy(@PathVariable("id") Integer id) {
		${model.name?uncap_first}Service.deleteById(id);
		return "redirect:/${model.name?uncap_first}s/index";
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String create(@Valid ${model.name} ${model.name?uncap_first}, Errors result, Map<String, Object> map
		<#list model.associateTypes as associate>
		<#if (model.widgets[associate_index] != "none") && (associate == "one-to-many" || associate == "many-to-many")>
		, @RequestParam(value = "${model.associateObjects[associate_index]?uncap_first}Ids", required = false) Integer[] ${model.associateObjects[associate_index]?uncap_first}Ids
		<#elseif model.widgets[associate_index] != "none" && associate == "many-to-one">
		, @RequestParam(value="${model.associateObjects[associate_index]?uncap_first}.id", required=false) Integer ${model.associateObjects[associate_index]?uncap_first}Id
		</#if>
		</#list>
		<#list model.pluginTypes as plugin>
		<#if plugin == "image">
		, @RequestParam("${plugin}files") MultipartFile[] ${plugin}files
		</#if>
		</#list>
		, HttpServletRequest request, HttpServletResponse response
	) {
		if(result.getErrorCount() > 0){
			for(FieldError error:result.getFieldErrors()){
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}
			<#if (model.associateObjects?size > 0)>
			prepareData(map, null);
			</#if>
			return "/${model.name?uncap_first}s/new";
		}
		
		<#if (model.associateObjects?size > 0)>
		setAssociate(${model.name?uncap_first}
		<#list model.associateTypes as associate>
		<#if (model.widgets[associate_index] != "none") && (associate == "one-to-many" || associate == "many-to-many")>
		, ${model.associateObjects[associate_index]?uncap_first}Ids
		<#elseif model.widgets[associate_index] != "none" && associate == "many-to-one">
		, ${model.associateObjects[associate_index]?uncap_first}Id
		</#if>
		</#list>
		);
		</#if>
		
		<#list model.pluginTypes as plugin>
		<#if plugin == "image">
		if(${plugin}files!=null&&${plugin}files.length>0){
		    Set<ImageAttachment> imageAttachments = new HashSet<ImageAttachment>();
            for(int i = 0;i<${plugin}files.length;i++){  
                MultipartFile file = ${plugin}files[i];  
                if (!file.isEmpty()) {
                	try {
                		String url = UtilTool.uploadFile(file, request, response);
            			imageAttachments.add(new ImageAttachment(url));
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
            }
            ${model.name?uncap_first}.setImageAttachments(imageAttachments);  
        }  
		</#if>
		</#list>
			
		${model.name?uncap_first}Service.save(${model.name?uncap_first});
		return "redirect:/${model.name?uncap_first}s/" + ${model.name?uncap_first}.getId().toString();
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(@Valid ${model.name} ${model.name?uncap_first}, Errors result, Map<String, Object> map
		<#list model.associateTypes as associate>
		<#if model.widgets[associate_index] != "none" && associate == "one-to-many" || associate == "many-to-many">
		, @RequestParam(value = "${model.associateObjects[associate_index]?uncap_first}Ids", required = false) Integer[] ${model.associateObjects[associate_index]?uncap_first}Ids
		<#elseif model.widgets[associate_index] != "none" && associate == "many-to-one">
		, @RequestParam(value="${model.associateObjects[associate_index]?uncap_first}.id", required=false) Integer ${model.associateObjects[associate_index]?uncap_first}Id
		</#if>
		</#list>
		<#list model.pluginTypes as plugin>
		<#if plugin == "image">
		, @RequestParam("${plugin}files") MultipartFile[] ${plugin}files
		, @RequestParam("hiddenImageIds") Integer[] hiddenImageIds
		</#if>
		</#list>
		, HttpServletRequest request, HttpServletResponse response
	) {
		if(result.getErrorCount() > 0){
			for(FieldError error:result.getFieldErrors()){
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}
			<#if (model.associateObjects?size > 0)>
			prepareData(map, ${model.name?uncap_first});
			</#if>
			return "/${model.name?uncap_first}s/edit";
		}
		
		<#if (model.associateObjects?size > 0)>
		setAssociate(${model.name?uncap_first}
		<#list model.associateTypes as associate>
		<#if (model.widgets[associate_index] != "none") && (associate == "one-to-many" || associate == "many-to-many")>
		, ${model.associateObjects[associate_index]?uncap_first}Ids
		<#elseif model.widgets[associate_index] != "none" && associate == "many-to-one">
		, ${model.associateObjects[associate_index]?uncap_first}Id
		</#if>
		</#list>
		);
		</#if>
		
		<#list model.pluginTypes as plugin>
		<#if plugin == "image">
		List<ImageAttachment> imageAttachments = new ArrayList<ImageAttachment>();
		if (hiddenImageIds != null) {
			imageAttachments = imageAttachmentService.findByIds(hiddenImageIds);
		}
		Set<ImageAttachment> images = new HashSet<ImageAttachment>(imageAttachments);
		if(${plugin}files!=null&&${plugin}files.length>0){  
            for(int i = 0;i<${plugin}files.length;i++){  
                MultipartFile file = ${plugin}files[i];  
                if (!file.isEmpty()) {
                	try {
                		String url = UtilTool.uploadFile(file, request, response);
            			images.add(new ImageAttachment(url));
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
            }
            ${model.name?uncap_first}.setImageAttachments(images);
        }  
		</#if>
		</#list>
		
		${model.name?uncap_first}Service.update(${model.name?uncap_first});
		return "redirect:/${model.name?uncap_first}s/" + ${model.name?uncap_first}.getId().toString();
	}
		
	<#list model.associateTypes as associate>
	<#if model.widgets[associate_index] != "none" && ( associate == "one-to-many" || associate == "many-to-many" )>
	private void prepare${model.associateObjects[associate_index]?cap_first}Ids(${model.name?cap_first} ${model.name?uncap_first}, Map<String, Object> map) {
		List<Integer> ${model.associateObjects[associate_index]?uncap_first}Ids = new ArrayList<Integer>();
		Set<${model.associateObjects[associate_index]?cap_first}> ${model.associateObjects[associate_index]?uncap_first}s = ${model.name?uncap_first}.get${model.associateObjects[associate_index]?cap_first}s();
		Iterator<${model.associateObjects[associate_index]?cap_first}> iterator = ${model.associateObjects[associate_index]?uncap_first}s.iterator();
		while (iterator.hasNext()) {
			${model.associateObjects[associate_index]?cap_first} ${model.associateObjects[associate_index]?uncap_first} = (${model.associateObjects[associate_index]?cap_first}) iterator.next();
			${model.associateObjects[associate_index]?uncap_first}Ids.add(${model.associateObjects[associate_index]?uncap_first}.getId());
		}
		${model.name?uncap_first}.set${model.associateObjects[associate_index]?cap_first}Ids(${model.associateObjects[associate_index]?uncap_first}Ids);
	}
	</#if>
	</#list>
	
	private void prepareData(Map<String, Object> map, ${model.name?cap_first} ${model.name?uncap_first}) {
		<#list model.associateObjects as associate>
		<#if model.widgets[associate_index] != "none">
		map.put("${associate?uncap_first}s", ${associate?uncap_first}Service.findAll());
		</#if>
		</#list>
		<#list model.associateTypes as type>
		<#if (model.widgets[type_index] != "none") && ( type == "one-to-many" || type == "many-to-many" )>
		if (${model.name?uncap_first} != null) {
			prepare${model.associateObjects[type_index]?cap_first}Ids(${model.name?uncap_first}, map);
		}
		</#if>
		</#list>
	}
	
	<#if (model.associateObjects?size > 0)>
	public void setAssociate(${model.name?cap_first} ${model.name?uncap_first}
		<#list model.associateTypes as associate>
		<#if model.widgets[associate_index] != "none" && ( associate == "one-to-many" || associate == "many-to-many" )>
		, Integer[] ${model.associateObjects[associate_index]?uncap_first}Ids
		<#elseif model.widgets[associate_index] != "none" && associate == "many-to-one">
		, Integer ${model.associateObjects[associate_index]?uncap_first}Id
		</#if>
		</#list>
	) {
		<#list model.associateTypes as associate>
		<#if model.widgets[associate_index] != "none" && ( associate == "one-to-many" || associate == "many-to-many" )>
		List<${model.associateObjects[associate_index]?cap_first}> ${model.associateObjects[associate_index]?uncap_first}Data = new ArrayList<${model.associateObjects[associate_index]?cap_first}>();
		if (${model.associateObjects[associate_index]?uncap_first}Ids != null) {
			List<${model.associateObjects[associate_index]?cap_first}> ${model.associateObjects[associate_index]?uncap_first}s = ${model.associateObjects[associate_index]?uncap_first}Service.findByIds(${model.associateObjects[associate_index]?uncap_first}Ids);
			${model.associateObjects[associate_index]?uncap_first}Data = ${model.associateObjects[associate_index]?uncap_first}s;
		}
		${model.name?uncap_first}.set${model.associateObjects[associate_index]?cap_first}s(new HashSet<${model.associateObjects[associate_index]?cap_first}>(${model.associateObjects[associate_index]?uncap_first}Data));
		<#elseif model.widgets[associate_index] != "none" && associate == "many-to-one">
		if (${model.associateObjects[associate_index]?uncap_first}Id != null) {
			${model.associateObjects[associate_index]?cap_first} ${model.associateObjects[associate_index]?uncap_first} = ${model.associateObjects[associate_index]?uncap_first}Service.findById(${model.associateObjects[associate_index]?uncap_first}Id);
			${model.name?uncap_first}.set${model.associateObjects[associate_index]?cap_first}(${model.associateObjects[associate_index]?uncap_first});
		}
		</#if>
		</#list>
	}
	</#if>
	

}