package net.greenbeansit.jobtracker.backend.services.job;

import net.greenbeansit.Jobtracker.shared.Job;

public interface IJobService {
	
	Job[] getAllJobs();
	Job getJob(Long jobid);
	

}
