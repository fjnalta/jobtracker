package net.greenbeansit.jobtracker.shared;

import java.io.Serializable;

/**
 * Represents a JobTask
 * 
 * @author Philipp Minges
 */
public class JobTask implements Serializable
{
	private static final long	serialVersionUID	= 1834300319535257310L;

	private Integer				id;
	private Integer				jobNr;
	private Integer				posNr;
	private String				name;

	/**
	 * Empty Constructor for internal purposes
	 */
	public JobTask()
	{

	}

	/**
	 * Initializes a new instance of the {@link JobTask} class
	 * 
	 * @param id
	 *            unique id for the JobTask
	 * @param jobNr
	 *            the identifier of the jobNr
	 * @param posNr
	 *            the position number.
	 * @param name
	 *            the name of the Task
	 */
	public JobTask(Integer id, Integer jobNr, Integer posNr, String name)
	{
		this.id = id;
		this.jobNr = jobNr;
		this.posNr = posNr;
		this.name = name;
	}

	/**
	 * Gets the {@link JobTask} id.
	 * 
	 * @return the id.
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Sets the {@link JobTask} id.
	 * 
	 * @param id
	 *            the id.
	 */
	public void setId(Integer id)
	{
		if (id == null)
		{
			throw new IllegalArgumentException();
		}
		this.id = id;
	}

	/**
	 * Gets the Job number.
	 * 
	 * @return the Job Number.
	 */
	public Integer getJobNr()
	{
		return jobNr;
	}

	/**
	 * Sets the Job number.
	 * 
	 * @param jobNr
	 *            the Job number.
	 */
	public void setJobNr(Integer jobNr)
	{
		if (jobNr == null)
		{
			throw new IllegalArgumentException();
		}
		this.jobNr = jobNr;
	}

	/**
	 * Gets the position Number.
	 * 
	 * @return the Position number.
	 */
	public Integer getPosNr()
	{
		return posNr;
	}

	/**
	 * Sets the position Number.
	 * 
	 * @param posNr
	 *            the position number.
	 */
	public void setPosNr(Integer posNr)
	{
		this.posNr = posNr;
	}

	/**
	 * Gets the JobTask name.
	 * 
	 * @return the Name.
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Sets the JobTask name.
	 * 
	 * @param name
	 *            the Name.
	 */
	public void setName(String name)
	{
		if (name == "")
		{
			throw new IllegalArgumentException();
		}
		this.name = name;
	}

	public boolean equals(Object obj)
	{
		if (obj == null)
			return false;
		else if (obj instanceof JobTask)
		{
			JobTask temp = (JobTask) obj;
			return this.id.equals(temp.id);
		} else
			return false;
	}

	@Override
	public int hashCode()
	{
		return id == null ? 0 : id.hashCode();
	}
}
