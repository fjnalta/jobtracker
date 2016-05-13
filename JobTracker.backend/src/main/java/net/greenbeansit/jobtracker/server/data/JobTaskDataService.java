package net.greenbeansit.jobtracker.server.data;

import java.util.List;

import net.greenbeansit.jobtracker.shared.JobTask;

/**
 * Database interface for {@link JobTask}s.
 * 
 * @author Mike Hukiewitz
 *
 */

public interface JobTaskDataService
{

	/**
	 * Returns all existing JobTasks.
	 * @return JobTasks in database
	 */
	List<JobTask> getAll();
	
	/**
	 * Returns a JobTask by its ID.
	 * @param taskId ID of requested JobTask
	 * @return shared JobTask object
	 */
	JobTask getById(Integer taskId);
	
	/**
	 * Retrieves all Tasks associated with the given jobNr
	 * @param jobNr 3 to 6 digits
	 * @param posNr 3 digits
	 * @return List of Tasks
	 */
	List<JobTask> getByJobNr(Integer jobNr, Integer posNr);
}
