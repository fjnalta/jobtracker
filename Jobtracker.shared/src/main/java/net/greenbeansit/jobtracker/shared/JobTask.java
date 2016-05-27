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
	 * Initializes a new instance of the {@link JobTask} class
	 * 
	 * @param id
	 *            unique id for the JobTask
	 * @param jobNr
	 *            the identifier of the jobNr
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

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		if (id == null)
		{
			throw new IllegalArgumentException();
		}
		this.id = id;
	}

	public Integer getJobNr()
	{
		return jobNr;
	}

	public void setJobNr(Integer jobNr)
	{
		if (jobNr == null)
		{
			throw new IllegalArgumentException();
		}
		this.jobNr = jobNr;
	}

	public Integer getPosNr()
	{
		return posNr;
	}

	public void setPosNr(Integer posNr)
	{
		this.posNr = posNr;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		if (name == "")
		{
			throw new IllegalArgumentException();
		}
		this.name = name;
	}
	
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		else if (obj instanceof JobTask)
		{
			JobTask temp = (JobTask) obj;
			return this.id.equals(temp.id);
		} else
			return false;
	}
}
