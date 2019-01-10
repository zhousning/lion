package app.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class Subject {
	private Integer id;
	@NotBlank
	private String code;
	@NotBlank
	private String name;
	private Integer leaderId;
	private Set<User> users = new HashSet<User>();
	private Set<Question> questions = new HashSet<Question>();
	private Set<ExamPoint> examPoints = new HashSet<ExamPoint>();
	private Set<ExamPaper> examPapers = new HashSet<ExamPaper>();
	
	public Subject(){}
	
	
	public Subject(String name) {
		super();
		this.name = name;
	}
	
	public Subject(String name, String code) {
		super();
		this.name = name;
		this.code = code;
	}
	
	

	public Set<Question> getQuestions() {
		return questions;
	}


	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}


	
	
	public Set<ExamPaper> getExamPapers() {
		return examPapers;
	}


	public void setExamPapers(Set<ExamPaper> examPapers) {
		this.examPapers = examPapers;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Set<User> getUsers() {
		return users;
	}


	public void setUsers(Set<User> users) {
		this.users = users;
	}


	public Integer getLeaderId() {
		return leaderId;
	}


	public void setLeaderId(Integer leaderId) {
		this.leaderId = leaderId;
	}


	public Set<ExamPoint> getExamPoints() {
		return examPoints;
	}


	public void setExamPoints(Set<ExamPoint> examPoints) {
		this.examPoints = examPoints;
	}


	

}
