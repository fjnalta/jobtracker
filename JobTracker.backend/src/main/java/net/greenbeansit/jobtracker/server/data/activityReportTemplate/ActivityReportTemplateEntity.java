package net.greenbeansit.jobtracker.server.data.activityReportTemplate;

import java.io.Serializable;

import javax.persistence.*;

/**
 * A class representing ActivityReportTemplate as entities in our database. Only
 * used in backend. Each entity class requires a standard constructor and
 * getters/setters for usage in Java Spring.
 * 
 * @author Mike Hukiewitz & Philipp Minges
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
	@Column(name = "job_no")
	private Integer				jobNo;
	@Column(name = "pos_no")
	private Integer				posNo;

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
	@Deprecated
	public ActivityReportTemplateEntity(String name, String text,
			Integer taskId, Integer author)
	{
		this.name = name;
		this.text = text;
		this.taskId = taskId;
		this.author = author;
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
	 * @param jobNr
	 *            3 to 6 digits
	 * @param posNr
	 *            up to 3 digits
	 */
	public ActivityReportTemplateEntity(String name, String text,
			Integer taskId, Integer author, Integer jobNr, Integer posNr)
	{
		this.name = name;
		this.text = text;
		this.taskId = taskId;
		this.author = author;
		this.jobNo = jobNr;
		this.posNo = posNr;
	}

	/**
	 * Gets the Name of the {@link ActivityReportTemplateEntity}
	 * 
	 * @return the name.
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Sets the Name of the {@link ActivityReportTemplateEntity}
	 * 
	 * @param name
	 *            the Name of the {@link ActivityReportTemplateEntity}
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Gets the Text of the {@link ActivityReportTemplateEntity}
	 * 
	 * @return the Text of the {@link ActivityReportTemplateEntity}
	 */
	public String getText()
	{
		return text;
	}

	/**
	 * Sets the Text of the {@link ActivityReportTemplateEntity}
	 * 
	 * @param text
	 *            the Text to be inserted into
	 *            {@link ActivityReportTemplateEntity}
	 */
	public void setText(String text)
	{
		this.text = text;
	}

	/**
	 * Gets the Task id of the {@link ActivityReportTemplateEntity}
	 * 
	 * @return the Task id of the {@link ActivityReportTemplateEntity}
	 */
	public Integer getTaskId()
	{
		return taskId;
	}

	/**
	 * Sets the Task id of the {@link ActivityReportTemplateEntity}
	 * 
	 * @param taskId
	 *            the Task id of the {@link ActivityReportTemplateEntity}
	 */
	public void setTaskId(Integer taskId)
	{
		this.taskId = taskId;
	}

	/**
	 * Gets the Author of the {@link ActivityReportTemplateEntity}
	 * 
	 * @return the Author of the {@link ActivityReportTemplateEntity}
	 */
	public Integer getAuthor()
	{
		return author;
	}

	/**
	 * Sets the Author of the {@link ActivityReportTemplateEntity}
	 * 
	 * @param author
	 *            the Author of the {@link ActivityReportTemplateEntity}
	 */
	public void setAuthor(Integer author)
	{
		this.author = author;
	}

	/**
	 * Gets the job number
	 * 
	 * @return the job number
	 */
	public Integer getJobNo()
	{
		return jobNo;
	}

	/**
	 * Sets the job number
	 * 
	 * @param jobNo
	 *            3 to 6 digits
	 */
	public void setJobNo(Integer jobNo)
	{
		this.jobNo = jobNo;
	}

	/**
	 * Gets the position number
	 * 
	 * @return the position number
	 */
	public Integer getPosNo()
	{
		return posNo;
	}

	/**
	 * Sets the position number
	 * 
	 * @param posNo
	 *            up to 3 digits
	 */
	public void setPosNo(Integer posNo)
	{
		this.posNo = posNo;
	}

	public boolean equals(Object obj)
	{
		if (obj == null)
			return false;
		else if (obj instanceof ActivityReportTemplateEntity)
		{
			ActivityReportTemplateEntity temp = (ActivityReportTemplateEntity) obj;
			return this.author.equals(temp.author)
					&& this.name.equals(temp.name);
		} else
			return false;
	}

	@Override
	public int hashCode()
	{
		if (author == null || name == null)
			return 0;
		String hashString = author + name;
		return hashString.hashCode();
	}

}
