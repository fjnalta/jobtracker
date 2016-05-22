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
	 * @return PseudoJobs in database
	 */
	List<PseudoJob> getAll();
	
	/**
	 * Returns a PseudoJob by its ID.
	 * @param pseudoJobId ID of requested PseudoJob
	 * @return shared PseudoJob object
	 */
	PseudoJob getById(Integer pseudoJobId);
	
	boolean save(PseudoJob report);

	void delete(PseudoJob report);
}
