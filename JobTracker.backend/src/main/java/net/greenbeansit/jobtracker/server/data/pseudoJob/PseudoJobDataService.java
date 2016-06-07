package net.greenbeansit.jobtracker.server.data.pseudoJob;

import java.util.List;

import net.greenbeansit.jobtracker.shared.PseudoJob;

/**
 * Database interface for {@link PseudoJob}s.
 * 
 * @author Mike Hukiewitz
 *
 */

public interface PseudoJobDataService
{

	/**
	 * Returns all existing PseudoJobs.
	 * 
	 * @return PseudoJobs in database
	 */
	List<PseudoJob> getAll();

	/**
	 * Returns all existing PseudoJobs for given
	 * 
	 * @param author
	 *            the Author.
	 * @return PseudoJobs in database
	 */
	List<PseudoJob> getAllByAuthor(Integer author);

	/**
	 * Returns a PseudoJob by its ID.
	 * 
	 * @param pseudoJobId
	 *            ID of requested PseudoJob
	 * @return shared PseudoJob object
	 */
	PseudoJob getById(Integer pseudoJobId);

	/**
	 * Saves a PseudoJob to the database.
	 * 
	 * @param pseudoJob
	 *            entity to be saved
	 * @return true if successful
	 */
	boolean save(PseudoJob pseudoJob);

	/**
	 * Removes a PseudoJob from the database.
	 * 
	 * @param pseudoJob
	 *            PseudoJob to be deleted
	 */
	void delete(PseudoJob pseudoJob);
}
