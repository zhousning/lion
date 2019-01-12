package app.controllers;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jws.soap.SOAPBinding.Use;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestScope;

import app.models.User;
import app.services.UserService;

@Controller
@RequestMapping("/home")
public class HomeController extends BaseController {

	@Autowired
	UserService userService;

	@RequestMapping("")
	public String index(Map<String, Object> map) {
		if (!adminRole()) {
			Subject currentUser = SecurityUtils.getSubject();
			String principal = currentUser.getPrincipal().toString();
			User user = userService.getUserByEmail(principal);
			map.put("user", user);
		}
		return "home/index";
	}
}
