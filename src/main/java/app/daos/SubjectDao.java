package app.daos;

import app.models.Subject;

public interface SubjectDao extends BaseDao<Subject> {

	Subject findByName(String math);

}
