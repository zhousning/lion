package app.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestScope;

import app.models.Role;
import app.models.Subject;
import app.models.User;
import app.services.RoleService;
import app.services.SubjectService;
import app.services.UsersService;
import javassist.expr.NewArray;

@Controller
@RequestMapping("/users")
public class UsersController extends BaseController {

	@ModelAttribute
	public void getUser(@RequestParam(value = "id", required = false) Integer id, Map<String, Object> map) {
		if (id != null) {
			System.out.println(userService.getUserById(id).toString());
			map.put("user", userService.getUserById(id));
		}
	}

	@RequestMapping("")
	public String index(Map<String, Object> map) {
		map.put("users", userService.getUsers());
		return "users/index";
	}

	@RequestMapping(value = "/{id}")
	public String show(@PathVariable("id") Integer id, Map<String, Object> map) {
		map.put("user", userService.getUserById(id));
		return "users/show";
	}

	@RequestMapping("/{id}/edit")
	public String edit(@PathVariable("id") Integer id, Map<String, Object> map) {
		User user = userService.getUserById(id);
		beforeUserEdit(user, map);
		return "users/edit";
	}
	

	private Set<Subject> getSubjectsFromIds(Integer[] subjectIds) {
		Set<Subject> subjects = new HashSet<Subject>();
		if (subjectIds != null) {
			for (int i = 0; i < subjectIds.length; i++) {
				Subject subject = subjectService.findById(subjectIds[i]);
				subjects.add(subject);
			}
		}
		return subjects;
	}
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public String update(@Valid User user,  Errors result, @RequestParam(value = "subjectIds", required = false) Integer[] subjectIds, Map<String, Object> map) {
		Set<Subject> subjects = this.getSubjectsFromIds(subjectIds);	
		user.setSubjects(subjects);
		if (result.getErrorCount() > 0) {
			for (FieldError error : result.getFieldErrors()) {
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}
			beforeUserEdit(user, map);
			return "/users/edit";
		}
		
		boolean isAdmin = adminRole();
		
		User baseUser = userService.getUserById(user.getId());
		if (!baseUser.getPassword().equals(user.getPassword())) {
			String principal = user.getEmail();
			String hashAlgorithmName = "MD5";
			Object credentials = user.getPassword();
			Object salt = ByteSource.Util.bytes(principal);
			int hashIterations = 1024;

			Object password = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
			user.setPassword(password.toString());
		}
		
		userService.updateUser(user);
		
		if (!isAdmin && !(baseUser.getEmail().equals(user.getEmail()))) {
			org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
			PrincipalCollection principalCollection = subject.getPrincipals();
			String realmName = principalCollection.getRealmNames().iterator().next();
			PrincipalCollection newPrincipalCollection = new SimplePrincipalCollection(user.getEmail(), realmName);
			subject.runAs(newPrincipalCollection);
		}

		return "redirect:/users/" + user.getId().toString();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String destroy(@PathVariable("id") Integer id) {
		User user = userService.getUserById(id);
		Set<Subject> subjects = user.getSubjects();
		Iterator<Subject> iterator = subjects.iterator();
		while (iterator.hasNext()) {
			Subject subject = (Subject) iterator.next();
			if (subject.getLeaderId() == user.getId()) {
				subject.setLeaderId(null);
				subjectService.update(subject);
				break;
			}
		}
		userService.deleteById(id);
		return "redirect:/users";
	}


	@RequestMapping("/new")
	public String fresh(Map<String, Object> map) {
		this.beforeNew(map);
		map.put("user", new User());
		return "users/new";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String create(@Valid User user, Errors result,  @RequestParam(value = "subjectIds", required = false) Integer[] subjectIds,  Map<String, Object> map) {
		if (result.getErrorCount() > 0) {
			for (FieldError error : result.getFieldErrors()) {
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}
			// 若验证出错, 则转向定制的页面
			this.beforeNew(map);
			return "users/new";
		}
		// 使用邮箱注册，如果改成其他需要修改以下两行内容
		String principal = user.getEmail();
		User selectUser = userService.getUserByEmail(principal);
		if (selectUser == null) {
			String hashAlgorithmName = "MD5";
			Object credentials = user.getPassword();
			Object salt = ByteSource.Util.bytes(principal);
			int hashIterations = 1024;

			Object password = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
			user.setPassword(password.toString());
			
			Set<Subject> subjects = this.getSubjectsFromIds(subjectIds);
			user.setSubjects(subjects);
			
			initRole(user);
			
			User userObject = userService.insert(user);
			return "redirect:/users";
		} else {
			this.beforeNew(map);
			return "users/new";
		}
	}
	
	private void beforeNew(Map<String, Object> map) {
		List<Subject> subjects = subjectService.findAll();
		map.put("subjects", subjects);
	}

}
