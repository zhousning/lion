package app.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.daos.SubjectDao;
import app.models.Subject;
import app.services.SubjectService;

@Service
public class SubjectServiceImpl extends BaseServiceImpl<Subject> implements SubjectService {

	@Autowired
	SubjectDao subjectDao;
	
	@Override
	public Subject findByName(String math) {
		return subjectDao.findByName(math);
	}

}
