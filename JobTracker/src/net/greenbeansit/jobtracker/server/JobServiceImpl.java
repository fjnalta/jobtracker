
package net.greenbeansit.jobtracker.server;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.NotFoundException;

import net.greenbeansit.jobtracker.shared.Job;
import net.greenbeansit.jobtracker.shared.JobService;

/**
 * Dummy implementation of the {@link JobService} interface.
 * 
 * @author Max Blatt
 */
public class JobServiceImpl implements JobService
{
	private static Map<Long, Job> jobMap;

	
	/**
	 * Initializes a new instance of the {@link JobServiceImpl} class.
	 */
	public JobServiceImpl()
	{
		if(jobMap == null)
			jobMap = new HashMap<Long, Job>();
	}

	@Override
	public Job[] getAllJobs()
	{
		return jobMap.values().toArray(new Job[jobMap.size()]);
	}

	@Override
	public Job getJob(Long jobid)
	{
		if(jobMap.containsKey(jobid))
			return jobMap.get(jobid);
		else
			throw new NotFoundException();
	}

}
