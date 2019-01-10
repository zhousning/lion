package app.tests;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import app.models.Role;
import app.models.User;
import app.services.RoleService;
import app.services.UsersService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class UsersTest {
	
	@Autowired
	UsersService userService;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	private ResourceBundleMessageSource messageSource;
	
	@Test
	public void testRole() {
		User oldLeader = userService.getUserById(2);
		Set<Role> oldRoles = new HashSet<Role>();
		Role defaultRole = roleService.findByName(messageSource.getMessage("roles.default", null, null));
		oldRoles.add(defaultRole);
		oldLeader.setRoles(oldRoles);
		userService.updateUser(oldLeader);
	}
	
	@Test
	public void selectByEmail() {
		String string = "24430510@qq.com";
		User user = userService.getUserByEmail(string);
		System.out.println(user);
		/*Set<Role> roles = user.getRoles();
		Iterator<Role> iterator = roles.iterator();
		while (iterator.hasNext()) {
			Role role = (Role) iterator.next();
			System.err.println(role.getName());
		}*/
	}
	
	
	@Test
	public void getUsers() {
		List<User> users = userService.getUsers();
		Iterator<User> iterator = users.iterator();
		while (iterator.hasNext()) {
			User user = (User)iterator.next();
			System.err.println(user.getName());
		}
	}
	
	@Test
	public void manyToManyCreate() {
		Set<Role> roles = new HashSet<Role>();
		roles.add(new Role("role1"));
		roles.add(new Role("role2"));
		roles.add(new Role("role3"));
		User user = new User("zn", "15763703199", "341", "481790374");
		user.setRoles(roles);
		userService.createUser(user);
	}
	
	@Test
	public void manyToManyDestroy() {
		userService.deleteById(2);
	}
	
	@Test
	public void save() {
		User user = new User("zn", null, "nnnn");
		userService.createUser(user);
	}
	
}