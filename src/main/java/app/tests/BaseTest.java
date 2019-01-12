package app.tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;

import app.services.RoleService;
import app.services.UserService;

public class BaseTest {
	
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;
}
