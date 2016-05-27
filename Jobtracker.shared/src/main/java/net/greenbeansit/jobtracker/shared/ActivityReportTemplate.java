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

	private String				name;
	private String				text;
	private Integer				taskId;
	private Integer				author;

	/**
	 * Initializes a new instance of the {@link ActivityReportTemplate} class.
	 */
	public ActivityReportTemplate()
	{

	}

	public ActivityReportTemplate(String name, String text, Integer taskId, Integer author) {
		this.name = name;
		this.text = text;
		this.taskId = taskId;
		this.author = author;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		if (name == "")
			throw (new IllegalArgumentException());
		this.name = name;
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
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
	
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		else if (obj instanceof ActivityReportTemplate)
		{
			ActivityReportTemplate temp = (ActivityReportTemplate) obj;
			return this.author.equals(temp.author) && this.name.equals(temp.name);
		} else
			return false;
	}
}
