package net.greenbeansit.jobtracker.client.components.widgets;

import org.gwtbootstrap3.extras.select.client.ui.Option;

import net.greenbeansit.jobtracker.shared.PseudoJob;
import net.greenbeansit.jobtracker.shared.Job;

/**
 * Helper class for storing the {@link Job} & {@link PseudoJob} objects in the Options of the Select field of the class
 * JobsWidget
 * @author Alexander Kirilyuk
 */
public class SelectJobOption extends Option{
	
	private Job job;
	private PseudoJob pjob;

	/**
	 * Consctructor for initialization with a {@link Job}
	 * @param job {@link Job} to save
     */
	public SelectJobOption(Job job){
		this.setJob(job);
		this.setText(job.toString());
	}

	/**
	 * Constructor for initialization with a {@link PseudoJob}
	 * @param pjob the PseudoJob
     */
	public SelectJobOption(PseudoJob pjob){
		this.setJob(pjob);
		this.setText(pjob.getName() + " - Pseudo Job");
	}

	/**
	 * get the saved Job
	 * @return {@link Job} associated with this Option
     */
	public Job getJob() {
		return job;
	}

	/**
	 *
	 * @param job set a new {@link Job} for this Option
     */
	public void setJob(Job job) {
		this.job = job;
	}

	/**
	 * get the saved Job
	 * @return {@link PseudoJob} associated with this Option
	 */
	public PseudoJob getPjob() {
		return this.pjob;
	}

	/**
	 *
	 * @param pjob set a new {@link PseudoJob} for this Option
	 */
	public void setJob(PseudoJob pjob) {
		this.pjob = pjob;
	}
}
