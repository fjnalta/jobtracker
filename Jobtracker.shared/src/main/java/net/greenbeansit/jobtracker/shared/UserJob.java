package net.greenbeansit.jobtracker.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
	 * @param jobNo jobNo of the job
	 * @param posNo posNo of the job
	 * @param role role in this job
	 */
	public UserJob(Integer userId, Integer jobNo, Integer posNo, Integer role)
	{
		this.userId = userId;
		this.jobNo = jobNo;
		this.posNo = posNo;
		this.role = role;
	}

	/**
	 * empty constructor for JSON parsing
	 */
	public UserJob()
	{

	}

	/**
	 * get userID
	 * @return the userID Integer
     */
	public Integer getUserId()
	{
		return userId;
	}

	/**
	 * set the userID
	 * @param userId Integer value
     */
	public void setUserId(Integer userId)
	{
		if (userId == null)
		{
			throw new IllegalArgumentException();
		}
		this.userId = userId;
	}

	/**
	 * get the jobNr
	 * @return Integer value
     */
	public Integer getJobNr()
	{
		return jobNo;
	}

	/**
	 * set the jobNr
	 * @param jobNo Integer value
     */
	public void setJobNr(Integer jobNo)
	{
		if (jobNo == null)
		{
			throw new IllegalArgumentException();
		}
		this.jobNo = jobNo;
	}

	/**
	 * get posNr
	 * @return Integer value
     */
	public Integer getPosNr()
	{
		return posNo;
	}

	/**
	 * set the posNr
	 * @param posNo Interger value
     */
	public void setPosNr(Integer posNo)
	{
		this.posNo = posNo;
	}

	/**
	 * get the role
	 * @return Integer value
     */
	public Integer getRole()
	{
		return role;
	}

	/**
	 * set the role in this job
	 * @param role Integer value
     */
	public void setRole(Integer role)
	{
		if (role == null)
		{
			throw new IllegalArgumentException();
		}
		this.role = role;
	}

	/**
	 * get the posNo
	 * @return Integer value
     */
	public Integer getPosNo()
	{
		return posNo;
	}

	/**
	 * set the posNo
	 *
	 * @param posNo Integer value
     */
	public void setPosNo(Integer posNo)
	{
		if (posNo == null)
		{
			throw new IllegalArgumentException();
		}
		this.posNo = posNo;
	}

	public boolean equals(Object obj)
	{
		if (obj == null)
			return false;
		else if (obj instanceof UserJob)
		{
			UserJob temp = (UserJob) obj;
			return this.userId.equals(temp.userId)
					&& this.jobNo.equals(temp.jobNo)
					&& this.posNo.equals(temp.posNo)
					&& this.role.equals(temp.role);
		} else
			return false;
	}
	
	@Override
    public int hashCode() {
        if(userId == null || jobNo == null || posNo == null || role == null)
        	return 0;
        List<Integer> ints = new ArrayList<Integer>();
        ints.add(userId);
        ints.add(jobNo);
        ints.add(posNo);
        ints.add(role);
        return ints.hashCode();
    }
}
