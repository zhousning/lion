package app.tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;

import app.services.ExamPaperService;
import app.services.ExamPointService;
import app.services.LevelService;
import app.services.RoleService;
import app.services.SubjectService;
import app.services.UsersService;

public class BaseTest {
	@Autowired
	LevelService levelService;
	@Autowired
	UsersService userService;
	@Autowired
	SubjectService subjectService;
	@Autowired
	ExamPaperService examPaperService;
	@Autowired
	ExamPointService examPointService;
	@Autowired
	RoleService roleService;
}
