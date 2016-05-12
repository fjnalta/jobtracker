package net.greenbeansit.jobtracker.server.data.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.greenbeansit.jobtracker.server.data.entity.JobEntity;

public interface JobEntityRepository extends CrudRepository<JobEntity, Integer>
{

	void delete(JobEntity deleted);

	List<JobEntity> findAll();

	@SuppressWarnings("unchecked")
	JobEntity save(JobEntity persisted);

	JobEntity findByJobNrAndPosNr(Integer jobNr, Integer posNr);
	
	List<JobEntity> findByCustomerID(Integer customer_id);
}
