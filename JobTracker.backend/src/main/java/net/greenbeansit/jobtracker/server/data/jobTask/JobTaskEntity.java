package net.greenbeansit.jobtracker.server.data.jobTask;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import net.greenbeansit.jobtracker.shared.JobTask;

/**
 * A class representing JobTask as entities in our database. Only used in
 * backend. Each entity class requires a standard constructor and
 * getters/setters for usage in Java Spring.
 * 
 * This class implements JIRA compatibility.
 * 
 * @author Mike Hukiewitz
 *
 */
@Entity
@Table(name = "job_task")
public class JobTaskEntity implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -8321550452580601537L;

	@Id
	@GeneratedValue
	private Integer				id;

	@Column(name = "job_no")
	private Integer				jobNr;
	@Column(name = "pos_no")
	private Integer				posNr;
	@Column(name = "name")
	private String				name;

	/**
	 * Standard constructor for internal purposes.
	 */
	public JobTaskEntity()
	{
	}

	/**
	 * Creates a new {@link JobTaskEntity}
	 * 
	 * @param jobNr
	 *            3 to 6 digits
	 * @param posNr
	 *            up to 3 digits
	 * @param name
	 *            name of the task
	 */
	public JobTaskEntity(Integer jobNr, Integer posNr, String name)
	{
		this.jobNr = jobNr;
		this.posNr = posNr;
		this.name = name;
	}

	/**
	 * @return the ID
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * set ID
	 * 
	 * @param id
	 *            the Id.
	 */
	public void setId(Integer id)
	{
		this.id = id;
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
	 *            the Job no.
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
	 *            the pos no.
	 */
	public void setPosNr(Integer posNr)
	{
		this.posNr = posNr;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * set name
	 * 
	 * @param name
	 *            the Name.
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		else if (obj instanceof JobTaskEntity)
		{
			JobTaskEntity temp = (JobTaskEntity) obj;
			return this.id.equals(temp.id);
		} else
			return false;
	}
	
	@Override
    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }
}
