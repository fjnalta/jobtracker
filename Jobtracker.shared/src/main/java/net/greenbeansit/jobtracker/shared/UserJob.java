package net.greenbeansit.jobtracker.shared;

import java.io.Serializable;

/**
 * Represents a UserJob
 * 
 * @author Philipp Minges
 */
public class UserJob implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -6327917752248682924L;

	private Integer				id;
	private Integer				jobId;
	private Integer				role;

	/**
	 * Initializes a new instance of the {@link UserJob} class
	 * 
	 * @param id
	 *            the unique identifier
	 * @param jobId
	 * @param role
	 */
	public UserJob(Integer id, Integer jobId, Integer role)
	{
		this.id = id;
		this.jobId = jobId;
		this.role = role;
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

	public Integer getJobId()
	{
		return jobId;
	}

	public void setJobId(Integer jobId)
	{
		if (jobId == null)
		{
			throw new IllegalArgumentException();
		}
		this.jobId = jobId;
	}

	public Integer getRole()
	{
		return role;
	}

	public void setRole(Integer role)
	{
		if (role == null)
		{
			throw new IllegalArgumentException();
		}
		this.role = role;
	}
}
