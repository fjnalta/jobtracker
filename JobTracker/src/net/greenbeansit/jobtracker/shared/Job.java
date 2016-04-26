package net.greenbeansit.jobtracker.shared;

import java.io.Serializable;
import java.util.LinkedList;

import net.greenbeansit.jobtracker.shared.JobID.PayMode;
import net.greenbeansit.jobtracker.shared.exceptions.InvalidInput;

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
	
	/* -- Logic -- */
	public int getUsedBudget() {
		int usedBudget = 0;
		for(ActivityReport a : activities) {
			usedBudget += a.getUsedBudget();
		}
		return usedBudget;
	}
	/**
	 * Returns the used budget of a single user.
	 * @param userID
	 * @return
	 */
	public int getUsedBudget(String userID) {
		int usedBudget = 0;
		for(ActivityReport a : activities) {
			if(a.getAuthor().getUserID() == userID) {
				usedBudget += a.getUsedBudget();
			}
		}
		return usedBudget;
	}
	/**
	 * Returns the productive amount of work time in Euro/Dollar.
	 * @return
	 */
	public int getFaktura() {
		int usedBudget = 0;
		for(ActivityReport a : activities) {
			usedBudget += a.getFaktura();
		}
		return usedBudget;
	}
	/**
	 * Returns the productive amount of work time of a single user.
	 * @param userID
	 * @return
	 */
	public int getFaktura(String userID) {
		int usedBudget = 0;
		for(ActivityReport a : activities) {
			if(a.getAuthor().getUserID() == userID) {
				usedBudget += a.getFaktura();
			}
		}
		return usedBudget;
	}
	
	/* -- Getter/Setter -- */
	public JobID getJobID() {
		return jobID;
	}

	public void setJobID(JobID jobID) {
		if(jobID == null)
			throw(new InvalidInput());
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
		if(activity == null)
			throw(new InvalidInput());
		this.activities.add(activity);
	}
	
	public long getMaxBudget() {
		return maxBudget;
	}

	public void setMaxBudget(int maxBudget) {
		if(maxBudget < 0)
			throw(new InvalidInput());
		this.maxBudget = maxBudget;
	}
}
