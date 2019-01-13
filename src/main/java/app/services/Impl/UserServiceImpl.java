package app.services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.daos.UserDao;
import app.daos.Impl.UserDaoImpl;
import app.models.User;
import app.services.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
	
	@Autowired
	UserDao userDao;

	public User getUserByEmail(String principal) {
		return userDao.selectByEmail(principal);
	}

}
