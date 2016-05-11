package net.greenbeansit.jobtracker.shared;

import java.io.Serializable;
import java.util.Date;

/**
 * Shared representation of an Activity (Taetigkeitsbericht (TB)). Is used in
 * frontend logic and used as a medium between frontend and backend.
 * 
 * @authorId Mike Hukiewitz & Alex
 *
 */
public class ActivityReport implements Serializable
{

	private static final long	serialVersionUID	= 7682896069658320372L;

	private Integer				id;
	private Integer				taskId;
	private Integer				jobNr;
	private Integer				jobPosNr;
	private Integer				authorId;
	
	private String 				jobDesc;
	private String				text;
	private Date				date;
	private Integer				startTime;
	private Integer				duration;
	private Integer				breakTime;

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
	 * @param id
	 *            id of this ActivityReport.
	 * @param taskId
	 *            id of the corresponding task.
	 * @param jobId
	 *            id of the corresponding job.
	 * @param authorId
	 *            of the author.
	 * @param text
	 *            the text of the report.
	 * @param date
	 *            the date of the report.
	 * @param startTime
	 *            the start time as minutes since 0 am. (1 = 0:01)
	 * @param duration
	 *            the duration in minutes.
	 * @param breakTime
	 *            duration of breaks in minutes.
	 */
	public ActivityReport(Integer id, Integer taskId, Integer jobNr,
			Integer authorId, String text, Date date, Integer startTime,
			Integer duration, Integer breakTime)
	{
		this.setId(id);
		this.setTaskId(taskId);
		this.setJobNr(jobNr);
		this.setAuthor(authorId);
		this.setText(text);
		this.setDate(date);
		this.setStartTime(startTime);
		this.setDuration(duration);
		this.setBreakTime(breakTime);
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
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

	public Integer getAuthor()
	{
		return authorId;
	}

	public void setAuthor(Integer authorId)
	{
		if (authorId == null)
			throw (new IllegalArgumentException());
		this.authorId = authorId;
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	@SuppressWarnings("deprecation")
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

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getTaskId()
	{
		return taskId;
	}

	public void setTaskId(Integer taskId)
	{
		this.taskId = taskId;
	}

	public String getJobDesc() {
		return jobDesc;
	}

	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}

	public Integer getJobPosNr() {
		return jobPosNr;
	}

	public void setJobPosNr(Integer jobPosNr) {
		this.jobPosNr = jobPosNr;
	}

	public Integer getJobNr() {
		return jobNr;
	}

	public void setJobNr(Integer jobNr) {
		this.jobNr = jobNr;
	}

}