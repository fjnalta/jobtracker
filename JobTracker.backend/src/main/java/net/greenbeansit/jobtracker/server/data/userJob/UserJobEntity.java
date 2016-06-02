package net.greenbeansit.jobtracker.server.data.userJob;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import net.greenbeansit.jobtracker.server.data.userJob.UserJobEntityId;

/**
 * A class representing UserJob as entities in our database. Only used in
 * backend. Each entity class requires a standard constructor and
 * getters/setters for usage in Java Spring.
 * 
 * This class is responsible for mapping users to jobs with their role in it.
 * 
 * @author Mike Hukiewitz
 *
 */
@Entity
@IdClass(UserJobEntityId.class)
@Table(name = "user_job")
public class UserJobEntity implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1371314200909587258L;

	@Id
	@Column(name = "user_id", nullable = false)
	private Integer				userId;
	@Id
	@Column(name = "job_no", nullable = false)
	private Integer				jobNr;
	@Id
	@Column(name = "pos_no", nullable = false)
	private Integer				posNr;
	@Column(name = "role")
	private Integer				role;

	/**
	 * Standard constructor for internal purposes.
	 */
	public UserJobEntity()
	{
	}
	
	/**
	 * Creates a new {@link UserJobEntity}
	 * @param userId ID of the user
	 * @param jobNr 3 to 6 digits
	 * @param posNr up to 3 digits
	 * @param role 0 = simple co-worker, 1 = project leader, 2 = same as 1 but without user administration
	 */
	public UserJobEntity(Integer userId, Integer jobNr, Integer posNr, Integer role)
	{
		this.userId = userId;
		this.jobNr = jobNr;
		this.posNr = posNr;
		this.role = role;
	}
	
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

	public Integer getRole()
	{
		return role;
	}

	public void setRole(Integer role)
	{
		this.role = role;
	}

}
