package net.greenbeansit.jobtracker.server.data.userJob;

import java.io.Serializable;

/**
 * Serves as representation of the composite primary key of UserJobEntity.
 * @author Mike Hukiewitz
 *
 */
public class UserJobEntityId implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= -3667654994571278026L;
	private Integer				userId;
	private Integer				jobNr;
	private Integer				posNr;

	/**
	 * @return the user ID
	 */
	public Integer getUserId()
	{
		return userId;
	}

	/**
	 * set user ID
	 * @param userId
	 */
	public void setUserId(Integer userId)
	{
		this.userId = userId;
	}

	/**
	 * @return the job number
	 */
	public Integer getJobNr()
	{
		return jobNr;
	}

	/**
	 * set job number
	 * @param jobNr
	 */
	public void setJobNr(Integer jobNr)
	{
		this.jobNr = jobNr;
	}

	/**
	 * @return the position number
	 */
	public Integer getPosNr()
	{
		return posNr;
	}

	/**
	 * set position number
	 * @param posNr
	 */
	public void setPosNr(Integer posNr)
	{
		this.posNr = posNr;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
			return false;
		else if (obj instanceof UserJobEntityId)
		{
			UserJobEntityId temp = (UserJobEntityId) obj;
			return this.userId.equals(temp.userId)
					&& this.jobNr.equals(temp.jobNr)
					&& this.posNr.equals(temp.posNr);
		} else
			return false;
	}

	@Override
	public int hashCode()
	{
		return super.hashCode();
	}
}
