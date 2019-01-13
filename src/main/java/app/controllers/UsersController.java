package app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import app.models.User;

@Controller
@RequestMapping("/users")
public class UsersController extends BaseController {

	@ModelAttribute
	public void getUser(@RequestParam(value = "id", required = false) Integer id, Map<String, Object> map) {
		if (id != null) {
			System.out.println(userService.findById(id).toString());
			map.put("user", userService.findById(id));
		}
	}

	@RequestMapping("/index")
	public String index(Map<String, Object> map) {
		map.put("users", userService.findAll());
		return "users/index";
	}

	@RequestMapping(value = "/{id}")
	public String show(@PathVariable("id") Integer id, Map<String, Object> map) {
		map.put("user", userService.findById(id));
		return "users/show";
	}

	@RequestMapping("/{id}/edit")
	public String edit(@PathVariable("id") Integer id, Map<String, Object> map) {
		User user = userService.findById(id);
		map.put("user", user);
		return "users/edit";
	}
	
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public String update(@Valid User user, Errors result, Map<String, Object> map) {	
		if (result.getErrorCount() > 0) {
			for (FieldError error : result.getFieldErrors()) {
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}
			return "/users/edit";
		}
		
		setPassword(user);
		userService.update(user);

		return "redirect:/users/" + user.getId().toString();
	}
	
	@RequestMapping(value = "/{id}/destroy", method = RequestMethod.DELETE)
	public String destroy(@PathVariable("id") Integer id) {
		userService.deleteById(id);
		return "redirect:/users/index";
	}

	@RequestMapping("/new")
	public String fresh(Map<String, Object> map) {
		map.put("user", new User());
		return "users/new";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String create(@Valid User user, Errors result, Map<String, Object> map) {
		if (result.getErrorCount() > 0) {
			for (FieldError error : result.getFieldErrors()) {
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}
			return "users/new";
		}
		String principal = user.getEmail();
		User selectUser = userService.getUserByEmail(principal);
		if (selectUser == null) {
			setPassword(user);
			initRole(user);
			userService.save(user);
			return "redirect:/users/index";
		} else {
			return "users/new";
		}
	}

}
