package net.greenbeansit.jobtracker.server.data.activityReport;

import java.sql.Time;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.greenbeansit.jobtracker.shared.ActivityReport;

/**
 * Implements {@link ActivityReportDataService}
 * 
 * @author Mike Hukiewitz
 *
 */
@Service("activityReportService")
public class ActivityReportServiceJpa implements ActivityReportDataService
{

	@Autowired
	private ActivityReportEntityRepository repository;

	@Override
	public List<ActivityReport> getAll()
	{
		ArrayList<ActivityReport> list = new ArrayList<ActivityReport>();
		for (ActivityReportEntity entity : repository.findAll())
		{
			list.add(convert(entity));
		}
		return list;
	}

	@Override
	public ActivityReport getActivityReport(Integer reportId)
	{
		return convert(repository.findById(reportId));
	}

	@Override
	public List<ActivityReport> getByUserAndPeriod(Integer employeeId,
			Date from, Date to)
	{
		ArrayList<ActivityReport> list = new ArrayList<ActivityReport>();
		for (ActivityReportEntity entity : repository.findByAuthor(employeeId))
		{
			if (entity.getBeginDate().compareTo(from) >= 0
					&& entity.getBeginDate().compareTo(to) <= 0)
				list.add(convert(entity));
		}
		return list;
	}

	@Override
	public List<ActivityReport> getByUser(Integer employeeId)
	{
		ArrayList<ActivityReport> list = new ArrayList<ActivityReport>();
		for (ActivityReportEntity entity : repository.findByAuthor(employeeId))
		{
			list.add(convert(entity));
		}
		return list;
	}

	@Override
	public boolean save(ActivityReport report)
	{
		return repository.save(convert(report)) != null;
	}

	@Override
	public void delete(Integer reportId)
	{
		repository.delete(reportId);
	}

	@Override
	public List<ActivityReport> getByJob(Integer jobNr, Integer posNr)
	{
		ArrayList<ActivityReport> list = new ArrayList<ActivityReport>();
		for (ActivityReportEntity entity : repository.findByJobNoAndPosNo(jobNr,
				posNr))
		{
			list.add(convert(entity));
		}
		return list;
	}

	/**
	 * convert an {@link ActivityReportEntity} to a {@link ActivityReport}
	 * 
	 * @param entity
	 *            the {@link ActivityReportEntity}
	 * @return the converted {@link ActivityReport}
	 */
	private ActivityReport convert(ActivityReportEntity entity)
	{
		if (entity == null)
			return null;
		Integer begin = convert(entity.getBeginTime());
		Integer duration = convert(entity.getEndTime()) - begin;
		return new ActivityReport(entity.getId(), entity.getTaskId(),
				entity.getJobNo(), entity.getPosNo(), entity.getAuthor(),
				entity.getText(),
				new java.util.Date(entity.getBeginDate().getTime()), begin,
				duration, entity.getBreakTime());
	}

	/**
	 * convert an {@link ActivityReport} to a {@link ActivityReportEntity}
	 * 
	 * @param report
	 *            the {@link ActivityReport} to convert
	 * @return the converted {@link ActivityReportEntity}
	 */
	private ActivityReportEntity convert(ActivityReport report)
	{
		if (report == null)
			return null;
		Time begin = convert(report.getStartTime());
		Time end = convert(report.getEndTime());
		return new ActivityReportEntity(report.getAuthor(), report.getText(),
				new java.sql.Date(report.getDate().getTime()), begin, end,
				report.getBreakTime(), report.getTaskId(), report.getJobNr(),
				report.getPosNr());
	}

	/**
	 * convert time object to a Integer value
	 * 
	 * @param time
	 *            java.sql.Time object
	 * @return Integer value of the minutes and hours
	 */
	@SuppressWarnings("deprecation")
	private int convert(Time time)
	{
		return time.getMinutes() + time.getHours() * 60;
	}

	/**
	 * convert a inter value of minutes into a Time object
	 * 
	 * @param minutes
	 *            Integer value of minutes
	 * @return a java.sql.Time object
	 */
	@SuppressWarnings("deprecation")
	private Time convert(int minutes)
	{
		return new Time(minutes / 60, minutes % 60, 0);
	}

	@Override
	public List<ActivityReport> getByUserAndMonth(Integer authorId,
			Integer year, Integer month)
	{
		ArrayList<ActivityReport> list = new ArrayList<ActivityReport>();
		for (ActivityReportEntity entity : repository
				.findByAuthorAndMonth(authorId, year, month))
		{
			list.add(convert(entity));
		}
		return list;
	}

	@Override
	public List<ActivityReport> getByUserAndYear(Integer authorId, Integer year)
	{
		ArrayList<ActivityReport> list = new ArrayList<ActivityReport>();
		for (ActivityReportEntity entity : repository
				.findByAuthorAndYear(authorId, year))
		{
			list.add(convert(entity));
		}
		return list;
	}
}