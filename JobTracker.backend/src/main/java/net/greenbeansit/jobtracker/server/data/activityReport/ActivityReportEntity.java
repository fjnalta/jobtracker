package net.greenbeansit.jobtracker.server.data.activityReport;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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

	@Column(name = "author")
	public Integer getAuthor()
	{
		return author;
	}

	public void setAuthor(Integer author)
	{
		this.author = author;
	}

	@Column(name = "text")
	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	@Column(name = "begin_date")
	public Date getBeginDate()
	{
		return beginDate;
	}

	public void setBeginDate(Date beginDate)
	{
		this.beginDate = beginDate;
	}

	@Column(name = "begin_time")
	public Time getBeginTime()
	{
		return beginTime;
	}

	public void setBeginTime(Time beginTime)
	{
		this.beginTime = beginTime;
	}

	@Column(name = "end_date")
	public Date getEndDate()
	{
		return endDate;
	}

	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}

	@Column(name = "end_time")
	public Time getEndTime()
	{
		return endTime;
	}

	public void setEndTime(Time endTime)
	{
		this.endTime = endTime;
	}

	@Column(name = "task_id")
	public Integer getTaskId()
	{
		return taskId;
	}

	public void setTaskId(Integer taskId)
	{
		this.taskId = taskId;
	}

	@Column(name = "break_time")
	public Integer getBreakTime()
	{
		return breakTime;
	}

	public void setBreakTime(Integer breakTime)
	{
		this.breakTime = breakTime;
	}

	@Column(name = "job_no")
	public Integer getJobNr()
	{
		return jobNr;
	}

	public void setJobNr(Integer jobNr)
	{
		this.jobNr = jobNr;
	}

	@Column(name = "pos_no")
	public Integer getPosNr()
	{
		return posNr;
	}

	public void setPosNr(Integer posNr)
	{
		this.posNr = posNr;
	}

}
