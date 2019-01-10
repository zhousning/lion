package app.tests;


import static org.hamcrest.CoreMatchers.nullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import antlr.build.Tool;
import app.models.Role;
import app.models.Subject;
import app.models.User;
import app.services.SubjectService;
import app.services.UsersService;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class SubjectsTest {
	
	
	@Autowired
	SubjectService service;
	@Autowired
	UsersService userService;
	
	@Test
	public void Tool() {
		Subject subject = service.findById(null);
		System.out.println(subject.getLeaderId() == null);
	}
	
	@Test
	public void prepareData() {
		List<Subject> subjects = service.findAll();		
		User user = userService.getUserById(3);
		Set<Subject> userSubjects = user.getSubjects();
		
		Iterator<Subject> userIterator = userSubjects.iterator();
		
		while (userIterator.hasNext()) {
			Subject userSubject = (Subject) userIterator.next();
			System.out.println(userSubject.getName());
			Iterator<Subject> iterator = subjects.iterator();
			while (iterator.hasNext()) {
				Subject subject = (Subject) iterator.next();
				if (userSubject.getId() == subject.getId()) {
					subjects.remove(subject);
					subjects.add(userSubject);
					iterator = null;
					break;
				}
			}
		}
	}
	
	@Test
	public void create() {
		Subject subject = new Subject("数学");
		Subject subject2 = new Subject("语文");
		Subject subject3 = new Subject("英语");
		Set<Subject> subjects = new HashSet<Subject>();
		subjects.add(subject);
		subjects.add(subject2);
		subjects.add(subject3);
		User user = new User("xuehseng234234", "341323", "134144321");
		user.setSubjects(subjects);
		userService.createUser(user);
	}
	
	@Test
	public void index() {
		List<Subject> subjects = service.findAll();
		System.out.println(subjects);
		Map<Integer, String> leaders = new HashMap<Integer, String>();
		Iterator<Subject> iterator = subjects.iterator();
		while (iterator.hasNext()) {
			Subject subject = (Subject) iterator.next();
			User user = userService.getUserById(subject.getLeaderId());
			String userName = "";
			if (user != null) {
				leaders.put(subject.getLeaderId(), user.getName());
			}
		}
		System.out.println(leaders);
	}
}