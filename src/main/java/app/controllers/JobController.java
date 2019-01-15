package app.controllers;

import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Map;
import java.util.Iterator;

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
import org.springframework.web.multipart.MultipartFile;

import app.models.ImageAttachment;

import app.models.Job;
import app.services.ImageAttachmentService;
import app.services.JobService;

import app.works.UtilTool;

@Controller
@RequestMapping("/jobs")
public class JobController extends BaseController {

	@Autowired
	JobService jobService;
	
	@Autowired
	ImageAttachmentService imageAttachmentService;
	
	@ModelAttribute
	public void getjob(@RequestParam(value="id", required=false) Integer id, Map<String, Object> map) {
		if (id != null) {
			map.put("job", jobService.findById(id));
		}
	}
	
	@RequestMapping("/index")
	public String index(Map<String, Object> map) {
		map.put("jobs", jobService.findAll());
		return "jobs/index";
	}
	
	@RequestMapping("/new")
	public String fresh(Map<String, Object> map) {
		map.put("job", new Job());
		return "jobs/new";
	}
		
	@RequestMapping("/{id}/edit")
	public String edit(@PathVariable("id") Integer id, Map<String, Object> map) {
		Job job = jobService.findById(id);
		map.put("job", job);
		return "jobs/edit";
	}
	
	@RequestMapping(value="/{id}")
	public String show(@PathVariable("id") Integer id, Map<String, Object> map) {
		map.put("job", jobService.findById(id));
		return "jobs/show";
	}

	@RequestMapping(value="/{id}/destroy", method=RequestMethod.DELETE)
	public String destroy(@PathVariable("id") Integer id) {
		jobService.deleteById(id);
		return "redirect:/jobs/index";
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String create(@Valid Job job, Errors result, Map<String, Object> map
		, @RequestParam("imagefiles") MultipartFile[] imagefiles
		, HttpServletRequest request, HttpServletResponse response
	) {
		if(result.getErrorCount() > 0){
			for(FieldError error:result.getFieldErrors()){
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}
			return "/jobs/new";
		}
		
		
		if(imagefiles!=null&&imagefiles.length>0){
		    Set<ImageAttachment> imageAttachments = new HashSet<ImageAttachment>();
            for(int i = 0;i<imagefiles.length;i++){  
                MultipartFile file = imagefiles[i];  
                if (!file.isEmpty()) {
                	try {
                		String url = UtilTool.uploadFile(file, request, response);
            			imageAttachments.add(new ImageAttachment(url));
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
            }
            job.setImageAttachments(imageAttachments);  
        }  
			
		jobService.save(job);
		return "redirect:/jobs/" + job.getId().toString();
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(@Valid Job job, Errors result, Map<String, Object> map
		, @RequestParam("imagefiles") MultipartFile[] imagefiles
		, @RequestParam("hiddenImageIds") Integer[] hiddenImageIds
		, HttpServletRequest request, HttpServletResponse response
	) {
		if(result.getErrorCount() > 0){
			for(FieldError error:result.getFieldErrors()){
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}
			return "/jobs/edit";
		}
		
		
		List<ImageAttachment> imageAttachments = new ArrayList<ImageAttachment>();
		if (hiddenImageIds != null) {
			imageAttachments = imageAttachmentService.findByIds(hiddenImageIds);
		}
		Set<ImageAttachment> images = new HashSet<ImageAttachment>(imageAttachments);
		if(imagefiles!=null&&imagefiles.length>0){  
            for(int i = 0;i<imagefiles.length;i++){  
                MultipartFile file = imagefiles[i];  
                if (!file.isEmpty()) {
                	try {
                		String url = UtilTool.uploadFile(file, request, response);
            			images.add(new ImageAttachment(url));
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
            }
            job.setImageAttachments(images);
        }  
		
		jobService.update(job);
		return "redirect:/jobs/" + job.getId().toString();
	}
		
	
	private void prepareData(Map<String, Object> map, Job job) {
	}
	
	

}