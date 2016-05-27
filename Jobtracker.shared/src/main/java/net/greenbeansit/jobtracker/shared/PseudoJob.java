package net.greenbeansit.jobtracker.shared;

import java.io.Serializable;

/**
 * Represents a PseudoJob for the Capazity planning
 *
 * @author Philipp Minges
 */
public class PseudoJob implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -2051156787516002689L;
    private Integer id;
    private String  name;
    private String  customer;
    private Integer author;
    private String  PseudoJob;

    public PseudoJob() {

    }

    /**
     * Initializes a new instance of the {@link PseudoJob} class
     *
     * @param id       the identifier
     * @param name     the name of the PseudoJob
     * @param PseudoJob name of the PseudoJob
     */
    public PseudoJob(Integer id, String name, String PseudoJob, String customer)
    {
        this.id = id;
        this.name = name;
        this.customer = customer;
//        this.author = author;
        this.PseudoJob = PseudoJob;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        if (id == null)
            throw new IllegalArgumentException();
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        if (name == "")
            throw new IllegalArgumentException();
        this.name = name;
    }

    public String getPseudoJob()
    {
        return PseudoJob;
    }

    public void setPseudoJob(String PseudoJob)
    {
        if (PseudoJob == "")
            throw new IllegalArgumentException();
        this.PseudoJob = PseudoJob;
    }
    
    public String getCustomer()
    {
        return customer;
    }

    public void setCustomer(String customer)
    {
        if (customer == "")
            throw new IllegalArgumentException();
        this.customer = customer;
    }

    public boolean equals(Object obj) {
		if(obj == null)
			return false;
		else if (obj instanceof PseudoJob)
		{
			PseudoJob temp = (PseudoJob) obj;
			return this.id.equals(temp.id);
		} else
			return false;
	}

    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        if(author == null)
            throw new IllegalArgumentException();
        this.author = author;
    }
}
