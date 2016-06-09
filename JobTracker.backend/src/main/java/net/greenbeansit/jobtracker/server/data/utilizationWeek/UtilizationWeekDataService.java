package net.greenbeansit.jobtracker.server.data.utilizationWeek;

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
	 * 
	 * @return List of all utilization weeks
	 */
	List<UtilizationWeek> getAll();

	/**
	 * Retrieves a UtilizationWeek by its ID.
	 * 
	 * @param utilId
	 *            its ID
	 * @return a UtilizationWeek
	 */
	UtilizationWeek getUtilizationWeek(Integer utilId);

	/**
	 * Retrieves all UtilizationWeek by their author.
	 * 
	 * @param authorId
	 *            ID of the author
	 * @return List of utilization weeks
	 */
	List<UtilizationWeek> getByUser(Integer authorId);

	/**
	 * Retrieve all UtilizationWeek by their assigned PseudoJob
	 * 
	 * @param pseudoJobId
	 *            ID of the PseudoJob
	 * @return List of utilization weeks
	 */
	List<UtilizationWeek> getByPseudoJob(Integer pseudoJobId);

	/**
	 * Retrieve all UtilizationWeek by their assigned author and month
	 * 
	 * @param authorId
	 *            ID of the author
	 * @param year
	 *            the year
	 * @param month
	 *            the month
	 * @return List of utilization weeks
	 */
//	List<UtilizationWeek> getByUserAndMonth(Integer authorId, Integer year,
//			Integer month);

	/**
	 * Retrieve all UtilizationWeek by their assigned author and year
	 * 
	 * @param authorId
	 *            ID of the author
	 * @param year
	 *            the year
	 * @return List of utilization weeks
	 */
//	List<UtilizationWeek> getByUserAndYear(Integer authorId, Integer year);

	/**
	 * Saves a UtilizationWeek into the database.
	 * 
	 * @param util
	 *            entity to save
	 * @return true if successful
	 */
	boolean save(UtilizationWeek util);

	/**
	 * Deletes a UtilizationWeek from the database.
	 * 
	 * @param utilId
	 *            ID of the entity to delete
	 */
	void delete(Integer utilId);
}
