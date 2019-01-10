package app.services;

import app.models.Role;

public interface RoleService extends BaseService<Role> {
	public Role findByName(String name);
}
