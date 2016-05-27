package net.greenbeansit.jobtracker.server.data.pseudoJob;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pseudo_job")
public class PseudoJobEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -5496667715876969877L;

    @Id
    @GeneratedValue
    private Integer id;

    private String name;
    private String customer;
    private Integer author;

    public PseudoJobEntity() {

    }

    public PseudoJobEntity(Integer id, String name, String customer) {
        this.id = id;
        this.name = name;
        this.customer = customer;
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "customer")
    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    @Column(name = "author")
    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }
}

