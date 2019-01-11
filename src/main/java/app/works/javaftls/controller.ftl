package app.controllers;

import java.util.Map;

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

import app.models.${model.name};
import app.services.${model.name}Service;

@Controller
@RequestMapping("/${model.name?uncap_first}s")
public class ${model.name}Controller extends BaseController {

	@Autowired
	${model.name}Service ${model.name?uncap_first}Service;
	
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
		map.put("${model.name?uncap_first}", new ${model.name}());
		return "${model.name?uncap_first}s/new";
	}
	
	@RequestMapping(value="/{id}")
	public String show(@PathVariable("id") Integer id, Map<String, Object> map) {
		map.put("${model.name?uncap_first}", ${model.name?uncap_first}Service.findById(id));
		return "${model.name?uncap_first}s/show";
	}
	
	@RequestMapping("/{id}/edit")
	public String edit(@PathVariable("id") Integer id, Map<String, Object> map) {	
		map.put("${model.name?uncap_first}", ${model.name?uncap_first}Service.findById(id));
		return "${model.name?uncap_first}s/edit";
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public String destroy(@PathVariable("id") Integer id) {
		${model.name?uncap_first}Service.deleteById(id);
		return "redirect:/${model.name?uncap_first}s";
	}
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public String create(@Valid ${model.name} ${model.name?uncap_first}, Errors result, Map<String, Object> map) {
		if(result.getErrorCount() > 0){
			for(FieldError error:result.getFieldErrors()){
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}
			return "/${model.name?uncap_first}s/new";
		}
		${model.name?uncap_first}Service.save(${model.name?uncap_first});
		return "redirect:/${model.name?uncap_first}s/" + ${model.name?uncap_first}.getId().toString();
	}
	
	@RequestMapping(value="", method=RequestMethod.PUT)
	public String update(@Valid ${model.name} ${model.name?uncap_first}, Errors result, Map<String, Object> map) {
		if(result.getErrorCount() > 0){
			for(FieldError error:result.getFieldErrors()){
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}		
			return "/${model.name?uncap_first}s/edit";
		}
		${model.name?uncap_first}Service.update(${model.name?uncap_first});
		return "redirect:/${model.name?uncap_first}s/" + ${model.name?uncap_first}.getId().toString();
	}

}