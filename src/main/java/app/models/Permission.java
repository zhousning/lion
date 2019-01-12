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

public class Permission {
	private Integer id;
	@NotBlank
	private String name;
	@NotBlank
	private String url;
	
	private Set<Role> roles = new HashSet<Role>();
	private List<Integer> roleIds = new ArrayList<Integer>();

	public Permission() {
		super();
	}
	
	public Permission(
		String name,
		String url
	) {
		super();
		this.name = name;
		this.url = url;
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
	
	public Set<Role> getRoles() {
		return roles;
	}
	
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public List<Integer> getRoleIds() {
		return roleIds;
	}
	
	public void setRoleIds(List<Integer> roleIds) {
		this.roleIds = roleIds;
	}
	
	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
	
}