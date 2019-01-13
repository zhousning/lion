package app.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import app.services.RoleService;

@Controller
@RequestMapping("/roles")
public class RolesController extends BaseController {

	@ModelAttribute
	public void getrole(@RequestParam(value="id", required=false) Integer id, Map<String, Object> map) {
		if (id != null) {
			map.put("role", roleService.findById(id));
		}
	}
	
	@RequestMapping("/index")
	public String index(Map<String, Object> map) {
		map.put("roles", roleService.findAll());
		return "roles/index";
	}
	
	@RequestMapping("/new")
	public String fresh(Map<String, Object> map) {
		Role role = new Role();
		preparePermissions(role, map);
		map.put("role", role);
		return "roles/new";
	}
	
	@RequestMapping(value="/{id}")
	public String show(@PathVariable("id") Integer id, Map<String, Object> map) {
		map.put("role", roleService.findById(id));
		return "roles/show";
	}
	
	@RequestMapping("/{id}/edit")
	public String edit(@PathVariable("id") Integer id, Map<String, Object> map) {
		Role role = roleService.findById(id);
		preparePermissions(role, map);
		map.put("role", role);
		return "roles/edit";
	}

	@RequestMapping(value="/{id}/destroy", method=RequestMethod.DELETE)
	public String destroy(@PathVariable("id") Integer id) {
		roleService.deleteById(id);
		return "redirect:/roles/index";
	}
	

	@RequestMapping(value="", method=RequestMethod.POST)
	public String create(@Valid Role role, Errors result, @RequestParam(value = "permissionIds", required = false) Integer[] permissionIds, Map<String, Object> map) {
		if(result.getErrorCount() > 0){
			for(FieldError error:result.getFieldErrors()){
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}
			preparePermissions(role, map);
			return "/roles/new";
		}
		List<Permission> permissionData = new ArrayList<Permission>();
		if (permissionIds != null) {
			List<Permission> permissions = permissionService.findByIds(permissionIds);
			permissionData = permissions;
		}
		role.setPermissions(new HashSet(permissionData));
		roleService.save(role);
		return "redirect:/roles/" + role.getId().toString();
	}
	
	@RequestMapping(value="", method=RequestMethod.PUT)
	public String update(@Valid Role role, Errors result, @RequestParam(value = "permissionIds", required = false) Integer[] permissionIds, Map<String, Object> map) {
		if(result.getErrorCount() > 0){
			for(FieldError error:result.getFieldErrors()){
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}
			preparePermissions(role, map);
			return "/roles/edit";
		}
		List<Permission> permissionData = new ArrayList<Permission>();
		if (permissionIds != null) {
			List<Permission> permissions = permissionService.findByIds(permissionIds);
			permissionData = permissions;
		}
		role.setPermissions(new HashSet(permissionData));
		roleService.update(role);
		return "redirect:/roles/" + role.getId().toString();
	}
	
	public void preparePermissions(Role role, Map<String, Object> map) {
		map.put("permissions", permissionService.findAll());
		List<Integer> permissionIds = new ArrayList<Integer>();
		Set<Permission> permissions = role.getPermissions();
		Iterator<Permission> iterator = permissions.iterator();
		while (iterator.hasNext()) {
			Permission permission = iterator.next();
			permissionIds.add(permission.getId());
		}
		role.setPermissionIds(permissionIds);
	}

}
