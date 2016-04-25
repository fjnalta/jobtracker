package net.greenbeansit.jobtracker.shared;

import java.io.Serializable;
import java.util.LinkedList;

public class Job implements Serializable {
	
	private LinkedList<Activity> Activities;
	private String jobName;
	private int id;
	private int budget;
	private static int hourCost = 30;
	
	public LinkedList<Activity> getActivities() {
		return Activities;
	}

	public void setActivities(LinkedList<Activity> activities) {
		Activities = activities;
	}

	public long getBudget() {
		return budget;
	}
	
	public int getWorkedBudget(){
		int workedBudget = 0;
		for(Activity a : this.Activities){
			workedBudget = a.getWorkedTime()*hourCost;
		}
		return workedBudget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	public Job(){
		this.Activities = new LinkedList<Activity>();
		this.jobName = "";
		this.id = 0;
	}
	
	public Job(int id, String jobName, LinkedList<Activity> activities,int budget){
		this.id = id;
		this.jobName = jobName;
		this.Activities = activities;
		this.budget = budget;
	}
	
	public LinkedList<Activity> getActivitiess() {
		if(this.Activities!=null){
			return Activities;
		}
		return null;
	}
	public void setActivitys(LinkedList<Activity> Activities) {
		this.Activities = Activities;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Activity getActivity(int index){
		if(this.Activities!=null){
			return this.Activities.get(index);
		}
		return null;
	}
	
	public void addActivity(Activity activity){
		this.Activities.add(activity);
	}
}
