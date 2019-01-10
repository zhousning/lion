package app.daos.Impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import app.daos.SubjectDao;
import app.models.Subject;
import app.models.User;

@Repository
public class SubjectDaoImpl extends BaseDaoImpl<Subject> implements SubjectDao{

	@Override
	public Subject findByName(String name) {
		String hql = "from Subject s where s.name = :name";
		List<Subject> subjects = (List<Subject>) hibernateTemplate.findByNamedParam(hql, "name", name);
		Subject subject = null;
		if (subjects.size() != 0) {
			subject = subjects.get(0);
		}
		return subject;
	}

}
