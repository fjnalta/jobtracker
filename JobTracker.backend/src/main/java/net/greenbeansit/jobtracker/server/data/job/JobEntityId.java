package net.greenbeansit.jobtracker.server.data.job;

import java.io.Serializable;

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
}
