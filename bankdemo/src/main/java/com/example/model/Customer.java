package com.example.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by rajat.arora on 2/2/2017.
 */
@Entity
@Table(name="customer")
public class Customer implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long customer_id;

    private String name;

    private String description;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer" ,cascade=CascadeType.ALL )
    private Set<Account> accounts = new HashSet<>(0);

    public long getCustomer_id() {
        return customer_id;
    }

    public Customer(long customer_id, String name, String description, Set<Account> accounts) {
        this.customer_id = customer_id;
        this.name = name;
        this.description = description;
        this.accounts = accounts;
    }

    public Customer(long customer_id) {
        this.customer_id = customer_id;
    }

    public void setCustomer_id(long customer_id) {
        this.customer_id = customer_id;
    }

    public Customer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }
}
