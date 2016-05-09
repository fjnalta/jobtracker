package net.greenbeansit.jobtracker.server.data.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMER")
//TODO Mike: Implement NamedQueries for Employee
//@NamedQueries( { @NamedQuery(name = "ActivityReportTemplate.findAll", query = "SELECT p FROM Person p"),
//@NamedQuery(name = "Person.findPerson", query = "SELECT p FROM Person p where p.name=:name and p.age=:age")
//})
public class CustomerEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3842559068860954254L;

	@Id
	@GeneratedValue
	private Integer id;

	private String	name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "NAME")
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}
