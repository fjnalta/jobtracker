package net.greenbeansit.jobtracker.server.services;

import net.greenbeansit.jobtracker.shared.Job;

public interface IJobService {
	
	Job[] getAllJobs();
	Job getJob(Long jobid);
	

}
