package net.greenbeansit.jobtracker.shared;

import java.io.Serializable;

/**
 * Shared representation of an Activity (Taetigkeitsbericht (TB)).
 * Is used in frontend logic and used as a medium between frontend and backend.
 * @author Mike Hukiewitz & Alex
 *
 */
public class ActivityReport implements Serializable {

	private String description;
	private String identifier;
	private Date date;
	private int startTime;
	private int duration;
	private int breakTime;
	private User author;

	/* -- Constructors -- */
	public ActivityReport(String description, String identifier, Date date, int startTime, int duration) {
		this.setDescription(description);
		this.setIdentifier(identifier);
		this.setDuration(duration);
		this.setStartTime(startTime);
	}
	
	/* -- Logic -- */
	/**
	 * Returns how much the company has to pay the author for the work done in Euro/Dollar.
	 * @return 
	 */
	public int getUsedBudget() {
		return (int)(duration*author.getHourlyPay())/60;
	}
	
	public int getFaktura() {
		return (int)((duration-breakTime)*author.getHourlyPay())/60;
	}

	/* -- Getter/Setter -- */
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if(description == "")
			throw(new IllegalArgumentException());
		this.description = description;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		if(duration < 1 || startTime+duration+breakTime > 1440 || duration < breakTime)
			throw(new IllegalArgumentException());
		this.duration = duration;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		if(startTime >= 1440 || startTime+duration+breakTime > 1440)
			throw(new IllegalArgumentException());
		this.startTime = startTime;
	}

	public int getEndTime() {
		return startTime+duration;
	}

	public void setEndTime(int endTime) {
		if(endTime-startTime-breakTime <= 0 || endTime > 1440)
			throw(new IllegalArgumentException());
		this.duration = endTime-startTime;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		if(author == null)
			throw(new IllegalArgumentException());
		this.author = author;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public void setDate(int year, int month, int day) {
		this.date = new Date(year, month, day);
	}

	public int getBreakTime() {
		return breakTime;
	}

	public void setBreakTime(int breakTime) {
		if(breakTime > duration)
			throw(new IllegalArgumentException());
		this.breakTime = breakTime;
	}

}
