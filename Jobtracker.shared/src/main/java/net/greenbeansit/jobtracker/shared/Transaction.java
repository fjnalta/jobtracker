/**
 * 
 */
package net.greenbeansit.jobtracker.shared;

import java.io.Serializable;
import java.sql.Date;


/**
 * @author Mike Hukiewitz
 *
 */
public class Transaction implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -178627731533874470L;

	private Integer				id;
	private Integer				author;
	private Date				date;
	private Integer				jobNo;
	private Integer				posNo;
	private Integer				budgetUsed;

	/**
	 * Standard constructor for internal purposes.
	 */
	public Transaction()
	{
	}

	/**
	 * Creates a new {@link Transaction}.
	 * @param author the user booking the transaction
	 * @param date the date of occurrence
	 * @param jobNo job associated to the transaction (3 to 6 digits)
	 * @param posNo job associated to the transaction (3 to 6 digits)
	 * @param budgetUsed the budget that was used
	 */
	public Transaction(Integer author, Date date, Integer jobNo, Integer posNo, Integer budgetUsed)
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
	 * @param budgetUsed Integer value
	 */
	public void setUsedBudget(Integer budgetUsed)
	{
		this.budgetUsed = budgetUsed;
	}

	public boolean equals(Object obj)
	{
		if (obj == null)
			return false;
		else if (obj instanceof Transaction)
		{
			Transaction temp = (Transaction) obj;
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
