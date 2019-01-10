package app.tests;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import app.models.Role;
import app.models.User;
import app.services.RoleService;
import app.services.UsersService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class RolesTest {

	@Autowired
	RoleService roleService;
	
	@Autowired
	UsersService userService;

	@Autowired
	private ResourceBundleMessageSource messageSource;

	@Test
	public void tools() {
		Role adminRole = new Role(messageSource.getMessage("roles.admin", null, null));
		Role leaderRole = new Role(messageSource.getMessage("roles.leader", null, null));
		Role teacher = new Role(messageSource.getMessage("roles.default", null, null));

		roleService.save(adminRole);
		roleService.save(leaderRole);
		roleService.save(teacher);
	}

	@Test
	public void selectByName() {
		Role role = roleService.findByName(messageSource.getMessage("roles.admin", null, null));
		User user = userService.getUserById(4);
		System.out.println(user.getRoles().contains(role));
	}

	@Test
	public void delete() {
		User newLeader = userService.getUserById(4);
		Role defaultRole = roleService.findByName(messageSource.getMessage("roles.default", null, null));
		Role leaderRole = roleService.findByName(messageSource.getMessage("roles.leader", null, null));
		Set<Role> newRoles = new HashSet<Role>();
		newRoles.add(leaderRole);
		newRoles.add(defaultRole);
		newLeader.setRoles(newRoles);
		userService.updateUser(newLeader);
	}

	@Test
	public void getRole() {
		Role role = roleService.findById(2);
		Set<User> users = role.getUsers();
		Iterator<User> iterator = users.iterator();
		while (iterator.hasNext()) {
			User user = (User) iterator.next();
			System.err.println(user.getEmail());
		}
	}
}