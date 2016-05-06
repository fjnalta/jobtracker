package net.greenbeansit.jobtracker.shared;

import java.io.Serializable;

/**
 * Result of parsing the job number string. A job number is read-only
 * accessible. "jobNr-posNr-payMode | customerID | desc"
 * 
 * @author Mike Hukiewitz
 */
public class JobID implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8137626358041245741L;
	String	payMode;
	Integer	jobNr, posNr;
	String	customerID;
	String	desc;

	/**
	 * Initializes a new instance of the {@link JobID} class with its fields set
	 * to null.
	 */
	public JobID()
	{

	}
	
	
	public JobID(int jobNr, int posNr, String payMode, String customerID,
			String desc)
	{
		this.jobNr = jobNr;
		this.posNr = posNr;
		this.payMode = payMode;
		this.customerID = customerID;
		this.desc = desc;
	}
	

	public String getDesc()
	{
		return desc;
	}

	public int getJobNr()
	{
		return jobNr;
	}

	public int getPosNr()
	{
		return posNr;
	}

	public String getPayMode() {
		return payMode;
	}
	
	public String getCustomerID()
	{
		return customerID;
	}
	
	public String toString(){
		return this.jobNr + "-"+ this.posNr + "-" + this.payMode + "|" + this.customerID +"|" + this.desc;
	}

}