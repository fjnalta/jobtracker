package net.greenbeansit.jobtracker.server.data.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_job")
public class UserJobEntity implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1371314200909587258L;

	private Integer				userId;
	private Integer				jobNr;
	private Integer				posNr;
	private Integer				role;


	@Column(name = "user_id")
	public Integer getUserId()
	{
		return userId;
	}

	public void setUserId(Integer userId)
	{
		this.userId = userId;
	}

	@Column(name = "job_no")
	public Integer getJobNr()
	{
		return jobNr;
	}

	public void setJobNr(Integer jobNr)
	{
		this.jobNr = jobNr;
	}

	@Column(name = "pos_no")
	public Integer getPosNr()
	{
		return posNr;
	}

	public void setPosNr(Integer posNr)
	{
		this.posNr = posNr;
	}

	@Column(name = "role")
	public Integer getRole()
	{
		return role;
	}

	public void setRole(Integer role)
	{
		this.role = role;
	}

}
