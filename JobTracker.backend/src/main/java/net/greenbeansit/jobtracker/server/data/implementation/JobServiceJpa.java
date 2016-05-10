package net.greenbeansit.jobtracker.server.data.implementation;

import java.util.List;

import javax.inject.Inject;
import org.springframework.stereotype.Service;

import net.greenbeansit.jobtracker.server.data.JobDataService;
import net.greenbeansit.jobtracker.server.data.entity.JobEntity;
import net.greenbeansit.jobtracker.server.data.repository.JobRepository;
import net.greenbeansit.jobtracker.shared.Job;
import net.greenbeansit.jobtracker.shared.JobID;

@Service("jobService")
public class JobServiceJpa implements JobDataService
{

	@Inject
	private JobRepository personRepository;

	@Override
	public Job getJob(Integer jobNr, Integer posNr)
	{
		return convert(personRepository.findByJobEntityNrAndPosNr(jobNr, posNr));
	}

	@Override
	public boolean save(Job job)
	{
		JobEntity entity = new JobEntity();
		entity.setJobNr(job.getJobNr());
		entity.setPosNr(job.getPosNr());
		entity.setPayMode(job.getPayMode());
		entity.setCustomerID(job.getCustomerID());
		entity.setDesc(job.getDesc());
		entity.setMaxBudget(job.getMaxBudget());
		entity.setUsedBudget(job.getUsedBudget());
		return personRepository.save(entity) != null;
	}

	@Override
	public boolean update(Job job)
	{
		return personRepository.update(job) != null;
	}

	@Override
	public void delete(Job job)
	{
		personRepository.delete(job);
	}

	@Override
	public List<Job> getAll()
	{
		return personRepository.findAll();
	}

	@Override
	public Job getJob(Long jobId)
	{
		return personRepository.findOne(jobId);
	}

	private Job convert(JobEntity entity)
	{
		return new Job(new JobID(entity.getJobNr(), entity.getPosNr(),
				entity.getPayMode(), entity.getCustomerID(), entity.getDesc()),
				entity.getMaxBudget(), entity.getUsedBudget());
	}
}