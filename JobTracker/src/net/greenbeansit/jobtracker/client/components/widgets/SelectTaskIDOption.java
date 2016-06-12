package net.greenbeansit.jobtracker.client.components.widgets;

import org.gwtbootstrap3.extras.select.client.ui.Option;

import net.greenbeansit.jobtracker.shared.Job;
import net.greenbeansit.jobtracker.shared.JobTask;
import net.greenbeansit.jobtracker.shared.PseudoJob;

/**
 * Helper class for storing the {@link JobTask} objects in the Options of the
 * Select field of the class
 * 
 * @author Jonathan Brenner
 *
 */
public class SelectTaskIDOption extends Option {
	
	private JobTask 	jobTask;
	
	/**
	 * Initializes a new Instance of a {@link SelectTaskIDOption} with a {@link JobTask}
	 * @param jobtask the {@link JobTask}
	 */
	public SelectTaskIDOption(JobTask jobtask){
		this.jobTask = jobtask;
		this.setText(jobTask.getName());
	}
	
	/**
	 * Gets the {@link JobTask}
	 * @return jobTask the {@link JobTask} 
	 */
	public JobTask getJobTask() {
		return this.jobTask;
	}
	
	/**
	 * Sets the {@link JobTask}
	 * @param jobTask the {@link JobTask}
	 */
	public void setJobTask(JobTask jobTask) {
		this.jobTask = jobTask;
	}
	
}
