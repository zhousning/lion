package app.controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import app.models.Model;
import app.works.ExportDoc;

@Controller
@RequestMapping("/models")
public class ModelController {
	
	@RequestMapping("/new")
	public String fresh(Map<String, Object> map) {
		List<String> attrTypes = new ArrayList<String>();
		attrTypes.add("String");
		attrTypes.add("Integer");
		attrTypes.add("Long");
		attrTypes.add( "Set");
		
		List<String> constraints = new ArrayList<String>();
		constraints.add("none");
		constraints.add("@NotBlank");
		constraints.add("@Email");
		
		List<String> associateTypes = new ArrayList<String>();
		associateTypes.add("none");
		associateTypes.add("one-to-one");
		associateTypes.add("one-to-many");
		associateTypes.add("many-to-one");
		associateTypes.add("many-to-many");
		
		map.put("attrTypes", attrTypes);
		map.put("constraints", constraints);
		map.put("associateTypes", associateTypes);
		map.put("model", new Model());
		return "models/new";
	}
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public String create(@Valid Model model, Errors result) throws Exception {
		if(result.getErrorCount() > 0){
			for(FieldError error:result.getFieldErrors()){
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}
			return "/models/new";
		}
		
		model.setName(model.getName().substring(0,1).toUpperCase().concat(model.getName().substring(1)));
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("model", model);
		
		ExportDoc maker = new ExportDoc("UTF-8");
		
		String baseUrl = "F:\\Project\\lion\\src\\main\\java\\app\\";
		String javaSuffix = ".java";
		
		String modelTemplate = "model.ftl";
		String targetModel = baseUrl + "models\\" + model.getName() + javaSuffix;
		maker.exportDoc(targetModel, map, modelTemplate);
		
		String controllerTemplate = "controller.ftl";
		String targetController = baseUrl + "controllers\\" + model.getName() + "Controller" + javaSuffix;
		maker.exportDoc(targetController, map, controllerTemplate);
		
		String serviceTemplate = "service.ftl";
		String serviceImplTemplate = "serviceImpl.ftl";
		String targetService = baseUrl + "services\\" + model.getName() + "Service" + javaSuffix;
		String targetServiceImpl = baseUrl + "services\\Impl\\" + model.getName() + "ServiceImpl" + javaSuffix;
		maker.exportDoc(targetService, map, serviceTemplate);
		maker.exportDoc(targetServiceImpl, map, serviceImplTemplate);
		
		String daoTemplate = "dao.ftl";
		String daoImplTemplate = "daoImpl.ftl";
		String targetDao = baseUrl + "daos\\" + model.getName() + "Dao" + javaSuffix;
		String targetDaoImpl = baseUrl + "daos\\Impl\\" + model.getName() + "DaoImpl" + javaSuffix;
		maker.exportDoc(targetDao, map, daoTemplate);
		maker.exportDoc(targetDaoImpl, map, daoImplTemplate);
			
		String testTemplate = "test.ftl";
		String targetTest = baseUrl + "tests\\" + model.getName() + "Test" + javaSuffix;
		maker.exportDoc(targetTest, map, testTemplate);
		
		String jspUrl = "F:\\Project\\lion\\src\\main\\webapp\\WEB-INF\\views\\";
		String folder = model.getName().substring(0,1).toLowerCase().concat(model.getName().substring(1)) + "s\\";
		String jspSuffix = ".jsp";
		
		String formTemplate = "formJsp.ftl";
		String targetForm = jspUrl + folder + "_form" + jspSuffix;
				
		File destFile = new File(targetForm);
        if (!destFile.getParentFile().exists()) { 
        	destFile.getParentFile().mkdirs();
        }
        maker.exportDoc(targetForm, map, formTemplate);
        
        String newTemplate = "newJsp.ftl";
		String targetNew = jspUrl + folder + "new" + jspSuffix;
		maker.exportDoc(targetNew, map, newTemplate);
		
		String editTemplate = "editJsp.ftl";
		String targetEdit = jspUrl + folder + "edit" + jspSuffix;
		maker.exportDoc(targetEdit, map, editTemplate);
		
		String indexTemplate = "indexJsp.ftl";
		String targetIndex = jspUrl + folder + "index" + jspSuffix;
		maker.exportDoc(targetIndex, map, indexTemplate);
	
		return "";
	}
}
