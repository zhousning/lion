package app.services;

import java.util.Date;
import java.util.List;

import app.models.Question;

public interface QuestionService extends BaseService<Question> {
	
	public List<Question> selectByConditions(String type, Integer subjectId, int levelId, String start, String end, Integer limit);

}
