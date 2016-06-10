package net.greenbeansit.jobtracker.server.data.user;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * A class representing User as entities in our database. Only used in backend.
 * Each entity class requires a standard constructor and getters/setters for
 * usage in Java Spring.
 * 
 * @author Mike Hukiewitz
 *
 */
@Entity
@Table(name = "user")
public class UserEntity implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1371314200909587258L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	private Integer				id;

	@Column(name = "name")
	private String				name;
	@Column(name = "surname")
	private String				surname;
	@Column(name = "supervisor")
	private Integer				supervisor;
	@Column(name = "utilization")
	private Integer				utilization;
	@Column(name = "utilization_year")
	private Integer				utilizationYear;
	@Column(name = "vacation_days")
	private Integer				vacationDays;

	/**
	 * Standard constructor for internal purposes.
	 */
	public UserEntity()
	{

	}

	/**
	 * Initializes an {@link UserEntity}
	 * 
	 * @param id
	 *            ID of the user
	 * @param name
	 *            their first name
	 * @param surname
	 *            their last name
	 * @param supervisor
	 *            ID of the supervisor
	 * @param vacationDays
	 *            how many remaining days this user has
	 */
	public UserEntity(Integer id, String name, String surname,
			Integer supervisor, Integer vacationDays)
	{
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.supervisor = supervisor;
		this.vacationDays = vacationDays;
	}

	/**
	 * Creates a new {@link UserEntity}
	 * 
	 * @param name
	 *            their first name
	 * @param surname
	 *            their last name
	 * @param supervisor
	 *            ID of the supervisor
	 * @param vacationDays
	 *            how many remaining days this user has
	 */
	public UserEntity(String name, String surname, Integer supervisor,
			Integer vacationDays)
	{
		this.name = name;
		this.surname = surname;
		this.supervisor = supervisor;
		this.vacationDays = vacationDays;
	}

	/**
	 * @return the ID
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * set ID
	 * 
	 * @param id
	 *            the UserId.
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * @return the first name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * set first name
	 * 
	 * @param name
	 *            the Name.
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the last name
	 */
	public String getSurname()
	{
		return surname;
	}

	/**
	 * set last name
	 * 
	 * @param surname
	 *            the Surname.
	 */
	public void setSurname(String surname)
	{
		this.surname = surname;
	}

	/**
	 * @return the supervisor ID
	 */
	public Integer getSupervisor()
	{
		return supervisor;
	}

	/**
	 * set supervisor ID
	 * 
	 * @param supervisor
	 *            the Supervisor.
	 */
	public void setSupervisor(Integer supervisor)
	{
		this.supervisor = supervisor;
	}

	/**
	 * @return the utilization in percent of
	 *         {@link UserEntity#getUtilizationYear()}
	 */
	public Integer getUtilization()
	{
		return utilization;
	}

	/**
	 * set utilization in percent of {@link UserEntity#getUtilizationYear()}
	 * 
	 * @param utilization
	 *            the Utilization.
	 */
	public void setUtilization(Integer utilization)
	{
		this.utilization = utilization;
	}

	/**
	 * @return the year which the utilization refers to
	 */
	public Integer getUtilizationYear()
	{
		return utilizationYear;
	}

	/**
	 * set the year which the utilization refers to
	 * 
	 * @param utilizationYear
	 *            the Utilization Year.
	 */
	public void setUtilizationYear(Integer utilizationYear)
	{
		this.utilizationYear = utilizationYear;
	}

	/**
	 * @return how many days of vacation this user has left
	 */
	public Integer getVacationDays()
	{
		return vacationDays;
	}

	/**
	 * set how many days of vacation this user has left
	 * 
	 * @param vacationDays
	 *            amount of vacation days
	 */
	public void setVacationDays(Integer vacationDays)
	{
		this.vacationDays = vacationDays;
	}

	public boolean equals(Object obj)
	{
		if (obj == null)
			return false;
		else if (obj instanceof UserEntity)
		{
			UserEntity temp = (UserEntity) obj;
			return this.id.equals(temp.id);
		} else
			return false;
	}

	@Override
	public int hashCode()
	{
		return id == null ? 0 : id.hashCode();
	}

}
