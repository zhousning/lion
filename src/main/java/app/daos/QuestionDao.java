package app.daos;

import java.util.Date;
import java.util.List;

import app.models.Question;
import app.models.Role;



public interface QuestionDao extends BaseDao<Question> {

	public List<Question> selectByConditions(String type, Integer subjectId, int levelId, String start, String end, Integer limit);

}
