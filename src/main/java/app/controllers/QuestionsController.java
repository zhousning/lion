package app.controllers;


import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import app.models.Attachment;
import app.models.ExamPoint;
import app.models.Level;
import app.models.Question;
import app.models.Subject;
import app.services.ExamPointService;
import app.services.LevelService;
import app.services.QuestionService;
import app.works.SemblanceWork;

@Controller
@RequestMapping("/questions")
public class QuestionsController extends BaseController {

	@Autowired
	QuestionService questionService;

	@Autowired
	LevelService levelService;
	
	@Autowired
	ExamPointService examPointService;

	@ModelAttribute
	public void getquestion(@RequestParam(value = "id", required = false) Integer id, Map<String, Object> map) {
		if (id != null) {
			System.out.println(questionService.findById(id).toString());
			map.put("question", questionService.findById(id));
		}
	}

	@RequestMapping("")
	public String index(Map<String, Object> map) {
		if (adminRole()) {
			List<Question> questions = questionService.findAll();
			map.put("questions", questions);
		} else {
		  currentUserQuestions(map);
		}
		return "questions/index";
	}

	@RequestMapping("/{path}/new")
	public String fresh(@PathVariable("path") String path,Map<String, Object> map) {
		currentUserSubjcets(map);
		String userId = currentUser().getId().toString();
		List<Level> levels = levelService.findAll();
		map.put("levels", levels);
		List<ExamPoint> examPoints = new ArrayList<ExamPoint>();
		map.put("examPoints", examPoints);
		map.put("userId", userId);
		map.put("question", new Question());
		String url = "";
		if (path.equals("multiple")) {
			url = "questions/multiple_new";
		} else {
			url = "questions/essay_new";
		}
		return url;
	}

	@RequestMapping(value = "/{id}")
	public String show(@PathVariable("id") Integer id, Map<String, Object> map) {
		map.put("question", questionService.findById(id));
		return "questions/show";
	}

	@RequestMapping("/{id}/{path}/edit")
	public String edit(@PathVariable("id") Integer id, @PathVariable("path") String path, Map<String, Object> map) {
		currentUserSubjcets(map);
		levels(map);
		Question question = questionService.findById(id);
		Set<ExamPoint> examPoints = question.getExamPoint().getSubject().getExamPoints();
		map.put("question", question);
		map.put("examPoints", examPoints);
		
		String url = "";
		if (path.equals("multiple")) {
			url = "questions/multiple_edit";
		} else {
			url = "questions/essay_edit";
		}
		return url;
	}
	

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String destroy(@PathVariable("id") Integer id) {
		questionService.deleteById(id);
		return "redirect:/questions";
	}

	// 创建完返回id
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String create(@Valid Question question, Errors result,
			@RequestParam("attachment") MultipartFile file,
			@RequestParam(value="subject.id", required=false) Integer subjectId,  
			@RequestParam(value="examPoint.id", required=false) Integer examPointId, 
			@RequestParam(value="level.id", required=false) Integer levelId, 
			HttpServletRequest request, HttpServletResponse response,
			 Map<String, Object> map) throws IOException {
		if (subjectId == -1) {
			String url = "";
			if (question.getType().equals("1")) {
				url = "redirect:/questions/multiple/new";
			} else {
				url = "redirect:/questions/essay/new";
			}
			return url;
		} else {
			app.models.Subject subject = subjectService.findById(subjectId);
			question.setSubject(subject);
		}
		if(result.getErrorCount() > 0){
			for(FieldError error:result.getFieldErrors()){
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}		
			String url = "";
			if (question.getType().equals("1")) {
				url = "redirect:/questions/multiple/new";
			} else {
				url = "redirect:/questions/essay/new";
			}
			return url;
		}
		setQuestionAttributes(question, subjectId, examPointId, levelId, file, request, response);
		question.setUser(currentUser());
		question.setStatus(messageSource.getMessage("question.status.pending", null, null));
		questionService.save(question);
		return "redirect:/questions/" + question.getId().toString();
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@Valid Question question, Errors result,
			@RequestParam("attachment") MultipartFile file,
			@RequestParam(value="subject.id", required=false) Integer subjectId,  
			@RequestParam(value="examPoint.id", required=false) Integer examPointId, 
			@RequestParam(value="level.id", required=false) Integer levelId, 
			HttpServletRequest request, HttpServletResponse response,
			 Map<String, Object> map) throws IOException {
		String idString = question.getId().toString();
		if (subjectId == -1) {
			String url = "";
		  	if (question.getType().equals("1")) {
				url = "redirect:/questions/" + idString +"/multiple/edit";
			} else {
				url = "redirect:/questions/" + idString +"/essay/edit";
			}
			return url;
		} else {
			app.models.Subject subject = subjectService.findById(subjectId);
			question.setSubject(subject);
		}
		if(result.getErrorCount() > 0){
			for(FieldError error:result.getFieldErrors()){
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}		
			String url = "";
			if (question.getType().equals("1")) {
				url = "redirect:/questions/" + idString +"/multiple/edit";
			} else {
				url = "redirect:/questions/" + idString +"/essay/edit";
			}
			return url;
		}
		setQuestionAttributes(question, subjectId, examPointId, levelId, file, request, response);
		questionService.update(question);
		return "redirect:/questions/" + question.getId().toString();
	}
	
	private void setQuestionAttributes(Question question, Integer subjectId, Integer examPointId, Integer levelId, MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException {
		if (examPointId != null) {
			ExamPoint examPoint = examPointService.findById(examPointId);
			question.setExamPoint(examPoint);
		}
		if (levelId != null) {
			Level level = levelService.findById(levelId);
			question.setLevel(level);
		}
		if (!file.isEmpty()) {        
			String url = uploadFile(file, request, response);
            Set<Attachment> attachments = new HashSet<Attachment>();
            attachments.add(new Attachment(url));
            question.setAttachments(attachments);
		}
		question.setCreateTime(new Date());
	}
	
	private String uploadFile(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException {
		 String path = request.getSession().getServletContext().getRealPath("/upload/");       
         path = path.replaceAll("\\\\", "/");
         
		 String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
         String fileName = uuid + "." + file.getOriginalFilename().replaceAll("[^\\.]+\\.", "");
         String url = "upload/" + fileName;
         
         File destFile = new File(path + "/" + fileName);
         if (!destFile.getParentFile().exists()) { 
         	destFile.getParentFile().mkdirs();
         }
         
         file.transferTo(destFile);
         return url;
	}
	
	@RequestMapping(value = "/semblance", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> semblance(@RequestParam("text1") String text1, @RequestParam("subjectId") Integer subjectId) throws UnsupportedEncodingException {
		Subject subject = subjectService.findById(5);
		Set<Question> questions = subject.getQuestions();
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
			
		NumberFormat percent = NumberFormat.getPercentInstance();
        percent.setMaximumFractionDigits(2); //保留多少位
        String score = percent.format(result).toString();
		Map<String, String> map = new HashMap<String, String>();
		map.put("score", score);
		return map;
	}

}
