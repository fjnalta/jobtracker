package net.greenbeansit.jobtracker.server.data.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.greenbeansit.jobtracker.server.data.entity.JobEntity;

public interface JobEntityRepository extends CrudRepository<JobEntity, Long>
{

	void delete(JobEntity deleted);

	List<JobEntity> findAll();

	JobEntity findOne(Long id);

	@SuppressWarnings("unchecked")
	JobEntity save(JobEntity persisted);

	//JobEntity update(JobEntity persisted);

	JobEntity findByJobNrAndPosNr(Integer jobNr, Integer posNr);
	
	List<JobEntity> findByCustomerID(Integer customer_id);
}
