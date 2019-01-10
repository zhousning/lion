package app.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;

import app.models.Role;
import app.services.RoleService;

public class Seed {
	@Autowired
	RoleService roleService;
	
	@Autowired
	private ResourceBundleMessageSource messageSource;
	
	public static void main(String[] args) {
		new Seed().initRoles();
	}
	
	public void initRoles() {
		Role adminRole = new Role(messageSource.getMessage("roles.admin", null, null));
		Role leaderRole = new Role(messageSource.getMessage("roles.leader", null, null));
		Role teacher = new Role(messageSource.getMessage("roles.default", null, null));
		
		roleService.save(adminRole);
		roleService.save(leaderRole);
		roleService.save(teacher);
	}
}
