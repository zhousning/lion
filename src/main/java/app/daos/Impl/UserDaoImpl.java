package app.daos.Impl;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.daos.BaseDao;
import app.daos.UserDao;
import app.models.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{

	public User selectByEmail(String principal) {
		String hql = "from User u where u.email = :email";
		List<User> users = (List<User>) hibernateTemplate.findByNamedParam(hql, "email", principal);
		User user = null;
		if (users.size() != 0) {
			user = users.get(0);
		}
		return user;
	}

}
