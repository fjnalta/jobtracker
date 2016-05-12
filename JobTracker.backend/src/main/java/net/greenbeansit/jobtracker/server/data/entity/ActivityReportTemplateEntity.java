package net.greenbeansit.jobtracker.server.data.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ACTIVITY_REPORT_TEMPLATE")
public class ActivityReportTemplateEntity implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -804679538322787505L;

	@Id
	@GeneratedValue
	private Integer				id;

	private String				name;
	private String				description;
	private Integer				taskId;
	private Integer				author;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	 @Column(name = "NAME")
	 public String getName()
	 {
	 return name;
	 }
	
	 public void setName(String name)
	 {
	 this.name = name;
	 }

	@Column(name = "TEXT")
	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
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

	@Column(name = "AUTHOR")
	public Integer getAuthor()
	{
		return author;
	}

	public void setAuthor(Integer author)
	{
		this.author = author;
	}
}
