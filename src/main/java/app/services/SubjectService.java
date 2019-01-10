package app.services;

import app.models.Subject;

public interface SubjectService extends BaseService<Subject> {

	Subject findByName(String math);

}
