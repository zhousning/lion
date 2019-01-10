package app.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class Question {
	private Integer id;
	@NotBlank
	private String title;
	@NotBlank
	private String content;
	@NotBlank
	private String answer;
	private String analysis;
	@NotBlank
	private String type;
	private String status;
	private Date createTime;
	private Date utilityTime;
	private Level level;
	private Subject subject;
	private User user;
	private ExamPoint examPoint;
	private Set<Attachment> attachments = new HashSet<Attachment>();
	private Set<ExamPaper> examPapers = new HashSet<ExamPaper>();
	
	@Override
	public String toString() {
		return "Question [title=" + title + ", content=" + content + ", answer=" + answer + ", analysis=" + analysis
				+ ", createTime=" + createTime + ", utilityTime=" + utilityTime + ", status=" + status + "]";
	}
	
	public Question(String title, String content, String answer, String analysis, Date createTime, Date utilityTime,
			String status) {
		super();
		this.title = title;
		this.content = content;
		this.answer = answer;
		this.analysis = analysis;
		this.createTime = createTime;
		this.utilityTime = utilityTime;
		this.status = status;
	}
	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Set<ExamPaper> getExamPapers() {
		return examPapers;
	}

	public void setExamPapers(Set<ExamPaper> examPapers) {
		this.examPapers = examPapers;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getAnalysis() {
		return analysis;
	}
	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUtilityTime() {
		return utilityTime;
	}
	public void setUtilityTime(Date utilityTime) {
		this.utilityTime = utilityTime;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Level getLevel() {
		return level;
	}
	public void setLevel(Level level) {
		this.level = level;
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
	public ExamPoint getExamPoint() {
		return examPoint;
	}
	public void setExamPoint(ExamPoint examPoint) {
		this.examPoint = examPoint;
	}
	public Set<Attachment> getAttachments() {
		return attachments;
	}
	public void setAttachments(Set<Attachment> attachments) {
		this.attachments = attachments;
	}
	
	

}
