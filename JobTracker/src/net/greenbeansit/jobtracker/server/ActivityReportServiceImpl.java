package net.greenbeansit.jobtracker.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.NotFoundException;

import net.greenbeansit.jobtracker.shared.ActivityReport;
import net.greenbeansit.jobtracker.shared.ActivityReportService;
import net.greenbeansit.jobtracker.shared.Employee;

/**
 * Dummy implementation of the {@link ActivityReportService} interface.
 * 
 * @author Max Blatt
 */
public class ActivityReportServiceImpl implements ActivityReportService
{
	private static Map<Long, ActivityReport>	reportMap;

	private Long								employeeId;

	/**
	 * Initializes a new instance of the {@link ActivityReportServiceImpl} that
	 * is associated with the {@link Employee} with the following employee ID.
	 * 
	 * @param employeeId
	 *            the ID of the employee.
	 */
	public ActivityReportServiceImpl(Long employeeId)
	{
		this.employeeId = employeeId;

		if (reportMap == null)
			reportMap = new HashMap<Long, ActivityReport>();
	}

	@Override
	public ActivityReport[] getAllReports()
	{
		List<ActivityReport> list = new ArrayList<ActivityReport>();

		for (Long l : reportMap.keySet())
		{
			ActivityReport report = reportMap.get(l);
			if (report.getAuthor().equals(employeeId))
				list.add(report);
		}

		return list.toArray(new ActivityReport[list.size()]);
	}

	@Override
	public ActivityReport getReport(Long reportId)
	{
		if (reportMap.containsKey(reportId))
			return reportMap.get(reportId);

		throw new NotFoundException();
	}

	@Override
	public ActivityReport[] getReportPeriod(Long employeeId, String from,
			String to)
	{
		// TODO Implement getReportPeriod
		return new ActivityReport[0];
	}

	@Override
	public void createReport(ActivityReport report)
	{
		if (report == null)
			throw new IllegalArgumentException();

		else if (report.getAuthor().equals(employeeId))
			throw new NotFoundException();

		report.setId(createNewReportId());

		reportMap.put(report.getId(), report);
	}

	/**
	 * Generates a new, valid ID for a report.
	 * 
	 * @return a long integer.
	 */
	private static long createNewReportId()
	{
		int newId = reportMap.size();
		while (reportMap.containsKey(newId))
			newId++;

		return newId;
	}

	@Override
	public void updateReport(ActivityReport report)
	{
		if (report == null)
			throw new IllegalArgumentException();

		else if (reportMap.containsKey(report.getId()))
			reportMap.replace(report.getId(), report);
		else
		{
			report.setId(createNewReportId());

			reportMap.put(report.getId(), report);
		}
	}

}
