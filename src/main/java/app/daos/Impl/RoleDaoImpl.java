package app.daos.Impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import app.daos.RoleDao;
import app.models.Role;
import app.models.User;

@Repository
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao{

	@Override
	public Role findByName(String name) {
		String hql = "from Role u where u.name = :name";
		List<Role> roles = (List<Role>) hibernateTemplate.findByNamedParam(hql, "name", name);
		Role role = null;
		if (roles.size() != 0) {
			role = roles.get(0);
		}
		return role;
	}

}
