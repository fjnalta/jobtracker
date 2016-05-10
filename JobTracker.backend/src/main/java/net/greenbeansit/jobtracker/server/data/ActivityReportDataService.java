package net.greenbeansit.jobtracker.server.data;

import java.util.List;

import net.greenbeansit.jobtracker.shared.ActivityReport;

/**
 * Database interface for {@link ActivityReport}s.
 * 
 * @author Mike Hukiewitz
 *
 */
public interface ActivityReportDataService
{

	List<ActivityReport> getAll();

	ActivityReport getActivityReport(Long reportId);

	List<ActivityReport> getByUserAndPeriod(Long employeeId, String from,
			String to);

	List<ActivityReport> getByUser(Long employeeId);

	void save(ActivityReport report);

	void update(ActivityReport report);

	void delete(ActivityReport report);
}
