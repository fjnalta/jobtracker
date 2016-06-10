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

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -1176061103569100845L;
	private Integer				id;
	private Integer				taskId;
	private Integer				jobNr;
	private Integer				posNr;
	private Integer				authorId;

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
	 * @param jobNr
	 *            jobNr of the corresponding job.
	 * @param posNr
	 *            posNr of the corresponding job
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
			Integer posNr, Integer authorId, String text, Date date,
			Integer startTime, Integer duration, Integer breakTime)
	{
		this.setId(id);
		this.setTaskId(taskId);
		this.setJobNr(jobNr);
		this.setPosNr(posNr);
		this.setAuthor(authorId);
		this.setText(text);

		this.setDate(date);
		this.setStartTime(startTime);
		this.setDuration(duration);
		this.setBreakTime(breakTime);
	}

	/**
	 * Method for getting the text attribute
	 * 
	 * @return String text of the activity report
	 */
	public String getText()
	{
		return text;
	}

	/**
	 * Method for setting the text attribute
	 * 
	 * @param text
	 *            text to set for the ActivityReport
	 */
	public void setText(String text)
	{
		this.text = text;
	}

	/**
	 * Method for getting the duration attribute in minutes
	 * 
	 * @return Integer of the duration attribute in minutes
	 */
	public Integer getDuration()
	{
		return duration;
	}

	/**
	 * Set the duration parameter with an Integer in minutes
	 * 
	 * @param duration
	 *            Integer value of the duration in minutes to be set
	 */
	public void setDuration(Integer duration)
	{
		// if (duration < 1 || this.startTime + duration > 1440)
		// throw (new IllegalArgumentException());
		this.duration = duration;
	}

	/**
	 *
	 * @return the Interger value of the attribute startTime in minutes
	 */
	public Integer getStartTime()
	{
		return startTime;
	}

	/**
	 * set the value of the attribute startTime in minutes
	 * 
	 * @param startTime
	 *            the Integer value to be set
	 */
	public void setStartTime(Integer startTime)
	{
		this.startTime = startTime;
	}

	/**
	 * get the Integer value of the endTime. This value is calculated out of the
	 * startTime and the durartion value
	 * 
	 * @return Integer value of the endTime in minutes
	 */
	public Integer getEndTime()
	{
		return startTime + duration;
	}

	/**
	 * set the endTime of the report in minutes
	 * 
	 * @param endTime
	 *            Integer value in minutes of the endTime
	 */
	public void setEndTime(Integer endTime)
	{
		if (this.startTime != null)
			this.duration = endTime - this.startTime;
		// if (endTime - startTime <= 0)
		// throw (new IllegalArgumentException());
	}

	/**
	 * method for getting the authorID
	 * 
	 * @return Integer value of authorId
	 */
	public Integer getAuthor()
	{
		return authorId;
	}

	/**
	 * set the corresponding author with the authorID
	 * 
	 * @param authorId
	 *            Integer value of an athorID to be set
	 */
	public void setAuthor(Integer authorId)
	{
		if (authorId == null)
			throw (new IllegalArgumentException());
		this.authorId = authorId;
	}

	/**
	 * get the date of the report
	 * 
	 * @return Date object with the date of the report
	 */
	public Date getDate()
	{
		return date;
	}

	/**
	 * Set the date of the report
	 * 
	 * @param date
	 *            Date object with the date, the report will be saved to
	 */
	public void setDate(Date date)
	{
		this.date = date;
	}

	/**
	 * set Date with integer values
	 * 
	 * @param year
	 *            Integer year value
	 * @param month
	 *            Integer month value
	 * @param day
	 *            Integer day value
	 */
	@SuppressWarnings("deprecation")
	public void setDate(Integer year, Integer month, Integer day)
	{
		this.date = new Date(year - 1900, month - 1, day);
	}

	/**
	 * get the breaktime in minutes
	 * 
	 * @return Integer value of the breakTime in minutes
	 */
	public Integer getBreakTime()
	{
		return breakTime;
	}

	/**
	 * set the breakTime in minutes
	 * 
	 * @param breakTime
	 *            Integer value of the breakTime in minutes
	 */
	public void setBreakTime(Integer breakTime)
	{
		// if (breakTime < 0)
		// throw (new IllegalArgumentException());
		this.breakTime = breakTime;
	}

	/**
	 * get the ID of the report
	 * 
	 * @return Integer value of the id of this report
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * set the ID of the report
	 * 
	 * @param id
	 *            the Integer value of the ID
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * get the corresponding TaskID which is associated with this report
	 * 
	 * @return Interger taskID
	 */
	public Integer getTaskId()
	{
		return taskId;
	}

	/**
	 * set the corresponding taskID to this report
	 * 
	 * @param taskId
	 *            Integer value of the taskID
	 */
	public void setTaskId(Integer taskId)
	{
		this.taskId = taskId;
	}

	/**
	 * get the posNr value corresponding with this report
	 * 
	 * @return Integer value of the posNr
	 */
	public Integer getPosNr()
	{
		return posNr;
	}

	/**
	 * set the corresponding posNr for this report
	 * 
	 * @param jobPosNr
	 *            Integer value of the posNr
	 */
	public void setPosNr(Integer jobPosNr)
	{
		this.posNr = jobPosNr;
	}

	/**
	 * get the corresponding jobNr of this report
	 * 
	 * @return Integer value of the corresponding jobNr
	 */
	public Integer getJobNr()
	{
		return jobNr;
	}

	/**
	 * set the corrresponding jobNr for this report
	 * 
	 * @param jobNr
	 *            Interger value of the corresponding jobNr
	 */
	public void setJobNr(Integer jobNr)
	{
		this.jobNr = jobNr;
	}

	/**
	 * To sting method, returns the {id + taskid + jobNr + posNr + authorId +
	 * text + date + startTime + duration+ breakTime }
	 * 
	 * @return String object with all values of the report
	 */
	@Override
	public String toString()
	{
		return "{id:" + id + ", taskId:" + taskId + ", jobNr:" + jobNr
				+ ", posNr:" + posNr + ", authorId:" + authorId + ", text:"
				+ text + ", date:" + date.toString() + ", startTime:"
				+ startTime + ", duration:" + duration + ", breakTime:"
				+ breakTime + "}";
	}

	public boolean equals(Object obj)
	{
		if (obj == null)
			return false;
		else if (obj instanceof ActivityReport)
		{
			ActivityReport temp = (ActivityReport) obj;
			return this.id.equals(temp.id);
		} else
			return false;
	}

	@Override
	public int hashCode()
	{
		return id == null ? 0 : id.hashCode();
	}

}