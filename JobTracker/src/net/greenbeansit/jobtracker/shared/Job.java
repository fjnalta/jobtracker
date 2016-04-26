package net.greenbeansit.jobtracker.shared;

import java.io.Serializable;
import java.util.LinkedList;

import net.greenbeansit.jobtracker.shared.JobID.PayMode;

/**
 * Shared representation of a job. Is used in frontend logic and used as a
 * medium between frontend and backend. A job consists of several
 * {@code Activity} to determine the used budget;
 * @author Mike Hukiewitz & Alex
 *
 */
public class Job implements Serializable {

	private JobID jobID;
	private LinkedList<ActivityReport> activities;
	private int maxBudget;
	private int usedBudget;

	
	/* -- Constructors -- */
	public Job(JobID jobID, int maxBudget) {
		this.setJobID(jobID);
		this.setActivities(new LinkedList<ActivityReport>());
		this.setMaxBudget(maxBudget);
	}
	
	public Job(String jobID, int maxBudget) {
		this.setJobID(jobID);
		this.setActivities(new LinkedList<ActivityReport>());
		this.setMaxBudget(maxBudget);
	}

	public Job(JobID jobID, int maxBudget, LinkedList<ActivityReport> activities) {
		this.setJobID(jobID);
		this.setActivities(activities);
		this.setMaxBudget(maxBudget);
	}
	
	/* -- Getter/Setter -- */
	//TODO: Exceptions
	public JobID getJobID() {
		return jobID;
	}

	public void setJobID(JobID jobID) {
		this.jobID = jobID;
	}
	
	public void setJobID(String jobID) {
		this.jobID = JobIDParser.parse(jobID);
	}
	
	public void setJobID(int jobNr, int posNr, PayMode payMode, String clientID, String desc) {
		this.jobID = new JobID(jobNr, posNr, payMode, clientID, desc);
	}
	
	public LinkedList<ActivityReport> getActivities() {
		return activities;
	}

	public void setActivities(LinkedList<ActivityReport> activities) {
		this.activities = activities;
	}

	public ActivityReport getActivity(int index) {
		if (this.activities != null)
			return this.activities.get(index);
		else
			return null;
	}

	public void addActivity(ActivityReport activity) {
		this.activities.add(activity);
	}
	
	public long getMaxBudget() {
		return maxBudget;
	}

	public void setMaxBudget(int maxBudget) {
		this.maxBudget = maxBudget;
	}
}
