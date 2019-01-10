package app.controllers;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jws.soap.SOAPBinding.Use;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestScope;

import app.models.ExamPaper;
import app.models.ExamPoint;
import app.models.Question;
import app.models.User;
import app.services.UsersService;

@Controller
@RequestMapping("/home")
public class HomeController extends BaseController {

	@Autowired
	UsersService userService;
	
	@ModelAttribute
	public void getUser(@RequestParam(value="id", required=false) Integer id, Map<String, Object> map) {
		if (id != null) {
			System.out.println(userService.getUserById(id).toString());
			map.put("user", userService.getUserById(id));
		} 
	}
	
	@RequestMapping("")
	public String index(Map<String, Object> map) {
		Subject currentUser = SecurityUtils.getSubject();
		String principal = currentUser.getPrincipal().toString();
		User user = userService.getUserByEmail(principal);
		if (adminRole()) {
			List<Question> questions = questionService.findAll();
			int questionCount = questions !=null ? questions.size() : 0;
			List<ExamPaper> examPapers = examPaperService.findAll();
			int examPaperCount = examPapers != null ? examPapers.size() : 0;
			
			map.put("questionCount", questionCount);
			map.put("examPaperCount", examPaperCount);
		} else {
			Set<Question> questions = user.getQuestions();
			int questionCount = questions !=null ? questions.size() : 0;
			Set<ExamPaper> examPapers = user.getExamPapers();
			int examPaperCount = examPapers != null ? examPapers.size() : 0;
			
			map.put("questionCount", questionCount);
			map.put("examPaperCount", examPaperCount);
		}
		beforeUserEdit(user, map);
		return "home/index";
	}
}
