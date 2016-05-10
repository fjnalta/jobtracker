package net.greenbeansit.jobtracker.server.data.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "JOB")
// TODO Mike: Implement NamedQueries for Job
// @NamedQueries( { @NamedQuery(name = "ActivityReportTemplate.findAll", query =
// "SELECT p FROM Person p"),
// @NamedQuery(name = "Person.findPerson", query = "SELECT p FROM Person p where
// p.name=:name and p.age=:age")
// })
public class JobEntity implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -8538049446050091809L;

	@Id
	@GeneratedValue
	private Integer				id;

	private Integer				jobNr;
	private Integer				posNr;
	private Integer				accountingMode;
	private Integer				customerID;
	private String				desc;
	private Integer				maxBudget;
	private Integer				usedBudget;
	private boolean				isIntern;

	public JobEntity()
	{

	}

	public JobEntity(Integer jobNr, Integer posNr, Integer accountingMode,
			Integer customerID, String desc, Integer maxBudget,
			Integer usedBudget)
	{
		this.jobNr = jobNr;
		this.posNr = posNr;
		this.accountingMode = accountingMode;
		this.customerID = customerID;
		this.desc = desc;
		this.maxBudget = maxBudget;
		this.usedBudget = usedBudget;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	@Column(name = "JOB_NO")
	public Integer getJobNr()
	{
		return jobNr;
	}

	public void setJobNr(Integer jobNr)
	{
		this.jobNr = jobNr;
	}

	@Column(name = "POS_NO")
	public Integer getPosNr()
	{
		return posNr;
	}

	public void setPosNr(Integer posNr)
	{
		this.posNr = posNr;
	}

	@Column(name = "ACOUNTING_MODE")
	public Integer getAccountingMode()
	{
		return accountingMode;
	}

	public void setAccountingMode(Integer accountingMode)
	{
		this.accountingMode = accountingMode;
	}

	@Column(name = "CUSTOMER_ID")
	public Integer getCustomerID()
	{
		return customerID;
	}

	public void setCustomerID(Integer customerID)
	{
		this.customerID = customerID;
	}

	@Column(name = "DESCRIPTION")
	public String getDesc()
	{
		return desc;
	}

	public void setDesc(String desc)
	{
		this.desc = desc;
	}

	@Column(name = "BUDGET")
	public Integer getMaxBudget()
	{
		return maxBudget;
	}

	public void setMaxBudget(Integer maxBudget)
	{
		this.maxBudget = maxBudget;
	}

	@Column(name = "BUDGET_USED")
	public Integer getUsedBudget()
	{
		return usedBudget;
	}

	public void setUsedBudget(Integer usedBudget)
	{
		this.usedBudget = usedBudget;
	}

	@Column(name = "IS_INTERN")
	public boolean isIntern()
	{
		return isIntern;
	}

	public void setIntern(boolean isIntern)
	{
		this.isIntern = isIntern;
	}
}
