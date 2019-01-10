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

import app.models.Lion;
import app.services.LionService;

@Controller
@RequestMapping("/lions")
public class LionsController extends BaseController {

	@Autowired
	LionService lionService;
	
	@ModelAttribute
	public void getlion(@RequestParam(value="id", required=false) Integer id, Map<String, Object> map) {
		if (id != null) {
			map.put("lion", lionService.findById(id));
		}
	}
	
	@RequestMapping("/index")
	public String index(Map<String, Object> map) {
		map.put("lions", lionService.findAll());
		return "lions/index";
	}
	
	@RequestMapping("/new")
	public String fresh(Map<String, Object> map) {
		map.put("lion", new Lion());
		return "lions/new";
	}
	
	@RequestMapping(value="/{id}")
	public String show(@PathVariable("id") Integer id, Map<String, Object> map) {
		map.put("lion", lionService.findById(id));
		return "lions/show";
	}
	
	@RequestMapping("/{id}/edit")
	public String edit(@PathVariable("id") Integer id, Map<String, Object> map) {	
		map.put("lion", lionService.findById(id));
		return "lions/edit";
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public String destroy(@PathVariable("id") Integer id) {
		lionService.deleteById(id);
		return "redirect:/lions";
	}
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public String create(@Valid Lion lion, Errors result, Map<String, Object> map) {
		if(result.getErrorCount() > 0){
			for(FieldError error:result.getFieldErrors()){
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}
			return "/lions/new";
		}
		lionService.save(lion);
		return "redirect:/lions/" + lion.getId().toString();
	}
	
	@RequestMapping(value="", method=RequestMethod.PUT)
	public String update(@Valid Lion lion, Errors result, Map<String, Object> map) {
		if(result.getErrorCount() > 0){
			for(FieldError error:result.getFieldErrors()){
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}		
			return "/lions/edit";
		}
		lionService.update(lion);
		return "redirect:/lions/" + lion.getId().toString();
	}

}