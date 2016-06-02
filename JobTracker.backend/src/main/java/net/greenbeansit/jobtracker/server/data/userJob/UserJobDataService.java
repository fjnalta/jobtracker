package net.greenbeansit.jobtracker.server.data.userJob;

import java.util.List;

import net.greenbeansit.jobtracker.shared.UserJob;

/**
 * Database interface for {@link UserJob}s.
 * 
 * @author Mike Hukiewitz
 *
 */

public interface UserJobDataService
{

	/**
	 * Returns all existing UserJobs.
	 * 
	 * @return UserJobs in database
	 */
	List<UserJob> getAll();

	/**
	 * Retrieves all UserJob relations from one user.
	 * 
	 * @param userId
	 *            ID of requested User
	 * @return shared List of UserJob relations
	 */
	List<UserJob> getByUserId(Integer userId);

	/**
	 * Retrieves all UserJob relations for a given job and pos number.
	 * 
	 * @param jobNr
	 *            3 to 6 digits
	 * @param posNr
	 *            3 digits
	 * @return List of UserJob relations
	 */
	List<UserJob> getByJobNrAndPosNr(Integer jobNr, Integer posNr);
}
