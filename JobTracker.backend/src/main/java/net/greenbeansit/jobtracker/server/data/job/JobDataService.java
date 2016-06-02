package net.greenbeansit.jobtracker.server.data.job;

import java.util.List;

import net.greenbeansit.jobtracker.shared.Job;

/**
 * Database interface for {@link Job}s.
 * 
 * @author Mike Hukiewitz
 *
 */

public interface JobDataService
{

	/**
	 * Retrieves all Jobs from database.
	 * 
	 * @return a list of all Jobs
	 */
	List<Job> getAll();

	/**
	 * Retrieves a Job by its combined job number.
	 * 
	 * @param jobNr
	 *            3 to 6 digt number
	 * @param posNr
	 *            3 digit number
	 * @return corresponding Job object
	 */
	Job getJob(Integer jobNr, Integer posNr);

	/**
	 * Returns a
	 * 
	 * @param customerId
	 * @return
	 */
	List<Job> getByCustomer(Integer customerId);

	/**
	 * Retrieves all Jobs available to the given User
	 * 
	 * @param userId
	 *            ID of the user
	 * @return a list of all his bookable jobs
	 */
	List<Job> getByUser(Integer userId);

	/**
	 * Saves a Job to the database.
	 * 
	 * @param job
	 *            Job to be saved
	 * @return true if successful
	 */
	boolean save(Job job);

	/**
	 * Deletes the given Job from the database.
	 * 
	 * @param job
	 *            Job to be deleted
	 */
	void delete(Job job);
}
