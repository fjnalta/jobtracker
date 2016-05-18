package net.greenbeansit.jobtracker.client.components.widgets;

import org.gwtbootstrap3.extras.select.client.ui.Option;

import net.greenbeansit.jobtracker.shared.Job;

public class SelectJobOption extends Option{
	
	private Job job;

	
	public SelectJobOption(Job job){
		this.setJob(job);
		this.setText("JobNr: " + String.valueOf(job.getJobNr()) + " | Descr: " + job.getDesc());
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

}
