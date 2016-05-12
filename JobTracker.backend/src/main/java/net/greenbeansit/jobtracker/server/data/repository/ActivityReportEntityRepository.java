package net.greenbeansit.jobtracker.server.data.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.greenbeansit.jobtracker.server.data.entity.ActivityReportEntity;

public interface ActivityReportEntityRepository extends CrudRepository<ActivityReportEntity, Long>
{

	void delete(ActivityReportEntity deleted);

	List<ActivityReportEntity> findAll();

	ActivityReportEntity findById(Integer id);

	@SuppressWarnings("unchecked")
	ActivityReportEntity save(ActivityReportEntity persisted);

	//ActivityReportEntity update(ActivityReportEntity persisted);

	List<ActivityReportEntity> findByAuthor(Integer author);
	
	List<ActivityReportEntity> findByTaskId(Integer taskId);
	
	List<ActivityReportEntity> findByJobNr(Integer jobNr);
	
	//TODO add some more
}
