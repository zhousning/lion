package app.models;

import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Lion {
	private Integer id;
	private String name;
	
	private Set<User> users = new HashSet<User>();
	

	public Lion() {
		super();
	}
	
	public Lion(
		String name,
		Set<User> users
	) {
		super();
		this.name = name;
		this.users = users;
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
	
	public void setUsers(Set<User> user) {
		this.users = users;
	}
	
	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
	
}