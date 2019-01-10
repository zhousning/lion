package app.daos.Impl;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;

import app.daos.QuestionDao;
import app.daos.RoleDao;
import app.models.Level;
import app.models.Question;
import app.models.Role;
import app.models.User;

@Repository
public class QuestionDaoImpl extends BaseDaoImpl<Question> implements QuestionDao {

	@Override
	public List<Question> selectByConditions(String type, Integer subjectId, int levelId, String start, String end, Integer limit) {
		String sql = "from Question " + " where subject_id = " + subjectId.toString() 
			+ " and level_id = " + String.valueOf(levelId)
			+ " and type = " + type
			+ " and status = 1";

		if (start != "") {
			sql +=  " and utilityTime >= '" + start + "'";
		}
		
		if (end != "") {
			sql += " and utilityTime <= '" + end + "'";
		}
		
		limit = limit == null || limit == 0 ? 5 : limit;
		
		SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
		Session currentSession = sessionFactory.getCurrentSession();
		Query query = currentSession.createQuery(sql);
		query.setMaxResults(limit);
		return query.list();
	}

}
