package net.greenbeansit.jobtracker.server.data.transaction;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * A class representing ActivityReport as entities in our database. Only used in
 * backend. Each entity class requires a standard constructor and
 * getters/setters for usage in Java Spring.
 * 
 * @author Mike Hukiewitz
 *
 */
@Entity
@Table(name = "transaction")
public class TransactionEntity implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -178627731533874470L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	private Integer				id;

	@Column(name = "author")
	private Integer				author;
	@Column(name = "date")
	private Date				date;
	@Column(name = "job_no")
	private Integer				jobNo;
	@Column(name = "pos_no")
	private Integer				posNo;
	@Column(name = "budget_used")
	private Integer				budgetUsed;

	/**
	 * Standard constructor for internal purposes.
	 */
	public TransactionEntity()
	{
	}

	/**
	 * Creates a new {@link TransactionEntity}.
	 * 
	 * @param author
	 *            the user booking the transaction
	 * @param date
	 *            the date of occurrence
	 * @param jobNo
	 *            job associated to the transaction (3 to 6 digits)
	 * @param posNo
	 *            job associated to the transaction (3 to 6 digits)
	 * @param budgetUsed
	 *            the budget that was used
	 */
	public TransactionEntity(Integer author, Date date, Integer jobNo,
			Integer posNo, Integer budgetUsed)
	{
		this.author = author;
		this.date = date;
		this.jobNo = jobNo;
		this.posNo = posNo;
		this.budgetUsed = budgetUsed;
	}

	/**
	 * get the ID
	 * 
	 * @return Integer value
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * set the ID
	 * 
	 * @param id
	 *            ID Integer value
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * get the authorID
	 * 
	 * @return Integer value
	 */
	public Integer getAuthor()
	{
		return author;
	}

	/**
	 * set the author ID
	 * 
	 * @param author
	 *            Integer value
	 */
	public void setAuthor(Integer author)
	{
		this.author = author;
	}

	/**
	 * get the begin Date
	 * 
	 * @return java.sql.Date object
	 */
	public Date getDate()
	{
		return date;
	}

	/**
	 * set the begin Date
	 * 
	 * @param date
	 *            java.sql.Date object
	 */
	public void setDate(Date date)
	{
		this.date = date;
	}

	/**
	 * get the JobNo
	 * 
	 * @return Integer value
	 */
	public Integer getJobNo()
	{
		return jobNo;
	}

	/**
	 * set the JobNo
	 * 
	 * @param jobNo
	 *            Integer value
	 */
	public void setJobNo(Integer jobNo)
	{
		this.jobNo = jobNo;
	}

	/**
	 * get the PosNo
	 * 
	 * @return Integer value
	 */
	public Integer getPosNo()
	{
		return posNo;
	}

	/**
	 * set the posNo
	 * 
	 * @param posNo
	 *            Integer value
	 */
	public void setPosNo(Integer posNo)
	{
		this.posNo = posNo;
	}

	/**
	 * get the used budget by this transaction
	 * 
	 * @return Integer value
	 */
	public Integer getUsedBudget()
	{
		return this.budgetUsed;
	}

	/**
	 * set the used budget by this transaction
	 * 
	 * @param budgetUsed
	 *            Integer value
	 */
	public void setUsedBudget(Integer budgetUsed)
	{
		this.budgetUsed = budgetUsed;
	}

	public boolean equals(Object obj)
	{
		if (obj == null)
			return false;
		else if (obj instanceof TransactionEntity)
		{
			TransactionEntity temp = (TransactionEntity) obj;
			return this.id.equals(temp.getId());
		} else
			return false;
	}

	@Override
	public int hashCode()
	{
		return id == null ? 0 : id.hashCode();
	}

}
