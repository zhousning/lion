package app.services;

import java.util.List;

public interface BaseService<T> {
	public void save(T entity);

	public void delete(T entity);
	
	public void deleteById(Integer id);

	public void update(T entity);
	
	public T findById(Integer id);

	public List<T> findAll();
	
	public List<T> findByIds(Integer[] ids);
}
