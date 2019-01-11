package app.controllers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

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
		attrTypes.add("Date");

		List<String> constraints = new ArrayList<String>();
		constraints.add("none");
		constraints.add("@NotBlank");
		constraints.add("@Email");

		List<String> associateTypes = new ArrayList<String>();
		associateTypes.add("none");
		associateTypes.add("one-to-many");
		associateTypes.add("many-to-one");
		associateTypes.add("many-to-many");

		map.put("attrTypes", attrTypes);
		map.put("constraints", constraints);
		map.put("associateTypes", associateTypes);
		map.put("model", new Model());
		return "models/new";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String create(@Valid Model model, Errors result) throws Exception {
		if (result.getErrorCount() > 0) {
			for (FieldError error : result.getFieldErrors()) {
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}
			return "redirect:/models/new";
		}

		String baseUrl = "F:\\Project\\lion\\src\\main\\";
		createDoc(model, baseUrl);
		outI18n(model, baseUrl);

		return "redirect:/models/new";
	}

	public void outI18n(Model model, String baseUrl) {
		String file = baseUrl + "resources\\i18n.properties";
		String name = lowerProcess(model.getName());
		String namePlural = name + "s";

		Properties prop = new Properties();		
		try{
            FileOutputStream oFile = new FileOutputStream(file, true);
            
            prop.setProperty(namePlural + ".index", model.getTitle() + "列表");
            prop.setProperty(namePlural + ".new", model.getTitle() + "新建");
            prop.setProperty(namePlural + ".edit", model.getTitle() + "编辑");
            prop.setProperty(namePlural + ".show", model.getTitle() + "详情");
            prop.setProperty(namePlural + ".manage", model.getTitle() + "管理");
            
            List<String> attrs = model.getAttrNames();
    		List<String> labels = model.getLabels();
            for (int i = 0; i < attrs.size(); i++) {
    			prop.setProperty(name + "." + attrs.get(i), labels.get(i));
    		}
           
            prop.store(oFile, null);
            oFile.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
	}

	public void createDoc(Model model, String baseUrl) throws Exception {
		model.setName(upperProcess(model.getName()));

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("model", model);

		ExportDoc maker = new ExportDoc("UTF-8");

		String javaftls = "javaftls\\";
		String jspftls = "jspftls\\";
		String staticftls = "staticftls\\";

		String javaUrl = baseUrl + "java\\app\\";
		String javaSuffix = ".java";

		String modelTemplate = javaftls + "model.ftl";
		String targetModel = javaUrl + "models\\" + model.getName() + javaSuffix;
		maker.exportDoc(targetModel, map, modelTemplate);

		String hibernateTemplate = javaftls + "hibernate.ftl";
		String targetHibernate = javaUrl + "models\\" + model.getName() + ".hbm.xml";
		maker.exportDoc(targetHibernate, map, hibernateTemplate);

		String controllerTemplate = javaftls + "controller.ftl";
		String targetController = javaUrl + "controllers\\" + model.getName() + "Controller" + javaSuffix;
		maker.exportDoc(targetController, map, controllerTemplate);

		String serviceTemplate = javaftls + "service.ftl";
		String serviceImplTemplate = javaftls + "serviceImpl.ftl";
		String targetService = javaUrl + "services\\" + model.getName() + "Service" + javaSuffix;
		String targetServiceImpl = javaUrl + "services\\Impl\\" + model.getName() + "ServiceImpl" + javaSuffix;
		maker.exportDoc(targetService, map, serviceTemplate);
		maker.exportDoc(targetServiceImpl, map, serviceImplTemplate);

		String daoTemplate = javaftls + "dao.ftl";
		String daoImplTemplate = javaftls + "daoImpl.ftl";
		String targetDao = javaUrl + "daos\\" + model.getName() + "Dao" + javaSuffix;
		String targetDaoImpl = javaUrl + "daos\\Impl\\" + model.getName() + "DaoImpl" + javaSuffix;
		maker.exportDoc(targetDao, map, daoTemplate);
		maker.exportDoc(targetDaoImpl, map, daoImplTemplate);

		String testTemplate = javaftls + "test.ftl";
		String targetTest = javaUrl + "tests\\" + model.getName() + "Test" + javaSuffix;
		maker.exportDoc(targetTest, map, testTemplate);

		String jspUrl = baseUrl + "webapp\\WEB-INF\\views\\";
		String folder = model.getName().substring(0, 1).toLowerCase().concat(model.getName().substring(1)) + "s\\";
		String jspSuffix = ".jsp";

		String formTemplate = jspftls + "formJsp.ftl";
		String targetForm = jspUrl + folder + "_form" + jspSuffix;

		File destFile = new File(targetForm);
		if (!destFile.getParentFile().exists()) {
			destFile.getParentFile().mkdirs();
		}
		maker.exportDoc(targetForm, map, formTemplate);

		String newTemplate = jspftls + "newJsp.ftl";
		String targetNew = jspUrl + folder + "new" + jspSuffix;
		maker.exportDoc(targetNew, map, newTemplate);

		String editTemplate = jspftls + "editJsp.ftl";
		String targetEdit = jspUrl + folder + "edit" + jspSuffix;
		maker.exportDoc(targetEdit, map, editTemplate);

		String indexTemplate = jspftls + "indexJsp.ftl";
		String targetIndex = jspUrl + folder + "index" + jspSuffix;
		maker.exportDoc(targetIndex, map, indexTemplate);

		String showTemplate = jspftls + "showJsp.ftl";
		String targetShow = jspUrl + folder + "show" + jspSuffix;
		maker.exportDoc(targetShow, map, showTemplate);

		String jsUrl = baseUrl + "webapp\\static\\javascripts\\";
		String cssUrl = baseUrl + "webapp\\static\\stylesheets\\";
		String jsTemplate = staticftls + "javascript.ftl";
		String cssTemplate = staticftls + "stylesheets.ftl";
		String file = lowerProcess(model.getName()) + "s";
		String targetJs = jsUrl + file + ".js";
		String targetCss = cssUrl + file + ".css";
		maker.exportDoc(targetJs, map, jsTemplate);
		maker.exportDoc(targetCss, map, cssTemplate);
	}

	public String lowerProcess(String string) {
		return string.substring(0, 1).toLowerCase().concat(string.substring(1));
	}

	public String upperProcess(String string) {
		return string.substring(0, 1).toUpperCase().concat(string.substring(1));
	}
}
