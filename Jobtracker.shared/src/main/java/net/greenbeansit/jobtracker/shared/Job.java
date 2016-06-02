package net.greenbeansit.jobtracker.shared;

import java.io.Serializable;

/**
 * Represents a job of a project. It is used in frontend.
 * 
 * @author Mike Hukiewitz & Alex & Philipp Minges
 */
@SuppressWarnings("JavaDoc")
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
	 *            the jobNr the is used to identify the {@link Job}.
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
	 * @return
	 */
	public Integer getMaxBudget() {
		return maxBudget;
	}

	/**
	 * set the jobNr
	 * @param jobNr Integer value
     */
	public void setJobNr(Integer jobNr) {
		if(jobNr == null)
			throw new IllegalArgumentException();
		this.jobNr = jobNr;
	}

	/**
	 * set the posNr
	 * @param posNr Integer value
     */
	public void setPosNr(Integer posNr) {
		if(posNr == null)
			throw new IllegalArgumentException();
		this.posNr = posNr;
	}

	/**
	 * set the payMode
	 * @param payMode Integer value
     */
	public void setPayMode(Integer payMode) {
		if(payMode == null)
			throw new IllegalArgumentException();
		this.payMode = payMode;
	}

	/**
	 * set the customerID
	 * @param customerID Integer value
     */
	public void setCustomerID(Integer customerID) {
		if(customerID == null)
			throw new IllegalArgumentException();
		this.customerID = customerID;
	}

	/**
	 * set the description
	 * @param desc String value
     */
	public void setDesc(String desc) {
		if(desc == "")
			throw new IllegalArgumentException();
		this.desc = desc;
	}

	/**
	 * set the maxBudget
	 * @param maxBudget Integer value
     */
	public void setMaxBudget(Integer maxBudget) {
		if(maxBudget == null)
			throw new IllegalArgumentException();
		this.maxBudget = maxBudget;
	}

	/**
	 * set the usedBudget
	 * @param usedBudget Integer value
     */
	public void setUsedBudget(Integer usedBudget) {
		if(usedBudget == null)
			throw new IllegalArgumentException();
		this.usedBudget = usedBudget;
	}

	/**
	 * get the used budget
	 * @return Integer value
     */
	public Integer getUsedBudget() {
		return usedBudget;
	}

	/**
	 * get the description
	 * @return String value
     */
	public String getDesc() {
		return desc;
	}

	/**
	 * get the jobNr
	 * @return Integer value
     */
	public Integer getJobNr() {
		return jobNr;
	}

	/**
	 * get the posNr
	 * @return Integer value
     */
	public Integer getPosNr() {
		return posNr;
	}

	/**
	 * get the payMode
	 * @return Integer value
     */
	public Integer getPayMode() {
		return payMode;
	}

	/**
	 * get the customerID
	 * @return Integer value
     */
	public Integer getCustomerID() {
		return customerID;
	}

	/**
	 * get the isIntern value
	 * @return Boolean value
     */
	public Boolean isIntern()
	{
		return isIntern;
	}

	/**
	 * set the isIntern value
	 * @param isIntern Boolean value
     */
	public void setIntern(Boolean isIntern)
	{
		this.isIntern = isIntern;
	}

	/**
	 * custom toString method
	 * @return String value
     */
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

	/**
	 * custom equals value
	 * @param obj Object to compare with
	 * @return Boolean value
     */
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

	/**
	 * get the isLocked value
	 * @return Boolean value
     */
	public Boolean isLocked()
	{
		return isLocked;
	}

	/**
	 * set the isLocked value
	 * @param isLocked Boolean value
     */
	public void setLocked(Boolean isLocked)
	{
		this.isLocked = isLocked;
	}

}
