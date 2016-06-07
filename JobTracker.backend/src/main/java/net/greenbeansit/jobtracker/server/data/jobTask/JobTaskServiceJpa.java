package net.greenbeansit.jobtracker.server.data.jobTask;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.greenbeansit.jobtracker.shared.JobTask;

/**
 * Implementation of JobTaskDataService {@link JobTaskDataService}.
 * 
 * @author Mike Hukiewitz & Philipp Minges
 *
 */

@Service("jobTaskService")
public class JobTaskServiceJpa implements JobTaskDataService
{

	@Autowired
	private JobTaskEntityRepository repository;

	@Override
	public List<JobTask> getAll()
	{
		ArrayList<JobTask> list = new ArrayList<JobTask>();
		for (JobTaskEntity entity : repository.findAll())
		{
			list.add(convert(entity));
		}
		return list;
	}

	@Override
	public JobTask getById(Integer jobTaskId)
	{
		return convert(repository.findById(jobTaskId));
	}

	@Override
	public List<JobTask> getByJobNr(Integer jobNr, Integer posNr)
	{
		ArrayList<JobTask> list = new ArrayList<JobTask>();
		for (JobTaskEntity entity : repository.findByJobNrAndPosNr(jobNr,
				posNr))
		{
			list.add(convert(entity));
		}
		return list;
	}

	/**
	 * Converts a {@link JobTaskEntity} to {@link JobTask}
	 * 
	 * @param entity
	 *            the {@link JobTaskEntity}
	 * @return the {@link JobTask}
	 */
	private JobTask convert(JobTaskEntity entity)
	{
		if (entity == null)
			return null;
		return new JobTask(entity.getId(), entity.getJobNr(), entity.getPosNr(),
				entity.getName());
	}

	// private JobEntity convert(JobTask user)
	// {
	// if (user == null)
	// return null;
	// return new JobEntity(job.getJobNr(), job.getPosNr(), job.getPayMode(),
	// job.getJobTaskID(), job.getDesc(), job.getMaxBudget(),
	// job.getUsedBudget());
	// }
}
