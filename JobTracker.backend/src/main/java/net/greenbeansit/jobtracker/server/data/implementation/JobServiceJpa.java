package net.greenbeansit.jobtracker.server.data.implementation;

import java.util.ArrayList;
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
		entity.setJobNr(job.getJobID().getJobNr());
		entity.setPosNr(job.getJobID().getPosNr());
		entity.setAccountingMode(job.getJobID().getPayMode());
		entity.setCustomerID(job.getJobID().getCustomerID());
		entity.setDesc(job.getJobID().getDesc());
		entity.setMaxBudget(job.getMaxBudget());
		entity.setUsedBudget(job.getUsedBudget());
		return personRepository.save(entity) != null;
	}

	@Override
	public boolean update(Job job)
	{
		JobEntity entity = new JobEntity();
		entity.setJobNr(job.getJobID().getJobNr());
		entity.setPosNr(job.getJobID().getPosNr());
		entity.setAccountingMode(job.getJobID().getPayMode());
		entity.setCustomerID(job.getJobID().getCustomerID());
		entity.setDesc(job.getJobID().getDesc());
		entity.setMaxBudget(job.getMaxBudget());
		entity.setUsedBudget(job.getUsedBudget());
		return personRepository.update(entity) != null;
	}

	@Override
	public void delete(Job job)
	{
		personRepository.delete(personRepository.findByJobNrAndPosNr(job.getJobID().getJobNr(), job.getJobID().getPosNr()));
	}

	@Override
	public List<Job> getAll()
	{
		ArrayList<Job> list = new ArrayList<Job>();
		for(JobEntity entity : personRepository.findAll())
		{
			Job job = new Job();
			//TODO
		}
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