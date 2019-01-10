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
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mysql.fabric.xmlrpc.base.Data;

import antlr.build.Tool;
import app.models.Attachment;
import app.models.ExamPaper;
import app.models.Question;
import app.models.Role;
import app.models.Subject;
import app.models.User;
import app.services.ExamPaperService;
import app.services.QuestionService;
import app.services.SubjectService;
import app.services.UsersService;
import app.works.ExportDoc;
import net.sf.jsqlparser.statement.delete.Delete;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class ExamPaperTest extends BaseTest {

	@Autowired
	QuestionService questionService;
	@Autowired
	UsersService userService;
	@Autowired
	ExamPaperService examPaperService;
	@Autowired
	ResourceBundleMessageSource messageSource;

	@Test
	public void Tool() throws IOException {
	}

	@Test
	public void exportDoc() throws Exception {
		ExportDoc maker = new ExportDoc("UTF-8");
		ExamPaper examPaper = examPaperService.findById(1);
		Set<Question> questions = examPaper.getQuestions();
		List<Object> multiples = new ArrayList<Object>();
		List<Object> essays = new ArrayList<Object>();
		Map<String, List<String>> examPointsMap = new HashMap<String, List<String>>();

		Integer sectAIndex = 0;
		Integer sectBIndex = 0;
		Iterator<Question> iterator = questions.iterator();
		while (iterator.hasNext()) {
			Question question = (Question) iterator.next();
			String tag = "";
			if (question.getType().equals(messageSource.getMessage("questions.multiple.code", null, null))) {
				Map<String, Object> map = new HashMap();
				sectAIndex += 1;
				map.put("index", sectAIndex.toString());
				map.put("question", question);
				multiples.add(map);

				tag = "A" + sectAIndex.toString();
			} else {
				Map<String, Object> map = new HashMap();
				sectBIndex += 1;
				map.put("index", sectBIndex.toString());
				map.put("question", question);
				essays.add(map);

				tag = "B" + sectBIndex.toString();
			}
			String examPoint = question.getExamPoint().getName();
			if (examPointsMap.containsKey(examPoint)) {
				examPointsMap.get(examPoint).add(tag);
			} else {
				List<String> list = new ArrayList<String>();
				list.add(tag);
				examPointsMap.put(examPoint, list);
			}
			
		}
		System.out.println(">>>>>>>>>>");
		System.out.println(examPointsMap);

		Map<String, Object> template = new HashMap<String, Object>();
		template.put("paper", examPaper);
		template.put("subject", examPaper.getSubject());
		template.put("multiples", multiples);
		template.put("essays", essays);
		template.put("examPoints", examPointsMap);

		maker.exportDoc("D:\\test13.doc", template, "template.ftl");
	}

	@Test
	public void create() throws ParseException {
		Attachment attachment = new Attachment("341341");
		Question question = new Question("title2", "content", "answer", "analysis", new Date(), new Date(), "2");
		Set<Attachment> attachments = new HashSet<Attachment>();
		attachments.add(attachment);
		question.setAttachments(attachments);
		/*
		 * User user = userService.getUserById(7); question.setUser(user);
		 */
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
		ExamPaper examPaper = examPaperService.findById(1);
		Subject subject = examPaper.getSubject();
		Set<Question> subjectQuestions = subject.getQuestions();
		List<Question> questions = new ArrayList<Question>();
		Iterator<Question> iterator = subjectQuestions.iterator();
		while (iterator.hasNext()) {
			Question question = (Question) iterator.next();
			System.out.println(
					question.getStatus().equals(messageSource.getMessage("question.status.approved", null, null)));
			if (question.getStatus() == messageSource.getMessage("question.status.approved", null, null)) {
				System.out.println(question.getTitle());
			}
		}
	}
}