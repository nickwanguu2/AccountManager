package com.revature.AccountManager.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class BankAccount implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    //Unique id
    private Long id;

    @Column(unique = true, length = 10, nullable = false)
    private String accountNumber;


    //User that owns the account
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @JsonBackReference(value = "userAccount")
    private BankUser user;

    @OneToMany(mappedBy="primaryAccount")
    @JsonManagedReference(value = "primaryAccount")
    private Set<Transaction> primaryTransactions = new HashSet<>();;

    @OneToMany(mappedBy="secondaryAccount")
    @JsonManagedReference(value = "secondaryAccount")
    private Set<Transaction> secondaryTransactions = new HashSet<>();;

    //Balance of the account.
    @Column(nullable = false)
    private Float balance;

    //whether the account is active or terminated.
    //private String status;



    //Getters and setters

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Number getCustomerID() {
        return user.getId();
    }
    public BankUser getUser() {
        return user;
    }
    public void setUser(BankUser user) {
        this.user = user;
    }

    public Float getBalance() {
        return balance;
    }
    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public Set<Transaction> getPrimaryTransactions() {
        return primaryTransactions;
    }

    public void setPrimaryTransactions(Set<Transaction> primaryTransactions) {
        this.primaryTransactions = primaryTransactions;
    }

    public Set<Transaction> getSecondaryTransactions() {
        return secondaryTransactions;
    }

    public void setSecondaryTransactions(Set<Transaction> secondaryTransactions) {
        this.secondaryTransactions = secondaryTransactions;
    }
}