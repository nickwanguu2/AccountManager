package com.revature.AccountManager.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class BankAccount {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    //User id, also referred to as customer ID
    private Long accountNumber;

    //User that owns the account
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @JsonManagedReference(value = "userAccount")
    private BankUser user;

    @OneToMany(mappedBy="primaryAccount")
    @JsonManagedReference(value = "primaryAccount")
    private Set<Transaction> primaryTransactions = new HashSet<>();;

    @OneToMany(mappedBy="secondaryAccount")
    @JsonManagedReference(value = "secondaryAccount")
    private Set<Transaction> secondaryTransactions = new HashSet<>();;

    //Balance of the account.
    private Number balance;



    //Getters and setters

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
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

    public Number getBalance() {
        return balance;
    }
    public void setBalance(Number balance) {
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