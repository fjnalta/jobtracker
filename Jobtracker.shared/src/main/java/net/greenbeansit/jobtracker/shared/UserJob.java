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

	private Integer				userId;
	private Integer				jobNr;
	private Integer				posNr;
	private Integer				role;

	/**
	 * Initializes a new instance of the {@link UserJob} class
	 * 
	 * @param userId
	 *            the unique identifier
	 * @param jobNr
	 * @param role
	 */
	public UserJob(Integer userId, Integer jobNr, Integer posNr, Integer role)
	{
		this.userId = userId;
		this.jobNr = jobNr;
		this.setPosNr(posNr);
		this.role = role;
	}

	public Integer getUserId()
	{
		return userId;
	}

	public void setUserId(Integer userId)
	{
		if (userId == null)
		{
			throw new IllegalArgumentException();
		}
		this.userId = userId;
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
