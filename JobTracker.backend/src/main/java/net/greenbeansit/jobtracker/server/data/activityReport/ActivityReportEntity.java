package net.greenbeansit.jobtracker.server.data.activityReport;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * A class representing ActivityReport as entities in our database. Only used in
 * backend. Each entity class requires a standard constructor and
 * getters/setters for usage in Java Spring.
 * 
 * @author Mike Hukiewitz
 *
 */
@Entity
@Table(name = "activity_report")
public class ActivityReportEntity implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -178627731533874470L;

	@Id
	@GeneratedValue
	private Integer				id;

	@Column(name = "author")
	private Integer				author;
	@Column(name = "text")
	private String				text;
	@Column(name = "begin_date")
	private Date				beginDate;
	@Column(name = "begin_time")
	private Time				beginTime;
	@Column(name = "end_date")
	private Date				endDate;
	@Column(name = "end_time")
	private Time				endTime;
	@Column(name = "break_time")
	private Integer				breakTime;
	@Column(name = "task_id")
	private Integer				taskId;
	@Column(name = "job_no")
	private Integer				jobNo;
	@Column(name = "pos_no")
	private Integer				posNo;

	/**
	 * Standard constructor for internal purposes.
	 */
	public ActivityReportEntity()
	{
	}

	/**
	 * Creates a new {@link ActivityReportEntity}
	 * 
	 * @param author
	 *            id of its author
	 * @param text
	 *            the description as written by the author
	 * @param beginDate
	 *            date of the report
	 * @param beginTime
	 *            when it begun
	 * @param endTime
	 *            when it ended
	 * @param breakTime
	 *            how much time (in minutes) was spent as break time
	 * @param taskId
	 *            corresponding id for eventual JIRA compatibility
	 * @param jobNo
	 *            3 to 6 digits
	 * @param posNo
	 *            up to 3 digits
	 */
	public ActivityReportEntity(Integer author, String text, Date beginDate,
			Time beginTime, Time endTime, Integer breakTime, Integer taskId,
			Integer jobNo, Integer posNo)
	{
		this.author = author;
		this.text = text;
		this.beginDate = beginDate;
		this.beginTime = beginTime;
		this.endDate = null;
		this.endTime = endTime;
		this.taskId = taskId;
		this.jobNo = jobNo;
		this.posNo = posNo;
		this.breakTime = breakTime;
	}

	/**
	 * get the ID
	 * @return Integer value
     */
	public Integer getId()
	{
		return id;
	}

	/**
	 * set the ID
	 * @param id  ID Integer value
     */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * get the authorID
	 * @return Integer value
     */
	public Integer getAuthor()
	{
		return author;
	}

	/**
	 * set the author ID
	 * @param author Integer value
     */
	public void setAuthor(Integer author)
	{
		this.author = author;
	}

	/**
	 * get the report text
	 * @return String value
     */
	public String getText()
	{
		return text;
	}

	/**
	 * set the report text
	 * @param text String value
     */
	public void setText(String text)
	{
		this.text = text;
	}

	/**
	 * get the begin Date
	 * @return java.sql.Date object
     */
	public Date getBeginDate()
	{
		return beginDate;
	}

	/**
	 * set the begin Date
	 * @param beginDate java.sql.Date object
     */
	public void setBeginDate(Date beginDate)
	{
		this.beginDate = beginDate;
	}

	/**
	 * get the begin Time
	 * @return java.sql.time object
     */
	public Time getBeginTime()
	{
		return beginTime;
	}

	/**
	 * set the begin time
	 * @param beginTime java.sql.time object
     */
	public void setBeginTime(Time beginTime)
	{
		this.beginTime = beginTime;
	}

	/**
	 * get the end date
	 * @return java.sql.date object
     */
	public Date getEndDate()
	{
		return endDate;
	}

	/**
	 * set the end date
	 * @param endDate java.sql.date object
     */
	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}

	/**
	 * get the endTime
	 * @return java.sql.time object
     */
	public Time getEndTime()
	{
		return endTime;
	}

	/**
	 * set the end time
	 * @param endTime java.sql.time object
     */
	public void setEndTime(Time endTime)
	{
		this.endTime = endTime;
	}

	/**
	 * get the task ID
	 * @return Integer value
     */
	public Integer getTaskId()
	{
		return taskId;
	}

	/**
	 * set the task ID
	 * @param taskId Integer value
     */
	public void setTaskId(Integer taskId)
	{
		this.taskId = taskId;
	}

	/**
	 * get the break time in minutes
	 * @return Integer value
     */
	public Integer getBreakTime()
	{
		return breakTime;
	}

	/**
	 * set the break time in minutes
	 * @param breakTime Integer value
     */
	public void setBreakTime(Integer breakTime)
	{
		this.breakTime = breakTime;
	}

	/**
	 * get the JobNo
	 * @return Interger value
     */
	public Integer getJobNo()
	{
		return jobNo;
	}

	/**
	 * set the JobNo
	 * @param jobNo Integer value
     */
	public void setJobNo(Integer jobNo)
	{
		this.jobNo = jobNo;
	}

	/**
	 * get the PosNo
	 * @return Integer value
     */
	public Integer getPosNo()
	{
		return posNo;
	}

	/**
	 * set the posNo
	 * @param posNo Integer value
     */
	public void setPosNo(Integer posNo)
	{
		this.posNo = posNo;
	}

}
