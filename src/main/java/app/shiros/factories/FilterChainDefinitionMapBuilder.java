package app.shiros.factories;

import java.util.LinkedHashMap;

import app.models.Monkey;
import app.services.RoleService;

public class FilterChainDefinitionMapBuilder {

	//注意顺序，在前的用在前的
	public LinkedHashMap<String, String> buildFilterChainDefinitionMap(){
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		map.put("/users/sign_in", "anon");
		map.put("/users/sign_up", "anon");
		map.put("/users/passwords**", "anon");
		map.put("/users/reset_password", "anon");
		map.put("/shiro/logout", "logout");
				
		map.put("/users", "authc, perms[user:read]");
		map.put("/home", "authc");
		map.put("/questions/**", "authc");
		map.put("/subjects/**", "authc");
		map.put("/levels/**", "authc");
		map.put("/checks/**", "authc");
		map.put("/examPoints/**", "authc");
		map.put("/examPapers/**", "authc");
		
		String admin = "authc, roles[admin]";
		String leader = "authc, roles[leader]";
		String teacher = "authc, roles[teacher]";
		String adminLeaderAndTeacher = "roles[admin], roles[leader], roles[teacher]";
		String leaderAndTeacher = "roles[leader], roles[teacher]";
		
		/*map.put(Monkey.userIndex, admin);
		map.put(Monkey.subjectIndex, admin);
		
		map.put(Monkey.questionIndex, admin);
		map.put(Monkey.questionIndex, leader);
		map.put(Monkey.questionIndex, teacher);
		
		map.put(Monkey.levelIndex, leader);
		
		map.put(Monkey.examPointIndex, leader);
		
		map.put(Monkey.examPaperIndex, admin);
		map.put(Monkey.examPaperIndex, leader);
		map.put(Monkey.examPaperIndex, teacher);*/
		
		
		
		//map.put(Monkey.userFresh, "authc,roles[admin]");
		//map.put(Monkey.userEdit, "authc,roles[admin]");
		//map.put(Monkey.userDestroy, "authc,perms[user:delete]");
		//map.put(Monkey.userShow, "authc,roles[admin]");	
		
		/*
		map.put("/user.jsp", "authc,roles[user]");//authc必须是通过认证访问的，而不是remember me
		map.put("/admin.jsp", "authc,roles[admin]");
		map.put("/list.jsp", "user");//认证和remember me 都可以
		*/
		
		return map;
	}
	
}
