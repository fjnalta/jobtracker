package net.greenbeansit.jobtracker.server.data.job;

import java.io.Serializable;

/**
 * Serves as representation of the composite primary key of JobEntity.
 * @author Mike Hukiewitz
 *
 */
public class JobEntityId implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= -5692670503370732054L;
	private Integer				jobNr;
	private Integer				posNr;
	private String				desc;

	/**
	 * @return the job number
	 */
	public Integer getJobNr()
	{
		return jobNr;
	}

	/**
	 * set job number
	 * @param jobNr the Job number.
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
	 * @param posNr the position number.
	 */
	public void setPosNr(Integer posNr)
	{
		this.posNr = posNr;
	}

	/**
	 * @return the description
	 */
	public String getDesc()
	{
		return desc;
	}

	/**
	 * set description
	 * @param desc the description
	 */
	public void setDesc(String desc)
	{
		this.desc = desc;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
			return false;
		else if (obj instanceof JobEntityId)
		{
			JobEntityId temp = (JobEntityId) obj;
			return this.jobNr.equals(temp.jobNr)
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
