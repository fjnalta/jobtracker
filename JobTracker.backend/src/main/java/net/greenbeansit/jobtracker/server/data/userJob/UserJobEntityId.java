package net.greenbeansit.jobtracker.server.data.userJob;

import java.io.Serializable;

import net.greenbeansit.jobtracker.shared.Job;

public class UserJobEntityId implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3667654994571278026L;
	private Integer				userId;
	private Integer				jobNr;
	private Integer				posNr;
	public Integer getUserId()
	{
		return userId;
	}
	public void setUserId(Integer userId)
	{
		this.userId = userId;
	}
	public Integer getJobNr()
	{
		return jobNr;
	}
	public void setJobNr(Integer jobNr)
	{
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
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		else if (obj instanceof UserJobEntityId)
		{
			UserJobEntityId temp = (UserJobEntityId) obj;
			return this.userId.equals(temp.userId) && this.jobNr.equals(temp.jobNr) && this.posNr.equals(temp.posNr);
		} else
			return false;
	}
	
	@Override
	public int hashCode()
	{
		return super.hashCode();
	}
}
