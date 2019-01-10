package app.controllers;

import java.util.Map;

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

import app.models.Level;
import app.services.LevelService;

@Controller
@RequestMapping("/levels")
public class LevelsController extends BaseController {

	@Autowired
	LevelService levelService;
	
	@ModelAttribute
	public void getlevel(@RequestParam(value="id", required=false) Integer id, Map<String, Object> map) {
		if (id != null) {
			map.put("level", levelService.findById(id));
		}
	}
	
	@RequestMapping("")
	public String index(Map<String, Object> map) {
		map.put("levels", levelService.findAll());
		return "levels/index";
	}
	
	@RequestMapping("/new")
	public String fresh(Map<String, Object> map) {
		map.put("level", new Level());
		return "levels/new";
	}
	
	@RequestMapping(value="/{id}")
	public String show(@PathVariable("id") Integer id, Map<String, Object> map) {
		map.put("level", levelService.findById(id));
		return "levels/show";
	}
	
	@RequestMapping("/{id}/edit")
	public String edit(@PathVariable("id") Integer id, Map<String, Object> map) {	
		map.put("level", levelService.findById(id));
		return "levels/edit";
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public String destroy(@PathVariable("id") Integer id) {
		levelService.deleteById(id);
		return "redirect:/levels";
	}
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public String create(@Valid Level level, Errors result, Map<String, Object> map) {
		if(result.getErrorCount() > 0){
			for(FieldError error:result.getFieldErrors()){
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}
			return "/levels/new";
		}
		levelService.save(level);
		return "redirect:/levels/" + level.getId().toString();
	}
	
	@RequestMapping(value="", method=RequestMethod.PUT)
	public String update(@Valid Level level, Errors result, Map<String, Object> map) {
		if(result.getErrorCount() > 0){
			for(FieldError error:result.getFieldErrors()){
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}		
			return "/levels/edit";
		}
		levelService.update(level);
		return "redirect:/levels/" + level.getId().toString();
	}

}
