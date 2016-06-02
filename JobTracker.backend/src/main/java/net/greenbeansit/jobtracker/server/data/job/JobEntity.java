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
 * @author Mike Hukiewitz
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
	 * @return the job number
	 */
	public Integer getJobNr()
	{
		return jobNr;
	}

	/**
	 * set job number
	 * @param jobNr
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
	 * @param posNr
	 */
	public void setPosNr(Integer posNr)
	{
		this.posNr = posNr;
	}

	/**
	 * @return the accounting mode: 0 = NF, 1 = TM (Time & Material), 2 = FP (Festpreis)
	 */
	public Integer getAccountingMode()
	{
		return accountingMode;
	}

	/**
	 * set accounting mode: 0 = NF, 1 = TM (Time & Material), 2 = FP (Festpreis)
	 * @param accountingMode
	 */
	public void setAccountingMode(Integer accountingMode)
	{
		this.accountingMode = accountingMode;
	}

	/**
	 * @return the customer ID
	 */
	public Integer getCustomerID()
	{
		return customerID;
	}

	/**
	 * set customer ID
	 * @param customerID
	 */
	public void setCustomerID(Integer customerID)
	{
		this.customerID = customerID;
	}

	/**
	 * @return the short description
	 */
	public String getDesc()
	{
		return desc;
	}

	/**
	 * set description
	 * @param desc
	 */
	public void setDesc(String desc)
	{
		this.desc = desc;
	}

	/**
	 * @return the maximum budget
	 */
	public Integer getMaxBudget()
	{
		return maxBudget;
	}

	/**
	 * set maximum budget
	 * @param maxBudget
	 */
	public void setMaxBudget(Integer maxBudget)
	{
		this.maxBudget = maxBudget;
	}

	/**
	 * @return the used budget
	 */
	public Integer getUsedBudget()
	{
		return usedBudget;
	}

	/**
	 * set used budget
	 * @param usedBudget
	 */
	public void setUsedBudget(Integer usedBudget)
	{
		this.usedBudget = usedBudget;
	}

	/**
	 * @return true if internal job
	 */
	public Boolean getIntern()
	{
		return intern;
	}

	/**
	 * set whether internal job
	 * @param isIntern
	 */
	public void setIntern(Boolean isIntern)
	{
		this.intern = isIntern;
	}

	/**
	 * @return true if locked for further booking
	 */
	public Boolean getLocked()
	{
		return locked;
	}

	/**
	 * set lock on further booking
	 * @param isLocked
	 */
	public void setLocked(Boolean isLocked)
	{
		this.locked = isLocked;
	}
}
