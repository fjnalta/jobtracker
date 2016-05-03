package net.greenbeansit.jobtracker.server.service.jpa;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.greenbeansit.jobtracker.server.entity.JobEntity;
import net.greenbeansit.jobtracker.server.service.JobService;

@Service("jobService")
public class JobServiceJpa implements JobService {

	private EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Transactional(readOnly = true)
	public JobEntity getById(int id) {
		// TODO Auto-generated method stub
		return entityManager.find(JobEntity.class, id);
	}
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<JobEntity> getAll() {
		Query query = entityManager.createNamedQuery("JobEntity.findAll");
		List<JobEntity> jobs = null;
		jobs = query.getResultList();
		return jobs;
	}

	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public boolean save(JobEntity job) {

		entityManager.persist(job);
		entityManager.flush();

		return true;
	}
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public boolean update(JobEntity job) {
		entityManager.merge(job);
		entityManager.flush();
		return true;
	}
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public boolean delete(JobEntity job) {
		job = entityManager.getReference(JobEntity.class, job.getId()); //TODO: Change getJobEntityID to getId
		if (job == null)
			return false;
		entityManager.remove(job);
		entityManager.flush();
		return true;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public JobEntity findJobEntity(JobEntity job) {
		JobEntity result = null;
		Query queryFindJobEntity = entityManager.createNamedQuery("JobEntity.findJobEntity");
		queryFindJobEntity.setParameter("jobnr", job.getJobNr()); //TODO: Change?
		queryFindJobEntity.setParameter("posnr", job.getPosNr());
		List<JobEntity> jobs = queryFindJobEntity.getResultList();
		if(jobs.size() > 0) {
			result = jobs.get(0);
		}
		return result;
	}

	public List<JobEntity> getAllJobEntitys()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public JobEntity getJob(Long jobId)
	{
		// TODO Auto-generated method stub
		return null;
	}
}