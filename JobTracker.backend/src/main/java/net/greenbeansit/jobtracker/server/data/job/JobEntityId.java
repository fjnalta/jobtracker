package net.greenbeansit.jobtracker.server.data.job;

import java.io.Serializable;

import net.greenbeansit.jobtracker.server.data.userJob.UserJobEntityId;

public class JobEntityId implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5692670503370732054L;
	private Integer				jobNr;
	private Integer				posNr;
	private String				desc;
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
	public String getDesc()
	{
		return desc;
	}
	public void setDesc(String desc)
	{
		this.desc = desc;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		else if (obj instanceof JobEntityId)
		{
			JobEntityId temp = (JobEntityId) obj;
			return this.jobNr.equals(temp.jobNr) && this.posNr.equals(temp.posNr);
		} else
			return false;
	}
	
	@Override
	public int hashCode()
	{
		return super.hashCode();
	}
}
