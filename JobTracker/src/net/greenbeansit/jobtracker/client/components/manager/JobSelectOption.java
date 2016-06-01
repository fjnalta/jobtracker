package net.greenbeansit.jobtracker.client.components.manager;

import org.gwtbootstrap3.extras.select.client.ui.Option;
import org.gwtbootstrap3.extras.select.client.ui.Select;

import net.greenbeansit.jobtracker.shared.Job;

/**
 * Extends the class {@link Option} and helps to display a {@link Job} in a
 * {@link Select} widget.
 * 
 * @author Max Blatt
 */
class JobSelectOption extends Option
{
	private Job job;

	/**
	 * Initializes a new instance of the {@link JobSelectOption} class.
	 * 
	 * @param job the {@link Job} that should be displayed.
	 */
	public JobSelectOption(Job job)
	{
		this.job = job;

		this.setText(job.getJobNr() + "|" + job.getPosNr() + "|"
				+ job.getDesc());
	}

	/**
	 * Gets the {@link Job} of the current {@link JobSelectOption}.
	 * 
	 * @return a {@link Job} instance.
	 */
	public Job getJob()
	{
		return job;
	}
}
