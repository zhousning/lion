package app.controllers;

import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Map;
import java.util.Iterator;

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

import app.models.Permission;
import app.models.Role;
import app.services.PermissionService;
import app.services.RoleService;

@Controller
@RequestMapping("/permissions")
public class PermissionController extends BaseController {

	@Autowired
	PermissionService permissionService;
	@Autowired
	RoleService roleService;
	
	@ModelAttribute
	public void getpermission(@RequestParam(value="id", required=false) Integer id, Map<String, Object> map) {
		if (id != null) {
			map.put("permission", permissionService.findById(id));
		}
	}
	
	@RequestMapping("/index")
	public String index(Map<String, Object> map) {
		map.put("permissions", permissionService.findAll());
		return "permissions/index";
	}
	
	@RequestMapping("/new")
	public String fresh(Map<String, Object> map) {
		prepareData(map, null);
		map.put("permission", new Permission());
		return "permissions/new";
	}
		
	@RequestMapping("/{id}/edit")
	public String edit(@PathVariable("id") Integer id, Map<String, Object> map) {
		Permission permission = permissionService.findById(id);
		prepareData(map, permission);
		map.put("permission", permission);
		return "permissions/edit";
	}
	
	@RequestMapping(value="/{id}")
	public String show(@PathVariable("id") Integer id, Map<String, Object> map) {
		map.put("permission", permissionService.findById(id));
		return "permissions/show";
	}

	@RequestMapping(value="/{id}/destroy", method=RequestMethod.DELETE)
	public String destroy(@PathVariable("id") Integer id) {
		permissionService.deleteById(id);
		return "redirect:/permissions/index";
	}
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public String create(@Valid Permission permission, Errors result, Map<String, Object> map
	) {
		if(result.getErrorCount() > 0){
			for(FieldError error:result.getFieldErrors()){
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}
			prepareData(map, null);
			return "/permissions/new";
		}
		
		setAssociate(permission
		);
			
		permissionService.save(permission);
		return "redirect:/permissions/" + permission.getId().toString();
	}
	
	@RequestMapping(value="", method=RequestMethod.PUT)
	public String update(@Valid Permission permission, Errors result, Map<String, Object> map
		, @RequestParam(value = "RoleIds", required = false) Integer[] roleIds
	) {
		if(result.getErrorCount() > 0){
			for(FieldError error:result.getFieldErrors()){
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}
			prepareData(map, permission);
			return "/permissions/edit";
		}
		
		setAssociate(permission
		);
		
		permissionService.update(permission);
		return "redirect:/permissions/" + permission.getId().toString();
	}
		
	
	private void prepareData(Map<String, Object> map, Permission permission) {
	}
	
	public void setAssociate(Permission permission
	) {
	}
	

}