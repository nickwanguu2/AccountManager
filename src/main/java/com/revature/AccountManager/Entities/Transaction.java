package com.revature.AccountManager.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long transactionID;

    //Primary User of the Transaction
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "primaryAccountTransaction", referencedColumnName = "accountNumber")
    @JsonManagedReference(value = "primaryAccount")
    private BankAccount primaryAccount;

    //Secondary User of the Transaction
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "secondaryAccountTransaction", referencedColumnName = "accountNumber")
    @JsonManagedReference(value = "secondaryAccount")
    private BankAccount secondaryAccount;

    //date of transaction
    private Date timeOfTransaction;

    //cash, debit
    private String paymentType;

    //Withdrawal, transfer, or deposit;
    private String transactionType;

    //Amount of money in transaction;
    private Number transactionAmount;

    public Long getTransactionID() {
        return transactionID;
    }
    public void setTransactionID(Long transactionID) {
        this.transactionID = transactionID;
    }

    public BankAccount getPrimaryAccount() {
        return primaryAccount;
    }
    public void setPrimaryAccount(BankAccount primaryAccount) {
        this.primaryAccount = primaryAccount;
    }

    public BankAccount getSecondaryAccount() {
        return secondaryAccount;
    }
    public void setSecondaryAccount(BankAccount secondaryAccount) {
        this.secondaryAccount = secondaryAccount;
    }

    public Date getTimeOfTransaction() {
        return timeOfTransaction;
    }
    public void setTimeOfTransaction(Date timeOfTransaction) {
        this.timeOfTransaction = timeOfTransaction;
    }

    public String getPaymentType() {
        return paymentType;
    }
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getTransactionType() {
        return transactionType;
    }
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Number getTransactionAmount() {
        return transactionAmount;
    }
    public void setTransactionAmount(Number transactionAmount) {
        this.transactionAmount = transactionAmount;
    }
}
