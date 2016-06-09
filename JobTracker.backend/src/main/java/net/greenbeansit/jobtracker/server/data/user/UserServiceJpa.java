package net.greenbeansit.jobtracker.server.data.user;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.greenbeansit.jobtracker.server.data.activityReport.ActivityReportDataService;
import net.greenbeansit.jobtracker.shared.ActivityReport;
import net.greenbeansit.jobtracker.shared.User;

/**
 * Implementation of UserDataService {@link UserDataService}.
 * 
 * @author Philipp Minges & Mike Hukiewitz
 *
 */

@Service("userService")
public class UserServiceJpa implements UserDataService
{

	@Autowired
	private UserEntityRepository		repository;
	@Inject
	private ActivityReportDataService	reportService;

	@Override
	public List<User> getAll()
	{
		ArrayList<User> list = new ArrayList<User>();
		for (UserEntity entity : repository.findAll())
		{
			list.add(convert(entity));
		}
		return list;
	}

	@Override
	public User getUser(Integer id)
	{
		return convert(repository.findById(id));
	}

	@Override
	public User getByName(String firstname, String lastname)
	{
		return convert(repository.findByName(firstname, lastname));
	}

	@Override
	public List<User> getBySupervisor(Integer supervisorId)
	{
		ArrayList<User> list = new ArrayList<User>();
		for (UserEntity entity : repository.findBySupervisor(supervisorId))
		{
			list.add(convert(entity));
		}
		return list;
	}

	/**
	 * Converts a {@link UserEntity} to {@link User}
	 * 
	 * @param entity
	 *            the {@link UserEntity}
	 * @return {@link User}
	 */
	private User convert(UserEntity entity)
	{
		if (entity == null)
			return null;
		return new User(entity.getId(), entity.getName(), entity.getSurname(),
				entity.getSupervisor());
	}

	@Deprecated
	@Override
	public Integer getUtilization(Integer employeeId, Date from, Date to)
	{
		List<ActivityReport> reports = reportService
				.getByUserAndPeriod(employeeId, from, to);
		// This is going to be so dirty.
		int averageHours = (int) (((((to.getDay() - to.getDay()) + 1)
				+ (to.getMonth() - from.getMonth()) * 30) * 8) * (5f / 7f));
		int actualHours = 0;
		for (ActivityReport report : reports)
		{
			actualHours += report.getDuration();
		}
		return (actualHours * 100) / averageHours;
	}

	@Override
	public Integer getYearUtilization(Integer employeeId)
	{
		return repository.findById(employeeId).getUtilization();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void updateYearUtilization(Integer employeeId, Integer year)
	{
		UserEntity user = repository.findById(employeeId);
		user.setUtilization(Math.max(0, getUtilization(employeeId,
				new Date(year - 1900, 0, 1), new Date(year - 1900, 11, 30))));
		user.setUtilizationYear(year);
		repository.save(user);
	}

	// Not needed

	// private UserEntity convert(User user)
	// {
	// if (user == null)
	// return null;
	// return new UserEntity(user.getId(), user.getName(), user.getSurname(),
	// user.getSupervisor());
	// }
}