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
	private Integer				jobNr;
	private Integer				posNr;

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
	@Deprecated
	public ActivityReportTemplate(String name, String text, Integer taskId, Integer author) {
		this.name = name;
		this.text = text;
		this.taskId = taskId;
		this.author = author;
	}
	
	/**
	 * building constructor of {@link ActivityReportTemplate}
	 * @param name String name of the template
	 * @param text String text of the template
	 * @param taskId Integer task id for the template
     * @param author Integer authorID of this template
     */
	public ActivityReportTemplate(String name, String text, Integer taskId, Integer author, Integer jobNr, Integer posNr) {
		this.name = name;
		this.text = text;
		this.taskId = taskId;
		this.author = author;
		this.jobNr = jobNr;
		this.posNr = posNr;
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
	 * set the name of this template
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
	 * get the job number
	 * @return Integer value (3 to 6 digits)
	 */
	public Integer getJobNr()
	{
		return jobNr;
	}

	/**
	 * set the job number
	 * @param jobNr Integer value (3 to 6 digits)
	 */
	public void setJobNr(Integer jobNr)
	{
		this.jobNr = jobNr;
	}

	/**
	 * get the position number
	 * @return Integer value (up to 3 digits)
	 */
	public Integer getPosNr()
	{
		return posNr;
	}

	/**
	 * set the position number
	 * @param posNr Integer value (up to 3 digits)
	 */
	public void setPosNr(Integer posNr)
	{
		this.posNr = posNr;
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
	
	@Override
    public int hashCode() {
		if(author == null || name == null)
			return 0;
		String hashString = author + name;
        return hashString.hashCode();
    }
}
