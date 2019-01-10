package app.daos;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;

public interface BaseDao<T> {
	
	public void save(T entity);

	public void delete(T entity);
	
	public void deleteById(Integer id);

	public void update(T entity);
	
	public T findById(Integer id);

	public List<T> findAll();
	
	public List<T> findByIds(Integer[] ids);
}
