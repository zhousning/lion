package app.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Role {
	private Integer id;  
	private String name;
	private Set<User> users = new HashSet<User>();
	private Set<Permission> permissions = new HashSet<Permission>();
	private List<Integer> permissionIds = new ArrayList<Integer>();
	
	public Role(){}
	
	
	public Role(String name) {
		super();
		this.name = name;
	}


	

	public Set<Permission> getPermissions() {
		return permissions;
	}


	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}


	public List<Integer> getPermissionIds() {
		return permissionIds;
	}


	public void setPermissionIds(List<Integer> permissionIds) {
		this.permissionIds = permissionIds;
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


	

}
