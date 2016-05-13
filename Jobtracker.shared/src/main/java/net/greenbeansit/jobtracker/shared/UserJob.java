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
	private Integer				jobNo;
	private Integer				posNo;
	private Integer				role;

	/**
	 * Initializes a new instance of the {@link UserJob} class
	 * 
	 * @param id
	 *            the unique identifier
	 * @param jobId
	 * @param role
	 */
	public UserJob(Integer id, Integer jobId, Integer posNo, Integer role)
	{
		this.userId = id;
		this.jobNo = jobId;
		this.posNo = posNo;
		this.role = role;
	}

	public Integer getId()
	{
		return userId;
	}

	public void setId(Integer id)
	{
		if (id == null)
		{
			throw new IllegalArgumentException();
		}
		this.userId = id;
	}

	public Integer getJobId()
	{
		return jobNo;
	}

	public void setJobId(Integer jobId)
	{
		if (jobId == null)
		{
			throw new IllegalArgumentException();
		}
		this.jobNo = jobId;
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
	
	public Integer getPosNo()
	{
		return posNo;
	}

	public void setPosNo(Integer posNo)
	{
		if (posNo == null)
		{
			throw new IllegalArgumentException();
		}
		this.posNo = posNo;
	}
}
