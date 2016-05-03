package net.greenbeansit.jobtracker.server.service;

import java.util.List;

import net.greenbeansit.jobtracker.server.entity.JobEntity;

/**
 * Database interface for {@link Job}s.
 * @author Mike Hukiewitz
 *
 */

public interface JobService {
	
	List<JobEntity> getAll();
	JobEntity getJob(Long jobId);
	boolean save(JobEntity job);
	boolean update(JobEntity job);
	boolean delete(JobEntity job);
}
