package net.greenbeansit.jobtracker.client.components.widgets;

import net.greenbeansit.jobtracker.shared.Job;
import net.greenbeansit.jobtracker.shared.PseudoJob;
import net.greenbeansit.jobtracker.shared.UtilizationWeek;
import org.gwtbootstrap3.extras.select.client.ui.Option;

/**
 * Helper class for storing the {@link Job} & {@link PseudoJob} objects in the Options of the Select field of the class
 * JobsWidget
 * @author Alexander Kirilyuk
 */
public class SelectJobOption extends Option {

	private Job job;

	private UtilizationWeek utilizationWeek;

	/**
	 * Consctructor for initialization with a {@link Job}
	 *
	 * @param job {@link Job} to save
	 */
	public SelectJobOption(Job job) {
		this.setJob(job);
		this.setText(job.toString());
	}

	/**
	 * Initializes a new Instance of {@link SelectJobOption} with a {@link UtilizationWeek}.
	 * @param utilizationWeek the {@link UtilizationWeek}
     */
	public SelectJobOption(UtilizationWeek utilizationWeek) {
		this.setUtilizationWeek(utilizationWeek);
		this.setText(utilizationWeek.getText());
	}

	/**
	 * Sets the {@link UtilizationWeek}
	 * @param utilizationWeek the {@link UtilizationWeek}.
     */
	public void setUtilizationWeek(UtilizationWeek utilizationWeek) {
		this.utilizationWeek = utilizationWeek;
	}

	/**
	 * Gets the {@link UtilizationWeek}
	 * @return the {@link UtilizationWeek}
     */
	public UtilizationWeek getUtilizationWeek() {
		return utilizationWeek;
	}

	/**
	 * get the saved Job
	 *
	 * @return {@link Job} associated with this Option
	 */
	public Job getJob() {
		return job;
	}

	/**
	 * @param job set a new {@link Job} for this Option
	 */
	public void setJob(Job job) {
		this.job = job;
	}
}
