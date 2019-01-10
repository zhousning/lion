package app.services.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.daos.QuestionDao;
import app.models.Question;
import app.models.Subject;
import app.services.QuestionService;
import app.services.SubjectService;

@Service
public class QuestionServiceImpl extends BaseServiceImpl<Question> implements QuestionService {
	
	@Autowired
	QuestionDao questionDao;

	@Override
	public List<Question> selectByConditions(String type, Integer subjectId, int levelId, String start, String end, Integer limit) {
		return questionDao.selectByConditions(type, subjectId, levelId, start, end, limit);
	}
	
}
