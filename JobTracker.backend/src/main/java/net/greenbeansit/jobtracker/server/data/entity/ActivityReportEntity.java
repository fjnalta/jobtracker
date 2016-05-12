package net.greenbeansit.jobtracker.server.data.entity;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.print.attribute.standard.DateTimeAtCompleted;

@Entity
@Table(name = "ACTIVITY_REPORT")
public class ActivityReportEntity implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -178627731533874470L;

	@Id
	@GeneratedValue
	private Integer				id;

	private Integer				author;
	private String				text;
	private Date				beginDate;
	private Time				beginTime;
	private Date				endDate;
	private Time				endTime;
	private Integer				breakTime;
	private Integer				taskId;
	private Integer				jobNr;
	private Integer				posNr;

	public ActivityReportEntity()
	{

	}

	public ActivityReportEntity(Integer author, String text, Date beginDate,
			Time beginTime, Time endTime, Integer breakTime, Integer taskId,
			Integer jobNr, Integer posNr)
	{
		this.author = author;
		this.text = text;
		this.beginDate = beginDate;
		this.beginTime = beginTime;
		this.endDate = null;
		this.endTime = endTime;
		this.taskId = taskId;
		this.jobNr = jobNr;
		this.posNr = posNr;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	@Column(name = "AUTHOR")
	public Integer getAuthor()
	{
		return author;
	}

	public void setAuthor(Integer author)
	{
		this.author = author;
	}

	@Column(name = "TEXT")
	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	@Column(name = "BEGIN_DATE")
	public Date getBeginDate()
	{
		return beginDate;
	}

	public void setBeginDate(Date beginDate)
	{
		this.beginDate = beginDate;
	}

	@Column(name = "BEGIN_TIME")
	public Time getBeginTime()
	{
		return beginTime;
	}

	public void setBeginTime(Time beginTime)
	{
		this.beginTime = beginTime;
	}

	@Column(name = "END_DATE")
	public Date getEndDate()
	{
		return endDate;
	}

	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}

	@Column(name = "END_TIME")
	public Time getEndTime()
	{
		return endTime;
	}

	public void setEndTime(Time endTime)
	{
		this.endTime = endTime;
	}

	@Column(name = "TASK_ID")
	public Integer getTaskId()
	{
		return taskId;
	}

	public void setTaskId(Integer taskId)
	{
		this.taskId = taskId;
	}

	@Column(name = "BREAK_TIME")
	public Integer getBreakTime()
	{
		return breakTime;
	}

	public void setBreakTime(Integer breakTime)
	{
		this.breakTime = breakTime;
	}

	@Column(name = "JOB_NO")
	public Integer getJobNr()
	{
		return jobNr;
	}

	public void setJobNr(Integer jobNr)
	{
		this.jobNr = jobNr;
	}

	@Column(name = "POS_NO")
	public Integer getPosNr()
	{
		return posNr;
	}

	public void setPosNr(Integer posNr)
	{
		this.posNr = posNr;
	}

}
