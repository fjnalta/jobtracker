
package net.greenbeansit.jobtracker.shared;

import java.io.Serializable;
import java.util.Date;

/**
 * Shared representation of an Activity (Taetigkeitsbericht (TB)). Is used in
 * frontend logic and used as a medium between frontend and backend.
 * 
 * @author Mike Hukiewitz & Alex
 *
 */
public class ActivityReport implements Serializable
{

	private static final long	serialVersionUID	= 7682896069658320372L;

	private String				description;
	private String				identifier;
	private Date				date;
	private Integer				startTime;
	private Integer				duration;
	private Integer				breakTime;
	private Long				author;
	private Long				id;

	/**
	 * Initializes a new instance of the {@link ActivityReport} class with its
	 * fields set to null.
	 */
	public ActivityReport()
	{

	}

	/**
	 * Initializes a new instance of the {@link ActivityReport} class with the
	 * following values
	 * 
	 * @param description
	 *            the description of the report.
	 * @param identifier
	 *            the identifier string.
	 * @param date
	 *            the date of the report.
	 * @param startTime
	 *            the start time of the report.
	 * @param duration
	 *            the duration time of the report.
	 */
	public ActivityReport(String description, String identifier, Date date,
			int startTime, int duration, int breakTime)
	{
		this.setDescription(description);
		this.setIdentifier(identifier);
		this.setDate(date);
		this.setStartTime(startTime);
		this.setDuration(duration);
		this.setBreakTime(breakTime);
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getIdentifier()
	{
		return identifier;
	}

	public void setIdentifier(String identifier)
	{
		this.identifier = identifier;
	}

	public int getDuration()
	{
		return duration;
	}

	public void setDuration(int duration)
	{
		if (duration < 1 || startTime + duration > 1440)
			throw (new IllegalArgumentException());
		this.duration = duration;
	}

	public int getStartTime()
	{
		return startTime;
	}

	public void setStartTime(int startTime)
	{
		if (startTime >= 1440)
			throw (new IllegalArgumentException());

		this.startTime = startTime;
	}

	public int getEndTime()
	{
		return startTime + duration;
	}

	public void setEndTime(int endTime)
	{
		if (endTime - startTime <= 0)
			throw (new IllegalArgumentException());
		this.duration = endTime - startTime;
	}

	public Long getAuthor()
	{
		return author;
	}

	public void setAuthor(Long author)
	{
		if (author == null)
			throw (new IllegalArgumentException());
		this.author = author;
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	public void setDate(int year, int month, int day)
	{
		this.date = new Date(year, month, day);
	}

	public Integer getBreakTime()
	{
		return breakTime;
	}

	public void setBreakTime(Integer breakTime)
	{
		if (breakTime < 0)
			throw (new IllegalArgumentException());
		this.breakTime = breakTime;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

}