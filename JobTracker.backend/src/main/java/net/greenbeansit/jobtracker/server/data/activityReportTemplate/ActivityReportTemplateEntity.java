package net.greenbeansit.jobtracker.server.data.activityReportTemplate;

import java.io.Serializable;

import javax.persistence.*;

/**
 * A class representing ActivityReportTemplate as entities in our database. Only
 * used in backend. Each entity class requires a standard constructor and
 * getters/setters for usage in Java Spring.
 * 
 * @author Mike Hukiewitz
 *
 */
@Entity
@IdClass(ActivityReportTemplateEntityId.class)
@Table(name = "activity_report_template")
public class ActivityReportTemplateEntity implements Serializable
{

	/**
	 *
	 */
	private static final long	serialVersionUID	= -804679538322787505L;

	@Id
	@Column(name = "author", nullable = false)
	private Integer				author;
	@Id
	@Column(name = "name", nullable = false)
	private String				name;
	@Column(name = "text")
	private String				text;
	@Column(name = "taskId")
	private Integer				taskId;

	/**
	 * Standard constructor for internal purposes.
	 */
	public ActivityReportTemplateEntity()
	{

	}

	/**
	 * Creates a new {@link ActivityReportTemplateEntity}.
	 * 
	 * @param name
	 *            name given by the author
	 * @param text
	 *            description as written by the author
	 * @param taskId
	 *            corresponding id for eventual JIRA compatibility
	 * @param author
	 *            id of the author
	 */
	public ActivityReportTemplateEntity(String name, String text,
			Integer taskId, Integer author)
	{
		this.name = name;
		this.text = text;
		this.taskId = taskId;
		this.author = author;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * set name
	 * @param name
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the description text
	 */
	public String getText()
	{
		return text;
	}

	/**
	 * set description text
	 * @param text
	 */
	public void setText(String text)
	{
		this.text = text;
	}

	/**
	 * @return the task ID
	 */
	public Integer getTaskId()
	{
		return taskId;
	}

	/**
	 * set task ID
	 * @param taskId
	 */
	public void setTaskId(Integer taskId)
	{
		this.taskId = taskId;
	}

	/**
	 * @return the author
	 */
	public Integer getAuthor()
	{
		return author;
	}

	/**
	 * set author
	 * @param author
	 */
	public void setAuthor(Integer author)
	{
		this.author = author;
	}
}
