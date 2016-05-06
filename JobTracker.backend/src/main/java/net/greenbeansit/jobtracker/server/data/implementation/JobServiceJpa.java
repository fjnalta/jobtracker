package net.greenbeansit.jobtracker.server.data.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.greenbeansit.jobtracker.server.data.JobDataService;
import net.greenbeansit.jobtracker.server.data.entity.JobEntity;
import net.greenbeansit.jobtracker.shared.Job;

@Service("jobService")
public class JobServiceJpa implements JobDataService
{

//	@Inject
//	private JobRepository	personRepository;
	private EntityManager	entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager)
	{
		this.entityManager = entityManager;
	}

	public EntityManager getEntityManager()
	{
		return entityManager;
	}

	@Transactional(readOnly = true)
	public JobEntity getById(int id)
	{
		return entityManager.find(JobEntity.class, id);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean save(JobEntity job)
	{
		entityManager.persist(job);
		entityManager.flush();
		return true;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean update(JobEntity job)
	{
		entityManager.merge(job);
		entityManager.flush();
		return true;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean delete(JobEntity job)
	{	//TODO Mike: getId()
		job = entityManager.getReference(JobEntity.class, job.getId());
		if (job == null)
			return false;
		entityManager.remove(job);
		entityManager.flush();
		return true;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public JobEntity findJobEntity(JobEntity job)
	{
		JobEntity result = null;
		Query queryFindJobEntity = entityManager
				.createNamedQuery("JobEntity.findJobEntity");
		queryFindJobEntity.setParameter("jobnr", job.getJobNr()); // TODO Mike:
																	// Change?
		queryFindJobEntity.setParameter("posnr", job.getPosNr());
		List<JobEntity> jobs = queryFindJobEntity.getResultList();
		if (jobs.size() > 0)
		{
			result = jobs.get(0);
		}
		return result;
	}

	public List<JobEntity> getAllJobEntitys()
	{
		// TODO Mike: Implement getAllJobEnt
		return null;
	}

	@Override
	public Job getJob(Integer jobNr, Integer posNr)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(Job job)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Job job)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Job job)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Job> getAll()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Job getJob(Long jobId)
	{
		// TODO Auto-generated method stub
		return null;
	}
}