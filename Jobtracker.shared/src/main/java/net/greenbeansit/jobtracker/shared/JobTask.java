package net.greenbeansit.jobtracker.shared;

import java.io.Serializable;

/**
 * Represents a JobTask
 * 
 * @author Philipp Minges
 */
public class JobTask implements Serializable
{
	private static final long serialVersionUID = 1834300319535257310L;
	
	private Integer id;
	private Integer jobId;
	private String name;
	
	/**
	 * Initializes a new instance of the {@link JobTask} class
	 * @param id the identifier of the jobId
	 * @param name the name of the Task
	 */
	public JobTask(Integer id, Integer jobId, String name)
	{
		this.id = id;
		this.jobId = jobId;
		this.name = name;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		if(id == null)
		{
			throw new IllegalArgumentException();
		}
		this.id = id;
	}

	public Integer getJobId()
	{
		return jobId;
	}

	public void setJobId(Integer jobId)
	{
		if(jobId == null)
		{
			throw new IllegalArgumentException();
		}
		this.jobId = jobId;
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
}
