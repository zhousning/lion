package app.controllers;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestScope;

import app.models.ExamPoint;
import app.models.Role;
import app.models.Subject;
import app.models.User;
import app.services.SubjectService;
import app.services.UsersService;

@Controller
@RequestMapping("/subjects")
public class SubjectsController extends BaseController {

	@ModelAttribute
	public void getSubject(@RequestParam(value = "id", required = false) Integer id, Map<String, Object> map) {
		if (id != null) {
			map.put("subject", subjectService.findById(id));
		}
	}

	@RequestMapping("")
	public String index(Map<String, Object> map) {
		List<Subject> subjects = subjectService.findAll();
		Map<Integer, String> leaders = new HashMap<Integer, String>();
		Iterator<Subject> iterator = subjects.iterator();
		while (iterator.hasNext()) {
			Subject subject = (Subject) iterator.next();
			User user = userService.getUserById(subject.getLeaderId());
			if (user != null) {
				leaders.put(subject.getLeaderId(), user.getName());
			}
		}
		map.put("subjects", subjects);
		map.put("leaders", leaders);
		return "subjects/index";
	}

	@RequestMapping(value = "/{id}")
	public String show(@PathVariable("id") Integer id, Map<String, Object> map) {
		Subject subject = subjectService.findById(id);
		User user = userService.getUserById(subject.getLeaderId());
		map.put("subject", subject);
		if (user != null) {
			map.put("leader", user.getName());
		} else {
			map.put("leader", "");
		}
		return "subjects/show";
	}

	@RequestMapping("/{id}/edit")
	public String edit(@PathVariable("id") Integer id, Map<String, Object> map) {
		Subject subject = subjectService.findById(id);
		map.put("subject", subject);
		return "subjects/edit";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String destroy(@PathVariable("id") Integer id) {
		subjectService.deleteById(id);
		return "redirect:/subjects";
	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	public String update(@Valid Subject subject, Errors result,
			@RequestParam(value = "leaderIdentity", required = false) Integer leaderIdentity, 
			Map<String, Object> map) {
		if (result.getErrorCount() > 0) {
			for (FieldError error : result.getFieldErrors()) {
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}
			return "/subjects/edit";
		}
		
		Role defaultRole = roleService.findByName(messageSource.getMessage("roles.default", null, null));
		Role leaderRole = roleService.findByName(messageSource.getMessage("roles.leader", null, null));

		Integer leaderId = subject.getLeaderId();
		if (leaderIdentity != null && leaderIdentity != leaderId) {
			User oldLeader = userService.getUserById(leaderIdentity);
			Set<Role> oldRoles = new HashSet<Role>();
			oldRoles.add(defaultRole);
			oldLeader.setRoles(oldRoles);
			userService.updateUser(oldLeader);
		}
		if (leaderId != null) {
			User newLeader = userService.getUserById(leaderId);
			Set<Role> newRoles = new HashSet<Role>();
			newRoles.add(leaderRole);
			newRoles.add(defaultRole);
			newLeader.setRoles(newRoles);
			userService.updateUser(newLeader);
		}
		
		subjectService.update(subject);
		return "redirect:/subjects/" + subject.getId().toString();
	}

	@RequestMapping("/new")
	public String fresh(Map<String, Object> map) {
		map.put("subject", new Subject());
		return "subjects/new";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String create(@Valid Subject subject, Errors result, Map<String, Object> map) {
		if (result.getErrorCount() > 0) {
			for (FieldError error : result.getFieldErrors()) {
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}
			// 若验证出错, 则转向定制的页面
			return "/subjects/new";
		}
		try {
			subjectService.save(subject);
			return "redirect:/subjects";
		} catch (Exception e) {
			return "/users/new";
		}
	}

	@RequestMapping(value = "/{id}/examPoints")
	@ResponseBody
	public Map<String, String> getExamPointBySubject(@PathVariable("id") Integer id) {
		Subject subject = subjectService.findById(id);
		Set<ExamPoint> examPoints = subject.getExamPoints();
		Iterator<ExamPoint> iterator = examPoints.iterator();
		Map<String, String> map = new HashMap<String, String>();
		while (iterator.hasNext()) {
			ExamPoint examPoint = (ExamPoint) iterator.next();
			map.put(examPoint.getId().toString(), examPoint.getName());
		}
		return map;
	}

}
