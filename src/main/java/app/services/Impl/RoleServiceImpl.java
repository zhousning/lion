package app.services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.daos.RoleDao;
import app.models.Role;
import app.models.User;
import app.services.RoleService;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {
	@Autowired
	RoleDao roleDao;

	@Override
	public Role findByName(String name) {
		Role role = roleDao.findByName(name);
		return role;
	}

}
