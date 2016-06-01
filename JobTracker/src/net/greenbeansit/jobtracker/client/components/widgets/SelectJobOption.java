package net.greenbeansit.jobtracker.client.components.widgets;

import org.gwtbootstrap3.extras.select.client.ui.Option;

import net.greenbeansit.jobtracker.shared.PseudoJob;
import net.greenbeansit.jobtracker.shared.Job;

public class SelectJobOption extends Option{
	
	private Job job;
	private PseudoJob pjob;

	
	public SelectJobOption(Job job){
		this.setJob(job);
		this.setText(job.toString());
	}

	public SelectJobOption(PseudoJob pjob){
		this.setJob(pjob);
		this.setText(pjob.getName() + " - Pseudo Job");
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public PseudoJob getPjob() {
		return this.pjob;
	}

	public void setJob(PseudoJob pjob) {
		this.pjob = pjob;
	}
}
