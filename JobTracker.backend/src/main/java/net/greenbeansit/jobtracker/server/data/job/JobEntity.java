package net.greenbeansit.jobtracker.server.data.job;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity @IdClass(JobEntityId.class)
@Table(name = "job")

public class JobEntity implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -8538049446050091809L;

	@Id @Column(name = "job_no", nullable = false)
	private Integer				jobNr;
	@Id @Column(name = "pos_no", nullable = false)
	private Integer				posNr;
	@Id @Column(name = "description", nullable = false)
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

	public JobEntity()
	{

	}

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

	
	public Integer getJobNr()
	{
		return jobNr;
	}

	public void setJobNr(Integer jobNr)
	{
		this.jobNr = jobNr;
	}

	
	public Integer getPosNr()
	{
		return posNr;
	}

	public void setPosNr(Integer posNr)
	{
		this.posNr = posNr;
	}

	
	public Integer getAccountingMode()
	{
		return accountingMode;
	}

	public void setAccountingMode(Integer accountingMode)
	{
		this.accountingMode = accountingMode;
	}

	
	public Integer getCustomerID()
	{
		return customerID;
	}

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
