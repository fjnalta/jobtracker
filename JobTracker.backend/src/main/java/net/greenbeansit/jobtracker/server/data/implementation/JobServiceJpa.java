package net.greenbeansit.jobtracker.server.data.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import org.springframework.stereotype.Service;

import net.greenbeansit.jobtracker.server.data.JobDataService;
import net.greenbeansit.jobtracker.server.data.entity.JobEntity;
import net.greenbeansit.jobtracker.server.data.repository.JobRepository;
import net.greenbeansit.jobtracker.shared.Job;

@Service("jobService")
public class JobServiceJpa implements JobDataService
{

	@Inject
	private JobRepository personRepository;

	@Override
	public Job getJob(Integer jobNr, Integer posNr)
	{
		return convert(personRepository.findByJobNrAndPosNr(jobNr, posNr));
	}

	@Override
	public boolean save(Job job)
	{
		return personRepository.save(convert(job)) != null;
	}

	@Override
	public boolean update(Job job)
	{
		return personRepository.update(convert(job)) != null;
	}

	@Override
	public void delete(Job job)
	{
		personRepository.delete(personRepository
				.findByJobNrAndPosNr(job.getJobNr(), job.getPosNr()));
	}

	@Override
	public List<Job> getAll()
	{
		ArrayList<Job> list = new ArrayList<Job>();
		for (JobEntity entity : personRepository.findAll())
		{
			list.add(convert(entity));
		}
		return list;
	}

	@Override
	public Job getJob(Long jobId)
	{
		return convert(personRepository.findOne(jobId));
	}

	private Job convert(JobEntity entity)
	{
		if (entity == null)
			return null;
		return new Job(entity.getJobNr(), entity.getPosNr(),
				entity.getAccountingMode(), entity.getCustomerID(),
				entity.getDesc(), entity.getMaxBudget(),
				entity.getUsedBudget());
	}

	private JobEntity convert(Job job)
	{
		if (job == null)
			return null;
		return new JobEntity(job.getJobNr(), job.getPosNr(), job.getPayMode(),
				job.getCustomerID(), job.getDesc(), job.getMaxBudget(),
				job.getUsedBudget());
	}
}