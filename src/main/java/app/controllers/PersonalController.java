package app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import app.models.User;

@Controller
@RequestMapping("/personals")
public class PersonalController extends BaseController {
	
	@RequestMapping("/{id}/edit")
	public String edit(@PathVariable("id") Integer id, Map<String, Object> map) {
		User user = userService.findById(id);
		map.put("user", user);
		return "personals/edit";
	}
	
	@RequestMapping(value = "/{id}")
	public String show(@PathVariable("id") Integer id, Map<String, Object> map) {
		map.put("user", userService.findById(id));
		return "personals/show";
	}
	
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public String update(@Valid User user, Errors result, Map<String, Object> map) {	
		if (result.getErrorCount() > 0) {
			for (FieldError error : result.getFieldErrors()) {
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}
			return "/personals/edit";
		}
		
		setPassword(user);
		userService.update(user);	
		updateUserSession(user);

		return "redirect:/personals/" + user.getId().toString();
	}
}
