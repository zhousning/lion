package app.controllers;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.PropertiesEditor;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.sun.mail.iap.Literal;

import app.models.Monkey;
import app.models.Role;
import app.models.User;
import app.services.PermissionService;
import app.services.RoleService;
import app.services.UserService;

@Controller
public class BaseController {
	
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;
	@Autowired
	PermissionService permissionService;
	
	@Autowired
	ResourceBundleMessageSource messageSource;
	
/*	protected String multipleType = messageSource.getMessage("questions.multiple.code", null, null);
	protected String essayType = messageSource.getMessage("questions.essay.code", null, null);*/
	
	/*
	 * setAsText 表单提交到controller的时候执行
	 * getAsText controller到表单的时候执行，没有转换的必要可以不写
	 */

	/*@InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new MyDateEditor());
        binder.registerCustomEditor(Double.class, new DoubleEditor()); 
        binder.registerCustomEditor(Integer.class, new IntegerEditor());
        binder.registerCustomEditor(Long.class, new LongEditor());
        binder.registerCustomEditor(String.class, new StringEditor());
    }*/

    private class MyDateEditor extends PropertyEditorSupport {
        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
            	date = format.parse(text);
            } catch (ParseException e) {
                format = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    date = format.parse(text);
                } catch (ParseException e1) {
                }
            }
            setValue(date);
        }
    }
    
    public class StringEditor extends PropertiesEditor  {    
        @Override    
        public void setAsText(String text) throws IllegalArgumentException { 
            if (text == null || text.equals("")) {    
                text = "";    
            }    
            setValue(text);
        }
        
      /* @Override    
        public String getAsText() {    
            return (getValue() != null) ? getValue().toString() : "";    
        }*/
    }  
    
    public class DoubleEditor extends PropertiesEditor  {    
        @Override    
        public void setAsText(String text) throws IllegalArgumentException { 
            if (text == null || text.equals("")) {    
                text = "0";    
            }    
            setValue(Double.parseDouble(text));    
        }    
        
       /* @Override    
        public String getAsText() {    
            return (getValue() != null) ? getValue().toString() : "";    
        }    */
    }  
    
    public class IntegerEditor extends PropertiesEditor {    
        @Override    
        public void setAsText(String text) throws IllegalArgumentException {  
            if (text == null || text.equals("")) {    
                text = "0";    
            }    
            setValue(Integer.parseInt(text));    
        }    
        
       /* @Override    
        public String getAsText() {    
            return (getValue() != null) ? getValue().toString() : "";    
        }   */ 
    }  
    
    public class LongEditor extends PropertiesEditor {    
        @Override    
        public void setAsText(String text) throws IllegalArgumentException {  
            if (text == null || text.equals("")) {    
                text = "";    
            }    
            setValue(Long.parseLong(text));    
        }    
        
       /* @Override    
        public String getAsText() {    
            return (getValue() != null) ? getValue().toString() : "";    
        }   */ 
    }  
    
    protected boolean adminRole() {
    	User user = currentUser();
    	Iterator<Role> roles = user.getRoles().iterator();
    	while (roles.hasNext()) {
			Role role = (Role) roles.next();
			if (role.getName().equals(Monkey.admin)) {
				return true;
			}
		}
    	return false;
    }
    
    protected boolean leaderRole() {
    	User user = currentUser();
    	Iterator<Role> roles = user.getRoles().iterator();
    	while (roles.hasNext()) {
			Role role = (Role) roles.next();
			if (role.getName() == Monkey.leader) {
				return true;
			}
		}
    	return false;
    }
    
    protected void initRole(User user) {
		Role role = roleService.findByName(messageSource.getMessage("roles.default", null, null));
		Set<Role> roles = new HashSet<Role>();
		roles.add(role);
		user.setRoles(roles);
	}
    
    protected User currentUser() {
    	Subject currentUser = SecurityUtils.getSubject();
		String principal = currentUser.getPrincipal().toString();
		User user = userService.getUserByEmail(principal);
		return user;
	}
    
    protected void setPassword(User user) {
    	if (user.getId() != null) {
    		User baseUser = userService.findById(user.getId());
    		if (!baseUser.getPassword().equals(user.getPassword())) {
    			updatePassword(user);
    		}
		} else {
			updatePassword(user);
		}
		
	}
    
    private void updatePassword(User user) {
    	String principal = user.getEmail();
		String hashAlgorithmName = "MD5";
		Object credentials = user.getPassword();
		Object salt = ByteSource.Util.bytes(principal);
		int hashIterations = 1024;

		Object password = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
		user.setPassword(password.toString());
	}
    
    protected void updateUserSession(User user) {
    	User baseUser = userService.findById(user.getId());
    	if (!(baseUser.getEmail().equals(user.getEmail()))) {
			org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
			PrincipalCollection principalCollection = subject.getPrincipals();
			String realmName = principalCollection.getRealmNames().iterator().next();
			PrincipalCollection newPrincipalCollection = new SimplePrincipalCollection(user.getEmail(), realmName);
			subject.runAs(newPrincipalCollection);
		}
	}
}
