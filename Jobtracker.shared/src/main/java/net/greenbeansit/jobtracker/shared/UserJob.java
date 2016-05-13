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
	private Integer				userId;
	private Integer				jobNo;
	private Integer				posNo;
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
	public UserJob(Integer id, Integer jobId, Integer posNo, Integer role)
	{
		this.userId = userId;
		this.jobNr = jobNr;
		this.setPosNr(posNr);
		this.userId = id;
		this.jobNo = jobId;
		this.posNo = posNo;
		this.role = role;
	}
	
	public UserJob(){
		
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
		this.userId = id;
	}

	public Integer getJobNr()
	{
		return jobNr;
		return jobNo;
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
