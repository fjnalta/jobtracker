package net.greenbeansit.jobtracker.shared;

import java.io.Serializable;

/**
 * Represents a template for an {@link ActivityReport}.
 * 
 * @author Max Blatt
 */
public class ActivityReportTemplate implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1035084213210834537L;
	/**
	 * 
	 */

	private String				templateName;
	private String				description;
	private Integer				id;
	private Integer				taskId;
	private Integer				author;

	/**
	 * Initializes a new instance of the {@link ActivityReportTemplate} class.
	 */
	public ActivityReportTemplate()
	{

	}

	public ActivityReportTemplate(String templateName, String description, Integer id, Integer taskId, Integer author) {
		this.templateName = templateName;
		this.description = description;
		this.id = id;
		this.taskId = taskId;
		this.author = author;
	}



	public String getTemplateName()
	{
		return templateName;
	}

	public void setTemplateName(String templateName)
	{
		if (templateName == "")
			throw (new IllegalArgumentException());
		this.templateName = templateName;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Integer getTaskId()
	{
		return taskId;
	}

	public void setTaskId(Integer taskId)
	{
		this.taskId = taskId;
	}

	public Integer getAuthor()
	{
		return author;
	}

	public void setAuthor(Integer author)
	{
		this.author = author;
	}
}
