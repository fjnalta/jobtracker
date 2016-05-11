package net.greenbeansit.jobtracker.server.data.implementation;

import java.sql.Time;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import javax.inject.Inject;
import org.springframework.stereotype.Service;

import net.greenbeansit.jobtracker.server.data.ActivityReportDataService;
import net.greenbeansit.jobtracker.server.data.entity.ActivityReportEntity;
import net.greenbeansit.jobtracker.server.data.repository.ActivityReportRepository;
import net.greenbeansit.jobtracker.shared.ActivityReport;

@Service("activityReportService")
public class ActivityReportServiceJpa implements ActivityReportDataService
{

	@Inject
	private ActivityReportRepository repository;

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
		return convert(repository.findOne(reportId));
	}

	@Override
	public List<ActivityReport> getByUserAndPeriod(Integer employeeId,
			Date from, Date to)
	{
		ArrayList<ActivityReport> list = new ArrayList<ActivityReport>();
		for (ActivityReportEntity entity : repository
				.findByAuthor(employeeId))
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
		for (ActivityReportEntity entity : repository
				.findByAuthor(employeeId))
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
	public boolean update(ActivityReport report)
	{
		return repository.update(convert(report)) != null;
	}

	@Override
	public void delete(ActivityReport report)
	{
		repository.delete(convert(report));
	}

	@Override
	public List<ActivityReport> getByJob(Integer jobId)
	{
		ArrayList<ActivityReport> list = new ArrayList<ActivityReport>();
		for (ActivityReportEntity entity : repository
				.findByJobId(jobId))
		{
			list.add(convert(entity));
		}
		return list;
	}

	private ActivityReport convert(ActivityReportEntity entity)
	{
		if (entity == null)
			return null;
		int begin = convert(entity.getBeginTime());
		int duration = convert(entity.getEndTime()) - begin;
		return new ActivityReport(entity.getId(), entity.getTaskId(),
				entity.getJobId(), entity.getAuthor(), entity.getText(),
				entity.getBeginDate(), begin, duration, entity.getBreakTime());
	}

	private ActivityReportEntity convert(ActivityReport report)
	{
		if (report == null)
			return null;
		Time begin = convert(report.getStartTime());
		Time end = convert(report.getEndTime());
		return new ActivityReportEntity(report.getAuthor(), report.getText(),
				report.getDate(), begin, end, report.getBreakTime(),
				report.getTaskId(), report.getJobId());
	}

	@SuppressWarnings("deprecation")
	private int convert(Time time)
	{
		return time.getMinutes() + time.getHours() * 60;
	}

	@SuppressWarnings("deprecation")
	private Time convert(int minutes)
	{
		return new Time(minutes / 60, minutes % 60, 0);
	}
}