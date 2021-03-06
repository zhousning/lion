package app.controllers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import app.models.Role;
import app.models.User;

@Controller
@RequestMapping("/seeds")
public class SeedController  extends BaseController  {	
	@RequestMapping("/initdata")
	@ResponseBody
	public Map<String, String> initData() {
		initRole();
		initUser();
		Map<String, String> status = new HashMap<String, String>();
		status.put("status", "success");
		return status;
	}
	
	private void initUser() {
		String hashAlgorithmName = "MD5";
		Object credentials = "admin@admin.com";
		Object salt = ByteSource.Util.bytes("admin@admin.com");
		int hashIterations = 1024;

		Object password = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
		User admin = new User("Admin", "1579999999", "admin@admin.com", password.toString());
		Role adminRole = roleService.findByName(messageSource.getMessage("roles.admin", null, null));
		//Role teacherRole = roleService.findByName(messageSource.getMessage("roles.default", null, null));
		Set<Role> roles = new HashSet<Role>();
		roles.add(adminRole);
		//roles.add(teacherRole);
		admin.setRoles(roles);
		userService.save(admin);
	}
	
	private void initRole() {
		Role adminRole = new Role(messageSource.getMessage("roles.admin", null, null));
		Role leaderRole = new Role(messageSource.getMessage("roles.leader", null, null));
		Role teacher = new Role(messageSource.getMessage("roles.default", null, null));

		roleService.save(adminRole);
		roleService.save(leaderRole);
		roleService.save(teacher);
	}

}
