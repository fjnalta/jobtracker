package net.greenbeansit.jobtracker.shared;

import java.io.Serializable;

/**
 * Represents a job of a project. It is used in frontend.
 * 
 * @author Mike Hukiewitz & Alex
 */
public class Job implements Serializable
{
	private static final long	serialVersionUID	= -3379608733084915877L;

	private JobID				jobID;
	private Integer				maxBudget;
	private Integer				usedBudget;

	

	/**
	 * Initializes a new instance of the {@link Job} class
	 * with all its fields set to null.
	 */
	public Job()
	{
		
	}
	
	/**
	 * Initializes a new instance of the  {@link Job} class
	 * with the following values.
	 * 
	 * @param jobID the {@link JobID} the is used to identify the {@link Job}.
	 * @param maxBudget the maximum budget for the job.
	 */
	public Job(JobID jobID, int maxBudget, int usedBudget)
	{
		this.setJobID(jobID);
		this.setMaxBudget(maxBudget);
		this.setUsedBudget(usedBudget);
	}

	
	public JobID getJobID()
	{
		return jobID;
	}

	public void setJobID(JobID jobID)
	{
		this.jobID = jobID;
	}

	public Integer getMaxBudget()
	{
		return maxBudget;
	}

	public void setMaxBudget(int maxBudget)
	{
		this.maxBudget = maxBudget;
	}
	
	public Integer getUsedBudget()
	{
		return usedBudget;
	}

	public void setUsedBudget(Integer usedBudget)
	{
		this.usedBudget = usedBudget;
	}
	
}
