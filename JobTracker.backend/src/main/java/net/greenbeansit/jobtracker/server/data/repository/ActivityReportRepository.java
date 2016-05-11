package net.greenbeansit.jobtracker.server.data.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.greenbeansit.jobtracker.server.data.entity.ActivityReportEntity;

public interface ActivityReportRepository extends CrudRepository<ActivityReportEntity, Long>
{

	void delete(ActivityReportEntity deleted);

	List<ActivityReportEntity> findAll();

	ActivityReportEntity findOne(Integer id);

	@SuppressWarnings("unchecked")
	ActivityReportEntity save(ActivityReportEntity persisted);

	ActivityReportEntity update(ActivityReportEntity persisted);

	List<ActivityReportEntity> findByAuthor(Integer AUTHOR);
	
	List<ActivityReportEntity> findByTaskId(Integer TASK_ID);
	
	List<ActivityReportEntity> findByJobId(Integer JOB_ID);
	
	//TODO add some more
}
