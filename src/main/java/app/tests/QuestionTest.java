package app.tests;


import static org.hamcrest.CoreMatchers.nullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.aspectj.weaver.ast.Var;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mysql.fabric.xmlrpc.base.Data;

import antlr.build.Tool;
import app.models.Attachment;
import app.models.Question;
import app.models.Role;
import app.models.Subject;
import app.models.User;
import app.services.QuestionService;
import app.services.SubjectService;
import app.services.UsersService;
import app.works.SemblanceWork;
import net.sf.jsqlparser.statement.delete.Delete;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class QuestionTest {
	
	
	@Autowired
	QuestionService questionService;
	@Autowired
	UsersService userService;
	@Autowired
	SubjectService subjectService;
	
	@Test
	public void selectByConditions() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		int limit = 2;
		Date start = null;
		Date end = java.sql.Date.valueOf("2018-12-20");
		/*List<Question> questions = questionService.selectByConditions("1", 1, start, end, limit);
		Iterator<Question> iterator = questions.iterator();
		while (iterator.hasNext()) {
			Question question = (Question) iterator.next();
			System.out.println(question.getTitle());
		}*/
	}
	@Test
	public void semblance() throws UnsupportedEncodingException {
		Subject subject = subjectService.findById(5);
		Set<Question> questions = subject.getQuestions();
		String text1 = "this is a dog";
		float result = 0;
		Iterator<Question> iterator = questions.iterator();
		while (iterator.hasNext()) {
			Question question = (Question) iterator.next();
			String text2 = question.getTitle();
			float semblance = SemblanceWork.semblance(text1, text2);
			if (semblance > result) {
				result = semblance;
			}
		}
		System.out.println(result);
	}
	

	@Test
	public void Tool() throws IOException {    
       String string = "test.jpg";
       String string2 = string.replaceAll("[^\\.]+\\.", "");
       System.out.println(string2);
	}
	

	@Test
	public void create() throws ParseException {
		Attachment attachment = new Attachment("341341");
		Question question = new Question("title2", "content", "answer", "analysis", new Date(), new Date(), "2");
		Set<Attachment> attachments = new HashSet<Attachment>();
		attachments.add(attachment);
		question.setAttachments(attachments);
/*		User user = userService.getUserById(7);
		question.setUser(user);*/
		questionService.save(question);
	}
	
	@Test
	public void Delete() {
		Question question = questionService.findById(9);
		questionService.delete(question);
	}
	
	@Test
	public void show() {
		User user = userService.getUserById(2);
		Set<Question> questions = user.getQuestions();
		Iterator<Question> iterator = questions.iterator();
		while (iterator.hasNext()) {
			Question question = (Question) iterator.next();
			System.out.println(question.getTitle());
		}
	}
	
	@Test
	public void index() {
		Integer[] ids = {1, 4, 7, 10};
		List<Question> questions = questionService.findByIds(ids);
		Iterator<Question> iterator = questions.iterator();
		while (iterator.hasNext()) {
			Question question = (Question) iterator.next();
			System.out.println(question.getTitle());
		}
	}
}