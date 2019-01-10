package app.services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import app.daos.BaseDao;
import app.services.BaseService;

public class BaseServiceImpl<T> implements BaseService<T> {

	@Autowired
	BaseDao<T> dao;
	
	@Override
	public void save(T entity) {
		// TODO Auto-generated method stub
		dao.save(entity);
	}

	@Override
	public void delete(T entity) {
		// TODO Auto-generated method stub
		dao.delete(entity);
	}

	@Override
	public void update(T entity) {
		// TODO Auto-generated method stub
		dao.update(entity);
	}

	@Override
	public T findById(Integer id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		dao.deleteById(id);
	}

	@Override
	public List<T> findByIds(Integer[] ids) {
		return dao.findByIds(ids);
	}

}
