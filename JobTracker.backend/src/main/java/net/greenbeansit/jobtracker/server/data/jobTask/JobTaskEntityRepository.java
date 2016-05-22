package net.greenbeansit.jobtracker.server.data.jobTask;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface JobTaskEntityRepository extends CrudRepository<JobTaskEntity, Integer>
{

	void delete(JobTaskEntity deleted);
	
	@SuppressWarnings("unchecked")
	JobTaskEntity save(JobTaskEntity persisted);

	List<JobTaskEntity> findAll();

	JobTaskEntity findById(Integer id);
	
	List<JobTaskEntity> findByJobNrAndPosNr(Integer jobNr, Integer posNr);
}
