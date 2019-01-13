package app.services;

import java.util.List;
import org.springframework.stereotype.Service;

import app.models.Role;
import app.models.User;


public interface UserService  extends BaseService<User> {
	public User findById(Integer id);

	public User getUserByEmail(String principal);

}
