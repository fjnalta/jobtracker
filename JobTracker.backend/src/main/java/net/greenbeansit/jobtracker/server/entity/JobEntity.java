package net.greenbeansit.jobtracker.server.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "JOB")
//TODO Mike: Implement NamedQueries for Job
//@NamedQueries( { @NamedQuery(name = "ActivityReportTemplate.findAll", query = "SELECT p FROM Person p"),
//@NamedQuery(name = "Person.findPerson", query = "SELECT p FROM Person p where p.name=:name and p.age=:age")
//})
public class JobEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8538049446050091809L;

	@Id
	@GeneratedValue
	private Long id;

	private Integer				jobNr;
	private Integer				posNr;
	private String				payMode;
	private String				customerID;
	private String				desc;
	private Integer				maxBudget;
	private Integer				usedBudget;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUsedBudget(Integer usedBudget)
	{
		this.usedBudget = usedBudget;
	}

	@Column(name = "JOBNR")
	public Integer getJobNr()
	{
		return jobNr;
	}

	public void setJobNr(Integer jobNr)
	{
		this.jobNr = jobNr;
	}

	@Column(name = "POSNR")
	public Integer getPosNr()
	{
		return posNr;
	}

	public void setPosNr(Integer posNr)
	{
		this.posNr = posNr;
	}

	@Column(name = "PAYMODE")
	public String getPayMode()
	{
		return payMode;
	}

	public void setPayMode(String payMode)
	{
		this.payMode = payMode;
	}

	@Column(name = "CUSTOMERID")
	public String getCustomerID()
	{
		return customerID;
	}

	public void setCustomerID(String clientID)
	{
		this.customerID = clientID;
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
	
	@Column(name = "MAXBUDGET")
	public Integer getMaxBudget()
	{
		return maxBudget;
	}

	public void setMaxBudget(Integer maxBudget)
	{
		this.maxBudget = maxBudget;
	}

	@Column(name = "USEDBUDGET")
	public Integer getUsedBudget()
	{
		return usedBudget;
	}
}
