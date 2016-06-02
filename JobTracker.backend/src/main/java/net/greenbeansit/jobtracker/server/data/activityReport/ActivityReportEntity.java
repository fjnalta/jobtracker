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

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getAuthor()
	{
		return author;
	}

	public void setAuthor(Integer author)
	{
		this.author = author;
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public Date getBeginDate()
	{
		return beginDate;
	}

	public void setBeginDate(Date beginDate)
	{
		this.beginDate = beginDate;
	}

	public Time getBeginTime()
	{
		return beginTime;
	}

	public void setBeginTime(Time beginTime)
	{
		this.beginTime = beginTime;
	}

	public Date getEndDate()
	{
		return endDate;
	}

	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}

	public Time getEndTime()
	{
		return endTime;
	}

	public void setEndTime(Time endTime)
	{
		this.endTime = endTime;
	}

	public Integer getTaskId()
	{
		return taskId;
	}

	public void setTaskId(Integer taskId)
	{
		this.taskId = taskId;
	}

	public Integer getBreakTime()
	{
		return breakTime;
	}

	public void setBreakTime(Integer breakTime)
	{
		this.breakTime = breakTime;
	}

	public Integer getJobNo()
	{
		return jobNo;
	}

	public void setJobNo(Integer jobNo)
	{
		this.jobNo = jobNo;
	}

	public Integer getPosNo()
	{
		return posNo;
	}

	public void setPosNo(Integer posNo)
	{
		this.posNo = posNo;
	}

}
