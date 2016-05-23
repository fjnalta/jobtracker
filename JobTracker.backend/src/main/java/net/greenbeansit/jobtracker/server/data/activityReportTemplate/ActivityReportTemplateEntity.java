package net.greenbeansit.jobtracker.server.data.activityReportTemplate;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "activity_report_template")
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
	
	public ActivityReportTemplateEntity()
	{
		
	}
	
	/**
	 * Constructor for a new template (and ID to be generated)
	 */
	public ActivityReportTemplateEntity(String name, String description, Integer taskId, Integer author)
	{
		this.name = name;
		this.description = description;
		this.taskId = taskId;
		this.author = author;
	}
	
	/**
	 * Constructor for an already existing template (used in backend)
	 */
	public ActivityReportTemplateEntity(Integer id, String name, String description, Integer taskId, Integer author)
	{
		this.id = id;
		this.name = name;
		this.description = description;
		this.taskId = taskId;
		this.author = author;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	 @Column(name = "name")
	 public String getName()
	 {
	 return name;
	 }
	
	 public void setName(String name)
	 {
	 this.name = name;
	 }

	@Column(name = "text")
	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
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

	@Column(name = "author")
	public Integer getAuthor()
	{
		return author;
	}

	public void setAuthor(Integer author)
	{
		this.author = author;
	}
}