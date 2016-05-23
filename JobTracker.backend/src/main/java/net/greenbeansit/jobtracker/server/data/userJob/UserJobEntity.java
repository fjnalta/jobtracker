package net.greenbeansit.jobtracker.server.data.userJob;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import net.greenbeansit.jobtracker.server.data.userJob.UserJobEntityId;

@Entity @IdClass(UserJobEntityId.class)
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


	@Id @Column(name = "user_id", nullable = false)
	public Integer getUserId()
	{
		return userId;
	}

	public void setUserId(Integer userId)
	{
		this.userId = userId;
	}

	@Id @Column(name = "job_no", nullable = false)
	public Integer getJobNr()
	{
		return jobNr;
	}

	public void setJobNr(Integer jobNr)
	{
		this.jobNr = jobNr;
	}

	@Id @Column(name = "pos_no", nullable = false)
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
