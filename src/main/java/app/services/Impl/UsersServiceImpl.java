package app.services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.daos.UserDao;
import app.daos.Impl.UserDaoImpl;
import app.models.User;
import app.services.UsersService;

@Service
public class UsersServiceImpl implements UsersService {
	
	@Autowired
	UserDao userDao;

	public User getUserById(Integer id) {
		User user = null;
		if (id != null) {
			user = userDao.findById(id);
		}
		return user;
	}

	public List<User> getUsers() {
		return userDao.findAll();
	}

	public Integer createUser(User user) {
		userDao.save(user);
		return user.getId();
	}

	public Integer updateUser(User user) {
		userDao.update(user);
		return  user.getId();
	}

	public void deleteById(Integer id) {
		User user = userDao.findById(id);
		userDao.delete(user);
	}

	public User getUserByEmail(String principal) {
		return userDao.selectByEmail(principal);
	}

	public User insert(User user) {
		userDao.save(user);
		return user;
	}

}
