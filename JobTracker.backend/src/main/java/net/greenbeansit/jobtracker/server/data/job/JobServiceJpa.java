package net.greenbeansit.jobtracker.server.data.job;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.greenbeansit.jobtracker.server.data.userJob.UserJobDataService;
import net.greenbeansit.jobtracker.shared.Job;
import net.greenbeansit.jobtracker.shared.UserJob;

/**
 * Implements {@link JobDataService}
 * 
 * @author Mike Hukiewitz & Philipp Minges
 *
 */
@Service("jobService")
public class JobServiceJpa implements JobDataService
{

	@Autowired
	private JobEntityRepository	repository;

	@Inject
	private UserJobDataService	userJobService;

	@Override
	@Transactional
	public Job getJob(Integer jobNr, Integer posNr)
	{
		return convert(repository.findByJobNrAndPosNr(jobNr, posNr));
	}

	@Override
	@Transactional
	public boolean save(Job job)
	{
		return repository.save(convert(job)) != null;
	}

	@Override
	@Transactional
	public void delete(Job job)
	{
		repository.delete(
				repository.findByJobNrAndPosNr(job.getJobNr(), job.getPosNr()));
	}

	@Override
	@Transactional
	public List<Job> getAll()
	{
		ArrayList<Job> list = new ArrayList<Job>();
		for (JobEntity entity : repository.findAll())
		{
			list.add(convert(entity));
		}
		return list;
	}

	@Override
	@Transactional
	public List<Job> getByCustomer(Integer customerId)
	{
		ArrayList<Job> list = new ArrayList<Job>();
		for (JobEntity entity : repository.findAll())
		{
			list.add(convert(entity));
		}
		return list;
	}

	@Override
	@Transactional
	public List<Job> getByUser(Integer userId)
	{
		List<Job> jobs = new ArrayList<Job>();
		for (UserJob relation : userJobService.getByUserId(userId))
		{
			jobs.add(getJob(relation.getJobNr(), relation.getPosNo()));
		}
		// Add internal jobs
		for (JobEntity job : repository.findByInternTrue())
		{
			jobs.add(convert(job));
		}
		return jobs;
	}

	/**
	 * Converts a {@link JobEntity} to {@link Job}
	 * 
	 * @param entity
	 *            the {@link JobEntity}
	 * @return the {@link Job}
	 */
	private Job convert(JobEntity entity)
	{
		if (entity == null)
			return null;
		return new Job(entity.getJobNr(), entity.getPosNr(),
				entity.getAccountingMode(), entity.getCustomerID(),
				entity.getDesc(), entity.getMaxBudget(), entity.getUsedBudget(),
				entity.getIntern(), entity.getLocked());
	}

	/**
	 * Converts a {@link Job} to {@link JobEntity}
	 * 
	 * @param job
	 *            the {@link Job}
	 * @return the {@link JobEntity}
	 */
	private JobEntity convert(Job job)
	{
		if (job == null)
			return null;
		return new JobEntity(job.getJobNr(), job.getPosNr(), job.getPayMode(),
				job.getCustomerID(), job.getDesc(), job.getMaxBudget(),
				job.getUsedBudget(), job.isIntern(), job.isLocked());
	}
}