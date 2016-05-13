package net.greenbeansit.jobtracker.server.data;

import java.sql.Date;
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

	ActivityReport getActivityReport(Integer reportId);

	List<ActivityReport> getByUserAndPeriod(Integer authorId, Date from,
			Date to);

	List<ActivityReport> getByUser(Integer authorId);
	
	List<ActivityReport> getByJob(Integer jobNr, Integer posNr);

	boolean save(ActivityReport report);

	void delete(ActivityReport report);
}
