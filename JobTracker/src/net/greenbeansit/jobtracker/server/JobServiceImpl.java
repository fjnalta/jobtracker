package net.greenbeansit.jobtracker.server;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.greenbeansit.jobtracker.shared.Job;
import net.greenbeansit.jobtracker.shared.JobID;
import net.greenbeansit.jobtracker.shared.JobService;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
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
			jobMap.put(1L,new Job(new JobID(1,1, "TM", "Kunde 1", "Beschreibung des Pr0jektes"),
					1000, 1000));
			jobMap.put(1L,new Job(new JobID(2,1, "FM", "Kunde 2", "Beschreibung des Pr0jektes"),
					1000, 1000));
			jobMap.put(1L,new Job(new JobID(3,1, "TM", "Kunde 3", "Beschreibung des Pr0jektes"),
					1000, 1000));
			jobMap.put(1L,new Job(new JobID(4,1, "TM", "Kunde 4", "Beschreibung des Pr0jektes"),
					1000, 1000));
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
