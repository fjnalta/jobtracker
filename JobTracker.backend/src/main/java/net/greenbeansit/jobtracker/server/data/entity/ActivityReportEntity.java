package net.greenbeansit.jobtracker.server.data.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ACTIVITY_REPORT")
//TODO Mike: Implement NamedQueries for ActivityReport
//@NamedQueries( { @NamedQuery(name = "ActivityReportTemplate.findAll", query = "SELECT p FROM Person p"),
//@NamedQuery(name = "Person.findPerson", query = "SELECT p FROM Person p where p.name=:name and p.age=:age")
//})
public class ActivityReportEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -178627731533874470L;

	@Id
	@GeneratedValue
	private Integer id;

	private Integer	author;
	private String	text;
	private Date	beginDate;
	private Integer	beginTime;
	private Date	endDate;
	private Integer	endTime;
	private Integer	taskId;
	private Integer	jobId;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
	public Integer getBeginTime()
	{
		return beginTime;
	}

	public void setBeginTime(Integer beginTime)
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
	public Integer getEndTime()
	{
		return endTime;
	}

	public void setEndTime(Integer endTime)
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

	@Column(name = "JOB_ID")
	public Integer getJobId()
	{
		return jobId;
	}

	public void setJobId(Integer jobId)
	{
		this.jobId = jobId;
	}

}
