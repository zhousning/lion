package app.models;

public class Attachment {
	private Integer id;
	private String image;
	private Question question;
	
	public Attachment() {
		super();
	}

	public Attachment(String image) {
		super();
		this.image = image;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}
	
	
}
