package net.greenbeansit.jobtracker.server.rest.services;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import net.greenbeansit.jobtracker.server.data.job.JobDataService;
import net.greenbeansit.jobtracker.server.data.user.UserDataService;
import net.greenbeansit.jobtracker.server.data.userJob.UserJobDataService;
import net.greenbeansit.jobtracker.shared.Job;
import net.greenbeansit.jobtracker.shared.User;
import net.greenbeansit.jobtracker.shared.rest.services.ManagerPageRestService;

public class ManagerPageRestServiceImpl implements ManagerPageRestService
{

	@Inject
	private UserJobDataService userJobService;
	@Inject
	private UserDataService userService;
	@Inject
	private JobDataService jobService;

	@Override
	public Response getEmployees(Integer supervisorId)
	{
		List<User> users = userService.getBySupervisor(supervisorId);
		List<Job> jobs = new ArrayList<Job>();
		for(User user : users)
		{
			List<Job> temp = jobService.getByUser(user.getId());
			if(!jobs.containsAll(temp)) //Performance optimizing for bigger collections
			{
				for(Job job : temp)
				{
					if(!jobs.contains(job))
						jobs.add(job);
				}
			}
		}
		return new Response(users, jobs);
	}

}
