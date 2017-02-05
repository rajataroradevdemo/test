package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by rajat.arora on 2/2/2017.
 */
@Entity
@Table(name="account")
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long account_id;

    private String accountType;

    private long accountBalance;

    private boolean enabled;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "sourceAccount" ,cascade=CascadeType.ALL)
    private Set<Transaction> transactions = new HashSet<>(0);

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnore
    private Customer customer;


    public Account(Long account_id) {
        this.account_id = account_id;
    }

    public Account() {
    }

//    public Account(long account_id, String accountType, long accountBalance, boolean enabled, Customer customer) {
//        this.account_id = account_id;
//        this.accountType = accountType;
//        this.accountBalance = accountBalance;
//        this.enabled = enabled;
//        this.customer = customer;
//    }
//
    public long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(long account_id) {
        this.account_id = account_id;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Long getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Long accountBalance) {
        this.accountBalance = accountBalance;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }
}
