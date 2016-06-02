package net.greenbeansit.jobtracker.server.data.job;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * A class representing Job as entities in our database. Only used in
 * backend. Each entity class requires a standard constructor and
 * getters/setters for usage in Java Spring.
 * 
 * @author Mike Hukiewitz & Philipp Minges
 *
 */
@Entity
@IdClass(JobEntityId.class)
@Table(name = "job")
public class JobEntity implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -8538049446050091809L;

	@Id
	@Column(name = "job_no", nullable = false)
	private Integer				jobNr;
	@Id
	@Column(name = "pos_no", nullable = false)
	private Integer				posNr;
	@Id
	@Column(name = "description", nullable = false)
	private String				desc;
	@Column(name = "accounting_mode")
	private Integer				accountingMode;
	@Column(name = "customer_id")
	private Integer				customerID;
	@Column(name = "budget")
	private Integer				maxBudget;
	@Column(name = "budget_used")
	private Integer				usedBudget;
	@Column(name = "is_intern")
	private Boolean				intern;
	@Column(name = "is_locked")
	private Boolean				locked;

	/**
	 * Standard constructor for internal purposes.
	 */
	public JobEntity()
	{
	}

	/**
	 * Creates a new {@link JobEntity}.
	 * @param jobNr 3 to 6 digits
	 * @param posNr up to 3 digits
	 * @param accountingMode 0 = NF, 1 = TM (Time & Material), 2 = FP (Festpreis)
	 * @param customerID id of the customer
	 * @param desc short description of the job (max. 30 characters)
	 * @param maxBudget maximum budget 
	 * @param usedBudget already used budget
	 * @param isLocked true if job is locked for further booking
	 */
	public JobEntity(Integer jobNr, Integer posNr, Integer accountingMode,
			Integer customerID, String desc, Integer maxBudget,
			Integer usedBudget, boolean isLocked)
	{
		this.jobNr = jobNr;
		this.posNr = posNr;
		this.accountingMode = accountingMode;
		this.customerID = customerID;
		this.desc = desc;
		this.maxBudget = maxBudget;
		this.usedBudget = usedBudget;
		this.intern = false;
		this.locked = isLocked;
	}

	/**
	 * Gets the Job Number
	 * @return the Jobnumber.
     */
	public Integer getJobNr()
	{
		return jobNr;
	}

	/**
	 * Sets the Job Number for the {@link JobEntity}
	 * @param jobNr
     */
	public void setJobNr(Integer jobNr)
	{
		this.jobNr = jobNr;
	}

	/**
	 * Gets the Position Number of the {@link JobEntity}
	 * @return the position Number
     */
	public Integer getPosNr()
	{
		return posNr;
	}

	/**
	 * Sets the position Number of the {@link JobEntity}
	 * @param posNr the position Number
     */
	public void setPosNr(Integer posNr)
	{
		this.posNr = posNr;
	}

	/**
	 * Gets the Accounting Mode of the {@link JobEntity}
	 * @return the given accounting Mode
     */
	public Integer getAccountingMode()
	{
		return accountingMode;
	}

	/**
	 * Sets the Accounting Mode to the {@link JobEntity}
	 * @param accountingMode
     */
	public void setAccountingMode(Integer accountingMode)
	{
		this.accountingMode = accountingMode;
	}

	/**
	 * Gets the Customer ID of the {@link JobEntity}
	 * @return the the Cusomer id.
     */
	public Integer getCustomerID()
	{
		return customerID;
	}

	/**
	 * Sets the Customer id of the {@link JobEntity}
	 * @param customerID the Customer id.
     */
	public void setCustomerID(Integer customerID)
	{
		this.customerID = customerID;
	}

	public String getDesc()
	{
		return desc;
	}

	public void setDesc(String desc)
	{
		this.desc = desc;
	}

	public Integer getMaxBudget()
	{
		return maxBudget;
	}

	public void setMaxBudget(Integer maxBudget)
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

	public Boolean getIntern()
	{
		return intern;
	}

	public void setIntern(Boolean isIntern)
	{
		this.intern = isIntern;
	}

	public Boolean getLocked()
	{
		return locked;
	}

	public void setLocked(Boolean isLocked)
	{
		this.locked = isLocked;
	}

}
