package app.models;

public class ImageAttachment {
	private Integer id;
	private String name;
	private String url;
	
	private Job job;
	
	public ImageAttachment() {
		super();
	}

	public ImageAttachment(String name, String url) {
		super();
		this.name = name;
		this.url = url;
	}

	public ImageAttachment(String url) {
		super();
		this.url = url;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}


	
}
