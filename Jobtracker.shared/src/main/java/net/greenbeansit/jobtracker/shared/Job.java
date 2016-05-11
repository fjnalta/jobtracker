package net.greenbeansit.jobtracker.shared;

import java.io.Serializable;

/**
 * Represents a job of a project. It is used in frontend.
 * 
 * @author Mike Hukiewitz & Alex & Philipp
 */
public class Job implements Serializable {
	private static final long serialVersionUID = -3379608733084915877L;

	private Integer maxBudget;
	private Integer usedBudget;
	private Integer payMode;
	private Integer jobNr, posNr;
	private Integer customerID;
	private String desc;

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
	 *            the payMode as a String.
	 * @param customerID
	 *            the unique identifier for the customer
	 * @param desc
	 *            the description of the job
	 * @param maxBudget
	 *            the maximum budget for the job.
	 * @param usedBudget
	 *            the currently used budget for the job
	 */
	public Job(int jobNr, int posNr, Integer payMode, Integer customerID, String desc, int maxBudget, int usedBudget) {
		this.maxBudget = maxBudget;
		this.usedBudget = usedBudget;
		this.jobNr = jobNr;
		this.posNr = posNr;
		this.payMode = payMode;
		this.customerID = customerID;
		this.desc = desc;
	}

	/**
	 * Setter & Getter
	 */
	public Integer getMaxBudget() {
		return maxBudget;
	}

	public Integer getUsedBudget() {
		return usedBudget;
	}

	public String getDesc() {
		return desc;
	}

	public int getJobNr() {
		return jobNr;
	}

	public int getPosNr() {
		return posNr;
	}

	public Integer getPayMode() {
		return payMode;
	}

	public Integer getCustomerID() {
		return customerID;
	}

	public String toString() {
		return this.jobNr + "-" + this.posNr + "-" + this.payMode + "|" + this.customerID + "|" + this.desc;
	}

	public boolean equals(Job otherJob) {
		return this.jobNr == otherJob.jobNr && this.posNr == otherJob.posNr && this.desc.equals(otherJob.getDesc());
	}

}
