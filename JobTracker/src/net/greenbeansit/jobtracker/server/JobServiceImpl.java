package net.greenbeansit.jobtracker.server;

import net.greenbeansit.jobtracker.shared.Job;
import net.greenbeansit.jobtracker.shared.JobService;

public class JobServiceImpl implements JobService {
	
	private Long employeeId;
	
	public JobServiceImpl(Long employeeId)
	{
		this.employeeId = employeeId;
	}

	@Override
	public Job[] getAllJobs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Job getJob(Long jobid) {
		// TODO Auto-generated method stub
		return null;
	}

}
