package net.greenbeansit.jobtracker.server.data.activityReport;

import java.sql.Time;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.greenbeansit.jobtracker.shared.ActivityReport;

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
	public void delete(ActivityReport report)
	{
		repository.delete(convert(report));
	}

	@Override
	public List<ActivityReport> getByJob(Integer jobNr, Integer posNr)
	{
		ArrayList<ActivityReport> list = new ArrayList<ActivityReport>();
		for (ActivityReportEntity entity : repository
				.findByJobNoAndPosNo(jobNr, posNr))
		{
			list.add(convert(entity));
		}
		return list;
	}

	private ActivityReport convert(ActivityReportEntity entity)
	{
		if (entity == null)
			return null;
		Integer begin = convert(entity.getBeginTime());
		System.out.println(entity.getBeginTime());
		Integer duration = convert(entity.getEndTime()) - begin;
		return new ActivityReport(entity.getId(), entity.getTaskId(),
				entity.getJobNo(), entity.getAuthor(), entity.getText(), //TODO check jobnr-posnr
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
				report.getTaskId(), report.getJobNr(), report.getPosNr());
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