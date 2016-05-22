package net.greenbeansit.jobtracker.server.data.userJob;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.greenbeansit.jobtracker.shared.UserJob;

/**
 * Implementation of UserJobDataService {@link UserJobDataService}.
 * 
 * @author Mike Hukiewitz
 *
 */

@Service("userJobService")
public class UserJobServiceJpa implements UserJobDataService {
	
	@Autowired
	private UserJobEntityRepository repository;

	@Override
	public List<UserJob> getAll() {
		ArrayList<UserJob> list = new ArrayList<UserJob>();
		for (UserJobEntity entity : repository.findAll())
		{
			list.add(convert(entity));
		}
		return list;
	}

	@Override
	public List<UserJob> getByUserId(Integer userId)
	{
		ArrayList<UserJob> list = new ArrayList<UserJob>();
		for (UserJobEntity entity : repository.findByUserId(userId))
		{
			list.add(convert(entity));
		}
		return list;
	}
	
	@Override
	public List<UserJob> getByJobNrAndPosNr(Integer jobNr, Integer posNr)
	{
		ArrayList<UserJob> list = new ArrayList<UserJob>();
		for (UserJobEntity entity : repository.findByJobNrAndPosNr(jobNr, posNr))
		{
			list.add(convert(entity));
		}
		return list;
	}

	private UserJob convert(UserJobEntity entity)
	{
		if (entity == null)
			return null;
		return new UserJob(entity.getUserId(), entity.getJobNr(), entity.getPosNr(), entity.getRole());
	}

//	private JobEntity convert(UserJob user)
//	{
//		if (user == null)
//			return null;
//		return new JobEntity(job.getJobNr(), job.getPosNr(), job.getPayMode(),
//				job.getUserJobID(), job.getDesc(), job.getMaxBudget(),
//				job.getUsedBudget());
//	}
}
