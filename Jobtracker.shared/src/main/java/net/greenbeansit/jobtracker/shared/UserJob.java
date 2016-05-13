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
	 * @param userId
	 *            the unique identifier
	 * @param jobNo
	 * @param posNo
	 * @param role
	 */
	public UserJob(Integer userId, Integer jobNo, Integer posNo, Integer role)
	{
		this.userId = userId;
		this.jobNo = jobNo;
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
	}

	public Integer getJobNr()
	{
		return jobNo;
	}

	public void setJobNr(Integer jobNo)
	{
		if (jobNo == null)
		{
			throw new IllegalArgumentException();
		}
		this.jobNo = jobNo;
	}

	public Integer getPosNr()
	{
		return posNo;
	}

	public void setPosNr(Integer posNo)
	{
		this.posNo = posNo;
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
