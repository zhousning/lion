package app.daos.Impl;

import org.springframework.stereotype.Repository;

import app.daos.JobDao;
import app.models.Job;

@Repository
public class JobDaoImpl extends BaseDaoImpl<Job> implements JobDao{

}