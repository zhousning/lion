package app.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class ExamPaper {
	private Integer id;
	@NotBlank
	private String major;
	@NotBlank
	private String date;
	@NotBlank
	private String duration;
	@NotBlank
	private String space;
	@NotBlank
	private String introduction;
	@NotBlank
	private String partAInfo;
	@NotBlank
	private String partBInfo;
	private String status;
	private Subject subject;
	private User user;
	private Set<Question> questions = new HashSet<Question>();
	private List<Integer> questionIds = new ArrayList<Integer>();
	
	
	
	public ExamPaper() {
		super();
	}
	public ExamPaper(String major, String date, String duration, String space, String introduction, String partAInfo,
			String partBInfo) {
		super();
		this.major = major;
		this.date = date;
		this.duration = duration;
		this.space = space;
		this.introduction = introduction;
		this.partAInfo = partAInfo;
		this.partBInfo = partBInfo;
	}
	
	
	
	public List<Integer> getQuestionIds() {
		return questionIds;
	}
	public void setQuestionIds(List<Integer> questionIds) {
		this.questionIds = questionIds;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Set<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getSpace() {
		return space;
	}
	public void setSpace(String space) {
		this.space = space;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getPartAInfo() {
		return partAInfo;
	}
	public void setPartAInfo(String partAInfo) {
		this.partAInfo = partAInfo;
	}
	public String getPartBInfo() {
		return partBInfo;
	}
	public void setPartBInfo(String partBInfo) {
		this.partBInfo = partBInfo;
	}
	
	

}
