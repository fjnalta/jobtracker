package net.greenbeansit.jobtracker.backend.services;

import net.greenbeansit.Jobtracker.shared.Job;

public interface IJobService {
	
	Job[] getAllJobs();
	Job getJob(Long jobid);
	

}
