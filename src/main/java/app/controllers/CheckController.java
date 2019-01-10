package app.controllers;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import app.models.ExamPaper;
import app.models.Question;
import app.models.Subject;
import app.models.User;
import app.services.ExamPaperService;
import app.services.QuestionService;

@Controller
@RequestMapping("/checks")
public class CheckController extends BaseController {

	@Autowired
	QuestionService questionService;
	@Autowired
	ExamPaperService examPaperService;
	
	@ModelAttribute
	public void getquestion(@RequestParam(value="id", required=false) Integer id, Map<String, Object> map) {
		if (id != null) {
			System.out.println(questionService.findById(id).toString());
			map.put("question", questionService.findById(id));
		}
	}
	
	@RequestMapping("")
	public String index(Map<String, Object> map) {
		User currentUser = currentUser();
		Set<Subject> subjects = currentUser.getSubjects();
		Iterator<Subject> iterator = subjects.iterator();
		Subject mySubject = new Subject();
		while (iterator.hasNext()) {
			Subject subject = (Subject) iterator.next();
			if (subject.getLeaderId() == currentUser.getId()) {
				mySubject = subject;
				break;
			}
		}
		Set<Question> questions = mySubject.getQuestions();
		map.put("questions", questions);
		return "checks/index";
	}
	
	
	@RequestMapping(value="/{id}")
	public String show(@PathVariable("id") Integer id, Map<String, Object> map) {
		map.put("question", questionService.findById(id));
		return "checks/show";
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public String destroy(@PathVariable("id") Integer id) {
		questionService.deleteById(id);
		return "redirect:/checks";
	}
	
	@RequestMapping(value="/{id}/pass")
	public String pass(@PathVariable("id") Integer id, Map<String, Object> map) {
	    Question question = questionService.findById(id);
	    question.setStatus(messageSource.getMessage("question.status.approved", null, null));
	    questionService.update(question);
		map.put("question", question);
		return "checks/show";
	}
	
	@RequestMapping(value="/{id}/reject")
	public String reject(@PathVariable("id") Integer id, Map<String, Object> map) {
		Question question = questionService.findById(id);
	    question.setStatus(messageSource.getMessage("question.status.pending", null, null));
	    questionService.update(question);
		map.put("question", question);
		return "checks/show";
	}
	
	@RequestMapping(value="/examPaper/{id}/pass")
	public String examPaperPass(@PathVariable("id") Integer id) {
		ExamPaper examPaper = examPaperService.findById(id);
		examPaper.setStatus(messageSource.getMessage("examPaper.status.approved", null, null));
		examPaperService.update(examPaper);
		return "redirect:/examPapers/" + id.toString();
	}
	
	@RequestMapping(value="/examPaper/{id}/reject")
	public String examPaperReject(@PathVariable("id") Integer id) {
		ExamPaper examPaper = examPaperService.findById(id);
		examPaper.setStatus(messageSource.getMessage("examPaper.status.pending", null, null));
		examPaperService.update(examPaper);
		return "redirect:/examPapers/" + id.toString();
	}
	
	@RequestMapping(value="/examPaper/{id}/publish")
	public String examPaperPublish(@PathVariable("id") Integer id) {
		ExamPaper examPaper = examPaperService.findById(id);
		examPaper.setStatus(messageSource.getMessage("examPaper.status.publish", null, null));
		examPaperService.update(examPaper);
		return "redirect:/examPapers/" + id.toString();
	}
	

}
