package app.daos;

import app.models.Role;

public interface RoleDao extends BaseDao<Role> {

	Role findByName(String name);

}
