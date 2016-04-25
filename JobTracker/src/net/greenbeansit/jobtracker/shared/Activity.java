package net.greenbeansit.jobtracker.shared;

import java.io.Serializable;
import java.util.Date;

public class Activity implements Serializable {

	private String description;
	private int workedTime;
	private int month;
	private int year;
	private int day;

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getWorkedTime() {
		return workedTime;
	}

	public void setWorkedTime(int workedTime) {
		this.workedTime = workedTime;
	}

	public Activity(String description, int workedTime,  int day, int month, int year) {
		this.description = description;
		this.workedTime = workedTime;
		this.month = month;
		this.year = year;
		this.day = day;
	}

}
