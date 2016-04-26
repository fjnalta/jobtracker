package net.greenbeansit.jobtracker.shared;

import java.io.Serializable;

import net.greenbeansit.jobtracker.shared.exceptions.InvalidInput;

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
	private User author;

	/* -- Constructors -- */
	public ActivityReport(String description, String identifier, Date date, int startTime, int duration) {
		this.setDescription(description);
		this.setIdentifier(identifier);
		this.setDuration(duration);
		this.setStartTime(startTime);
	}
	
	/* -- Logic -- */
//	public int getUsedBudget() {
//		TODO: implement
//	}

	/* -- Getter/Setter -- */
	//TODO: Exceptions
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
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
		if(duration < 1 || startTime+duration > 1440)
			throw(new InvalidInput());
		this.duration = duration;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		if(startTime >= 1440)
			throw(new InvalidInput());
		this.startTime = startTime;
	}

	public int getEndTime() {
		return startTime+duration;
	}

	public void setEndTime(int endTime) {
		if(endTime-startTime <= 0)
			throw(new InvalidInput());
		this.duration = endTime-startTime;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		if(author == null)
			throw(new InvalidInput());
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

}
