package net.greenbeansit.jobtracker.shared;

import java.io.Serializable;

/**
 * Represents a job of a project. It is used in frontend.
 * 
 * @author Mike Hukiewitz & Alex & Philipp Minges
 */
public class Job implements Serializable {
	private static final long serialVersionUID = -3379608733084915877L;

	private Integer jobNr;
	private Integer posNr;
	private Integer payMode;
	private Integer customerID;
	private String desc;
	private Integer maxBudget;
	private Integer usedBudget;
	private Boolean isIntern;
	private Boolean isLocked;
	
	/**
	 * Initializes a new instance of the {@link Job} class with all its fields
	 * set to null.
	 */
	public Job() {

	}

	/**
	 * Initializes a new instance of the {@link Job} class with the following
	 * values.
	 * 
	 * @param jobNr
	 *            the {@link jobNr} the is used to identify the {@link Job}.
	 * @param posNr
	 *            the position no.
	 * @param payMode
	 *            0 = NF, 1 = TM, 2 = FP
	 * @param customerID
	 *            the unique identifier for the customer
	 * @param desc
	 *            the description of the job
	 * @param maxBudget
	 *            the maximum budget for the job.
	 * @param usedBudget
	 *            the currently used budget for the job
	 */
	public Job(Integer jobNr, Integer posNr, Integer payMode, Integer customerID, String desc, Integer maxBudget, Integer usedBudget, Boolean isLocked) {
		this.maxBudget = maxBudget;
		this.usedBudget = usedBudget;
		this.jobNr = jobNr;
		this.posNr = posNr;
		this.payMode = payMode;
		this.customerID = customerID;
		this.desc = desc;
		this.setLocked(isLocked);
	}

	/**
	 * Setter & Getter
	 */
	public Integer getMaxBudget() {
		return maxBudget;
	}

	public void setJobNr(Integer jobNr) {
		if(jobNr == null)
			throw new IllegalArgumentException();
		this.jobNr = jobNr;
	}

	public void setPosNr(Integer posNr) {
		if(posNr == null)
			throw new IllegalArgumentException();
		this.posNr = posNr;
	}

	public void setPayMode(Integer payMode) {
		if(payMode == null)
			throw new IllegalArgumentException();
		this.payMode = payMode;
	}

	public void setCustomerID(Integer customerID) {
		if(customerID == null)
			throw new IllegalArgumentException();
		this.customerID = customerID;
	}

	public void setDesc(String desc) {
		if(desc == "")
			throw new IllegalArgumentException();
		this.desc = desc;
	}

	public void setMaxBudget(Integer maxBudget) {
		if(maxBudget == null)
			throw new IllegalArgumentException();
		this.maxBudget = maxBudget;
	}

	public void setUsedBudget(Integer usedBudget) {
		if(usedBudget == null)
			throw new IllegalArgumentException();
		this.usedBudget = usedBudget;
	}

	public Integer getUsedBudget() {
		return usedBudget;
	}

	public String getDesc() {
		return desc;
	}

	public Integer getJobNr() {
		return jobNr;
	}

	public Integer getPosNr() {
		return posNr;
	}

	public Integer getPayMode() {
		return payMode;
	}

	public Integer getCustomerID() {
		return customerID;
	}

	public Boolean isIntern()
	{
		return isIntern;
	}

	public void setIntern(Boolean isIntern)
	{
		this.isIntern = isIntern;
	}

	public String toString() {
		String payMode;
		switch(this.payMode)
		{
			case 0:
				payMode = "NF";
				break;
			case 1:
				payMode = "TM";
				break;
			case 2:
				payMode = "FP";
				break;
			default:
				payMode = "UNDEFINED";
		}
			
		//TODO: String format
		return this.jobNr + "-" + this.posNr + "-" + payMode + " | " + this.customerID + " | " + this.desc;
	}

	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		else if (obj instanceof Job)
		{
			Job temp = (Job) obj;
			return this.jobNr.equals(temp.jobNr) && this.posNr.equals(temp.posNr);
		} else
			return false;
	}

	public Boolean isLocked()
	{
		return isLocked;
	}

	public void setLocked(Boolean isLocked)
	{
		this.isLocked = isLocked;
	}

}
