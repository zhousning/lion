package app.models;

import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

public class Job {
	private Integer id;
	@NotBlank
	private String name;
	
	
	private Set<ImageAttachment> imageAttachments = new HashSet<ImageAttachment>();

	public Job() {
		super();
	}
	
	public Job(
		String name
	) {
		super();
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
  	public Set<ImageAttachment> getImageAttachments() {
		return imageAttachments;
	}

	public void setImageAttachments(Set<ImageAttachment> imageAttachments) {
		this.imageAttachments = imageAttachments;
	}
	
	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
	
}