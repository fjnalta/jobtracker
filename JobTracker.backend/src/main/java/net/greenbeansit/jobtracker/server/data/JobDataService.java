package net.greenbeansit.jobtracker.server.data;

import java.util.List;

import net.greenbeansit.jobtracker.shared.Job;

/**
 * Database interface for {@link Job}s.
 * 
 * @author Mike Hukiewitz
 *
 */

public interface JobDataService
{

	List<Job> getAll();

	Job getJob(Long jobId);

	Job getJob(Integer jobNr, Integer posNr);
	
	List<Job> getByCustomer(Integer customerId);

	boolean save(Job job);

	boolean update(Job job);

	void delete(Job job);
}
