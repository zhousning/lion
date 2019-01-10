package app.services;

import java.util.List;
import org.springframework.stereotype.Service;
import app.models.User;


public interface UsersService {
	public User getUserById(Integer id);
	
	public List<User> getUsers();

	public Integer createUser(User user);

	public Integer updateUser(User user);

	public void deleteById(Integer id);

	public User getUserByEmail(String principal);

	public User insert(User user);



}
