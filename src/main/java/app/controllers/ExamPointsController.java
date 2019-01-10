package app.controllers;

import java.util.List;
import java.util.Map;
import java.util.Set;

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

import app.models.ExamPoint;
import app.models.User;
import app.services.ExamPointService;

@Controller
@RequestMapping("/examPoints")
public class ExamPointsController extends BaseController {

	@Autowired
	ExamPointService examPointService;
	
	@ModelAttribute
	public void getexamPoint(@RequestParam(value="id", required=false) Integer id, Map<String, Object> map) {
		if (id != null) {
			System.out.println(examPointService.findById(id).toString());
			map.put("examPoint", examPointService.findById(id));
		}
	}
	
	@RequestMapping("")
	public String index(Map<String, Object> map) {
		map.put("examPoints", examPointService.findAll());
		return "examPoints/index";
	}
	
	@RequestMapping("/new")
	public String fresh(Map<String, Object> map) {
		currentUserSubjcets(map);
		map.put("userId", currentUser().getId());
		map.put("examPoint", new ExamPoint());

		return "examPoints/new";
	}
	
	@RequestMapping(value="/{id}")
	public String show(@PathVariable("id") Integer id, Map<String, Object> map) {
		map.put("examPoint", examPointService.findById(id));
		return "examPoints/show";
	}
	
	@RequestMapping("/{id}/edit")
	public String edit(@PathVariable("id") Integer id, Map<String, Object> map) {
		currentUserSubjcets(map);
		map.put("examPoint", examPointService.findById(id));
		return "examPoints/edit";
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public String destroy(@PathVariable("id") Integer id) {
		examPointService.deleteById(id);
		return "redirect:/examPoints";
	}
	
	//创建完返回id
	@RequestMapping(value="", method=RequestMethod.POST)
	public String create(@Valid ExamPoint examPoint, Errors result, @RequestParam(value="subject", required=false) Integer subjectId, Map<String, Object> map) {
		if(result.getErrorCount() > 0){
			for(FieldError error:result.getFieldErrors()){
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}		
			//若验证出错, 则转向定制的页面
			this.currentUserSubjcets(map);
			return "/examPoints/new";
		}
		if (subjectId != null) {
			app.models.Subject subject = subjectService.findById(subjectId);
			examPoint.setSubject(subject);
		}
		examPointService.save(examPoint);
		return "redirect:/examPoints/" + examPoint.getId().toString();
	}
	
	@RequestMapping(value="", method=RequestMethod.PUT)
	public String update(@Valid ExamPoint examPoint, Errors result, @RequestParam(value="subject.id", required=false) Integer subjectId, Map<String, Object> map) {
		if(result.getErrorCount() > 0){
			for(FieldError error:result.getFieldErrors()){
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}		
			return "/examPoints/edit";
		}
		if (subjectId != null) {
			app.models.Subject subject = subjectService.findById(subjectId);
			examPoint.setSubject(subject);
		}
		examPointService.update(examPoint);
		return "redirect:/examPoints/" + examPoint.getId().toString();
	}
	
	

}
