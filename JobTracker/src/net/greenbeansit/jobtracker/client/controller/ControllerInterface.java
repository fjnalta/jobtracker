package net.greenbeansit.jobtracker.client.controller;

import net.greenbeansit.jobtracker.shared.Job;

public interface ControllerInterface {
	
	/**
	 * get a job from a jobID
	 */
	public Job getJob(String id);
}
