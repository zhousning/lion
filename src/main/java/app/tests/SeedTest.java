package app.tests;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import app.models.ExamPoint;
import app.models.Level;
import app.models.Role;
import app.models.Subject;
import app.models.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class SeedTest extends BaseTest{

	@Autowired
	ResourceBundleMessageSource messageSource;
	
/*	String  easy = easy();
	String moderate = moderate();
	String hard = hard();
	String extreme = extreme();*/
	
	String math = "Math";
	String english = "English";
	
	
	@Test
	public void initData() {
		initRole();
		initUser();
		initLevel();
		/*initSubject();
		initExamPoint();*/
	}
	
	private void initUser() {
		String hashAlgorithmName = "MD5";
		Object credentials = "admin@admin.com";
		Object salt = ByteSource.Util.bytes("admin@admin.com");
		int hashIterations = 1024;

		Object password = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
		User admin = new User("Admin", "15763703199", "admin@admin.com", password.toString());
		Role adminRole = roleService.findByName(messageSource.getMessage("roles.admin", null, null));
		//Role teacherRole = roleService.findByName(messageSource.getMessage("roles.default", null, null));
		Set<Role> roles = new HashSet<Role>();
		roles.add(adminRole);
		//roles.add(teacherRole);
		admin.setRoles(roles);
		userService.createUser(admin);
	}
	
	private void initLevel() {
		Level easy = new Level(messageSource.getMessage("level.easy", null, null));
		Level moderate = new Level(messageSource.getMessage("level.moderate", null, null));
		Level hard = new Level(messageSource.getMessage("level.hard", null, null));
		Level extreme = new Level(messageSource.getMessage("level.extreme", null, null));
		
		levelService.save(easy);
		levelService.save(moderate);
		levelService.save(hard);
		levelService.save(extreme);
	}
	
	private void initSubject() {
		Subject math = new Subject(this.math, "math-code-subject");
		Subject english = new Subject(this.english, "english-code-subject");
		
		User user = userService.getUserByEmail("leader@leader.com");
		math.setLeaderId(user.getId());
		
		subjectService.save(math);
		subjectService.save(english);
	}
	
	private void initExamPoint() {
		Subject subject = subjectService.findByName(this.math);
		ExamPoint collection = new ExamPoint("what is collection and it");
		collection.setSubject(subject);
		ExamPoint line = new ExamPoint("line is a good status");
		line.setSubject(subject);
		
		examPointService.save(collection);
		examPointService.save(line);
	}
	
	private void initRole() {
		Role adminRole = new Role(messageSource.getMessage("roles.admin", null, null));
		Role leaderRole = new Role(messageSource.getMessage("roles.leader", null, null));
		Role teacher = new Role(messageSource.getMessage("roles.default", null, null));

		roleService.save(adminRole);
		roleService.save(leaderRole);
		roleService.save(teacher);
	}
	

	public String easy() {
		return messageSource.getMessage("leve.easy", null, null);
	}
	
	public String moderate() {
		return messageSource.getMessage("leve.moderate", null, null);
	}
	
	public String hard() {
		return messageSource.getMessage("leve.hard", null, null);
	}
	
	public String extreme() {
		return messageSource.getMessage("leve.extreme", null, null);
	}
	
}
