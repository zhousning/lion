package app.daos;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;

import app.models.User;

public interface UserDao extends BaseDao<User> {

	User selectByEmail(String principal);
	
}
