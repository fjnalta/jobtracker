package net.greenbeansit.jobtracker.backend.services;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.NotFoundException;

import net.greenbeansit.Jobtracker.shared.*;

/**
 * Dummy implementation of the {@link JobService} interface.
 * 
 * @author Max Blatt
 */
public class JobServiceImpl implements IJobService
{
	private static Map<Long, Job> jobMap;
	
	private Long employeeId;
	
	/**
	 * Initializes a new instance of the {@link JobServiceImpl} class
	 * associated with the employee with the following ID.
	 * 
	 * @param employeeId the ID of the employee.
	 */
	public JobServiceImpl(Long employeeId)
	{
		if(jobMap == null)
			jobMap = new HashMap<Long, Job>();
		
		this.employeeId = employeeId;
	}

	public Job[] getAllJobs()
	{
		return jobMap.values().toArray(new Job[jobMap.size()]);
	}

	public Job getJob(Long jobid)
	{
		if(jobMap.containsKey(jobid))
			return jobMap.get(jobid);
		else
			throw new NotFoundException();
	}

}
