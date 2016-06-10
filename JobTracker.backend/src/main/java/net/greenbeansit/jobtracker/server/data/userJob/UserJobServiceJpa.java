package net.greenbeansit.jobtracker.server.data.userJob;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.greenbeansit.jobtracker.server.data.user.UserDataService;
import net.greenbeansit.jobtracker.shared.User;
import net.greenbeansit.jobtracker.shared.UserJob;

/**
 * Implementation of UserJobDataService {@link UserJobDataService}.
 * 
 * @author Mike Hukiewitz
 *
 */

@Service("userJobService")
public class UserJobServiceJpa implements UserJobDataService
{

	@Autowired
	private UserJobEntityRepository	repository;
	@Inject
	private UserDataService			userService;

	@Override
	public List<UserJob> getAll()
	{
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
	public List<User> getByJobNrAndPosNr(Integer jobNr, Integer posNr)
	{
		ArrayList<User> list = new ArrayList<User>();
		for (UserJobEntity uj : repository.findByJobNrAndPosNr(jobNr, posNr))
		{
			User u = userService.getUser(uj.getUserId());
			if (!list.contains(u))
				list.add(u);
		}

		return list;
	}

	/**
	 * Converts a {@link UserJobEntity} to {@link UserJob}
	 * 
	 * @param entity
	 *            the {@link UserJobEntity} to be converted
	 * @return the UserJob.
	 */
	private UserJob convert(UserJobEntity entity)
	{
		if (entity == null)
			return null;
		return new UserJob(entity.getUserId(), entity.getJobNr(),
				entity.getPosNr(), entity.getRole());
	}

	// private JobEntity convert(UserJob user)
	// {
	// if (user == null)
	// return null;
	// return new JobEntity(job.getJobNr(), job.getPosNr(), job.getPayMode(),
	// job.getUserJobID(), job.getDesc(), job.getMaxBudget(),
	// job.getUsedBudget());
	// }
}
