package net.greenbeansit.jobtracker.shared;

import java.io.Serializable;
import java.util.Date;

public class Activity implements Serializable {

	private String descriptionOne;
	private String descriptionTwo;
	private int duration;
	private DateClass startTime;
	private DateClass endTime;
	
	public Activity(String descriptionOne, String descriptionTwo, int duration, DateClass startTime,
			DateClass endTime) {
		this.descriptionOne = descriptionOne;
		this.descriptionTwo = descriptionTwo;
		this.duration = duration;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public String getDescriptionOne() {
		return descriptionOne;
	}

	public void setDescriptionOne(String descriptionOne) {
		this.descriptionOne = descriptionOne;
	}

	public String getDescriptionTwo() {
		return descriptionTwo;
	}

	public void setDescriptionTwo(String descriptionTwo) {
		this.descriptionTwo = descriptionTwo;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public DateClass getStartTime() {
		return startTime;
	}

	public void setStartTime(DateClass startTime) {
		this.startTime = startTime;
	}

	public DateClass getEndTime() {
		return endTime;
	}

	public void setEndTime(DateClass endTime) {
		this.endTime = endTime;
	}
	
	

}
