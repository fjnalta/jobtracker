package net.greenbeansit.jobtracker.server.data.userJob;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import net.greenbeansit.jobtracker.server.data.userJob.UserJobEntityId;
import net.greenbeansit.jobtracker.shared.UserJob;

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
	 * 
	 * @param userId
	 *            ID of the user
	 * @param jobNr
	 *            3 to 6 digits
	 * @param posNr
	 *            up to 3 digits
	 * @param role
	 *            0 = simple co-worker, 1 = project leader, 2 = same as 1 but
	 *            without user administration
	 */
	public UserJobEntity(Integer userId, Integer jobNr, Integer posNr,
			Integer role)
	{
		this.userId = userId;
		this.jobNr = jobNr;
		this.posNr = posNr;
		this.role = role;
	}

	/**
	 * @return the user ID
	 */
	public Integer getUserId()
	{
		return userId;
	}

	/**
	 * set user ID
	 * 
	 * @param userId
	 *            the User Id.
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
	 * 
	 * @param jobNr
	 *            the Job Nr.
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
	 * 
	 * @param posNr
	 *            the position no.
	 */
	public void setPosNr(Integer posNr)
	{
		this.posNr = posNr;
	}

	/**
	 * @return the role
	 */
	public Integer getRole()
	{
		return role;
	}

	/**
	 * set role
	 * 
	 * @param role
	 *            the User role.
	 */
	public void setRole(Integer role)
	{
		this.role = role;
	}
	
	public boolean equals(Object obj)
	{
		if (obj == null)
			return false;
		else if (obj instanceof UserJobEntity)
		{
			UserJobEntity temp = (UserJobEntity) obj;
			return this.userId.equals(temp.userId)
					&& this.jobNr.equals(temp.jobNr)
					&& this.posNr.equals(temp.posNr)
					&& this.role.equals(temp.role);
		} else
			return false;
	}
	
	@Override
    public int hashCode() {
        if(userId == null || jobNr == null || posNr == null)
        	return 0;
        List<Integer> ints = new ArrayList<Integer>();
        ints.add(userId);
        ints.add(jobNr);
        ints.add(posNr);
        ints.add(role);
        return ints.hashCode();
    }

}
