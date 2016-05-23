package net.greenbeansit.jobtracker.server.data.userJob;

import java.io.Serializable;

public class UserJobEntityId implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3667654994571278026L;
	private Integer				userId;
	private Integer				jobNr;
	private String				posNr;
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
	public String getPosNr()
	{
		return posNr;
	}
	public void setPosNr(String posNr)
	{
		this.posNr = posNr;
	}
}
