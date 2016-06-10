package net.greenbeansit.jobtracker.shared;

/**
 * Represents the assigned jobs to a user.
 * 
 * @author Mike Hukiewitz
 *
 */
public class JobID
{
	private Integer jobNr, posNr;

	/**
	 * Empty constructor for internal purposes.
	 */
	public JobID()
	{

	}

	/**
	 * Initializes a new instance of {@link JobID}
	 * 
	 * @param jobNr
	 *            the Job no.
	 * @param posNr
	 *            the Pos no.
	 */
	public JobID(Integer jobNr, Integer posNr)
	{
		this.setJobNr(jobNr);
		this.setPosNr(posNr);
	}

	/**
	 * Gets the Job number.
	 * 
	 * @return the job Numer.
	 */
	public Integer getJobNr()
	{
		return jobNr;
	}

	/**
	 * Sets the Job number.
	 * 
	 * @param jobNr
	 *            the Job no.
	 */
	public void setJobNr(Integer jobNr)
	{
		this.jobNr = jobNr;
	}

	/**
	 * Gets the Position number.
	 * 
	 * @return the position no.
	 */
	public Integer getPosNr()
	{
		return posNr;
	}

	/**
	 * Sets the Position number.
	 * 
	 * @param posNr
	 *            the Position number.
	 */
	public void setPosNr(Integer posNr)
	{
		this.posNr = posNr;
	}

	/**
	 * Checks if two Jobs match
	 * 
	 * @param job
	 *            the Job
	 * @return true if the Jobs match.
	 */
	public boolean matchesJob(Job job)
	{
		return posNr.equals(job.getPosNr()) && jobNr.equals(job.getJobNr());
	}
}
