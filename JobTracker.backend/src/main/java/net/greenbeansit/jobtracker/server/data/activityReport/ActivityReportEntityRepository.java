package net.greenbeansit.jobtracker.server.data.activityReport;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ActivityReportEntityRepository extends CrudRepository<ActivityReportEntity, Integer>
{

	void delete(ActivityReportEntity deleted);

	List<ActivityReportEntity> findAll();

	ActivityReportEntity findById(Integer id);

	@SuppressWarnings("unchecked")
	ActivityReportEntity save(ActivityReportEntity persisted);

	List<ActivityReportEntity> findByAuthor(Integer author);
	
	List<ActivityReportEntity> findByTaskId(Integer taskId);
	
	List<ActivityReportEntity> findByJobNrAndPosNr(Integer jobNr, Integer posNr);
	
	//TODO Mike: add from Date to Date
}
