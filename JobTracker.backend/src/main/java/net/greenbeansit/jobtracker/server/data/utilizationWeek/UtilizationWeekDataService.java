package net.greenbeansit.jobtracker.server.data.utilizationWeek;

import java.sql.Date;
import java.util.List;

import net.greenbeansit.jobtracker.shared.UtilizationWeek;

/**
 * Database interface for {@link UtilizationWeek}s.
 * 
 * @author Mike Hukiewitz
 *
 */
public interface UtilizationWeekDataService
{

	/**
	 * Retrieves all UtilizationWeek from the database.
	 * @return List of all utilization weeks
	 */
	List<UtilizationWeek> getAll();

	/**
	 * Retrieves a UtilizationWeek by its ID.
	 * @param utilId its ID
	 * @return a UtilizationWeek
	 */
	UtilizationWeek getUtilizationWeek(Integer utilId);

	/**
	 * Retrieves all UtilizationWeek in the given span of time
	 * @param from beginning date of the utilizations
	 * @param to last date of the utilizations
	 * @return List of utilization weeks
	 */
	List<UtilizationWeek> getByPeriod(Date from, Date to);

	/**
	 * Retrieves all UtilizationWeek by their author.
	 * @param authorId ID of the author
	 * @return List of utilization weeks
	 */
	List<UtilizationWeek> getByUser(Integer authorId);

	/**
	 * Retrieve all UtilizationWeek by their assigned PseudoJob
	 * @param pseudoJobId ID of the PseudoJob
	 * @return List of utilization weeks
	 */
	List<UtilizationWeek> getByPseudoJob(Integer pseudoJobId);

	/**
	 * Saves a UtilizationWeek into the database.
	 * @param util entity to save
	 * @return true if successful
	 */
	boolean save(UtilizationWeek util);

	/**
	 * Deletes a UtilizationWeek from the database.
	 * @param util entity to delete
	 */
	void delete(UtilizationWeek util);
}
