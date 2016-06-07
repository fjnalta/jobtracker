package net.greenbeansit.jobtracker.server.data.activityReport;

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

	/**
	 * Retrieves all reports. Should only be used when debugging (massive amount
	 * of data).
	 * 
	 * @return List of all reports
	 */
	List<ActivityReport> getAll();

	/**
	 * Retrieves a report by its ID.
	 * 
	 * @param reportId
	 *            id of the requested report
	 * @return report, null if not existent
	 */
	ActivityReport getActivityReport(Integer reportId);

	/**
	 * Retrieves all reports of a user created in the given time span.
	 * Deprecated, better use {@link this#getByUserAndMonth(Integer, Integer, Integer)}
	 * or {@link this#getByUserAndYear(Integer, Integer)}
	 * 
	 * @param authorId
	 *            ID of the author
	 * @param from
	 *            including this date
	 * @param to
	 *            also including this date
	 * @return List of corresponding reports
	 */
	@Deprecated
	List<ActivityReport> getByUserAndPeriod(Integer authorId, Date from,
			Date to);
	
	/**
	 * Retrieves all reports of a user created in the given month.
	 * @param authorId ID of the author
	 * @param year year of the month
	 * @param month the month
	 * @return List of corresponding reports
	 */
	List<ActivityReport> getByUserAndMonth(Integer authorId, Integer year, Integer month);
	
	/**
	 * Retrieves all reports of a user created in the given year.
	 * @param authorId ID of the author
	 * @param year year of the month
	 * @return List of corresponding reports
	 */
	List<ActivityReport> getByUserAndYear(Integer authorId, Integer year);

	/**
	 * Retrieves all reports created by the given author.
	 * 
	 * @param authorId
	 *            ID of the author
	 * @return List of corresponding reports
	 */
	List<ActivityReport> getByUser(Integer authorId);

	/**
	 * Retrieves all reports associated with a given job and pos number.
	 * 
	 * @param jobNr
	 *            3 to 6 digits
	 * @param posNr
	 *            3 digits
	 * @return List of corresponding reports
	 */
	List<ActivityReport> getByJob(Integer jobNr, Integer posNr);

	/**
	 * Saves a report to the database.
	 * 
	 * @param report
	 *            report to be saved
	 * @return true if successful
	 */
	boolean save(ActivityReport report);

	/**
	 * Deletes a report from the database.
	 * 
	 * @param reportId
	 *            report to be deleted
	 */
	void delete(Integer reportId);
}
