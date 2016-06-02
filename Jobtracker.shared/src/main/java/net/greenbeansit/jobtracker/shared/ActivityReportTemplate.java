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

	/**
	 * building constructor of {@link ActivityReportTemplate}
	 * @param name String name of the template
	 * @param text String text of the template
	 * @param taskId Integer task id for the template
     * @param author Integer authorID of this template
     */
	public ActivityReportTemplate(String name, String text, Integer taskId, Integer author) {
		this.name = name;
		this.text = text;
		this.taskId = taskId;
		this.author = author;
	}

	/**
	 * get the name of this template
	 * @return String value of name
     */
	public String getName()
	{
		return name;
	}

	/**
	 * set the namme of this template
	 * @param name String value of name
     */
	public void setName(String name)
	{
		if (name == "")
			throw (new IllegalArgumentException());
		this.name = name;
	}

	/**
	 * get the Text
	 * @return String value
     */
	public String getText()
	{
		return text;
	}

	/**
	 * set the text
	 * @param text String value
     */
	public void setText(String text)
	{
		this.text = text;
	}

	/**
	 * get the taskID
	 * @return Integer value
     */
	public Integer getTaskId()
	{
		return taskId;
	}

	/**
	 * set the taskID
	 * @param taskId Integer value
     */
	public void setTaskId(Integer taskId)
	{
		this.taskId = taskId;
	}

	/**
	 * get the authorID
	 * @return String value
     */
	public Integer getAuthor()
	{
		return author;
	}

	/**
	 * set the authorID
	 * @param author Integer value
     */
	public void setAuthor(Integer author)
	{
		this.author = author;
	}

	/**
	 * custom equals method
	 * @param obj Object to compare to
	 * @return Boolean value
     */
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
