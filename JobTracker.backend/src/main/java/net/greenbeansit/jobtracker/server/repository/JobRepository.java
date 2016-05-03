package net.greenbeansit.jobtracker.server.repository;

import org.springframework.data.repository.CrudRepository;

import net.greenbeansit.jobtracker.server.entity.JobEntity;

public interface JobRepository extends CrudRepository<JobEntity, Long> {

	//TODO Mike: Vervollständigen
//	JobEntity findByJobID(Integer jobID, Integer posID);
//	JobEntity[] findByCustomerID(String customerID);
}
