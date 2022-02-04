package com.revature.AccountManager.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long transactionID;

    //Primary User of the Transaction
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "primaryAccountTransaction", referencedColumnName = "accountNumber", nullable = false)
    @JsonBackReference(value = "primaryAccount")
    private BankAccount primaryAccount;

    private String primaryAccountNumber;

    private Float primaryAmountBefore;

    private Float primaryAmountAfter;

    //Secondary User of the Transaction
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "secondaryAccountTransaction", referencedColumnName = "accountNumber")
    @JsonBackReference(value = "secondaryAccount")
    private BankAccount secondaryAccount;

    private String secondaryAccountNumber;

    private Float secondaryAmountBefore;

    private Float secondaryAmountAfter;

    //date of transaction
    @Column(nullable = false)
    private LocalDateTime timeOfTransaction;

    //cash, debit
    @Column(nullable = false)
    private String paymentType;

    //Withdrawal, transfer, or deposit;
    @Column(nullable = false)
    private String transactionType;

    //Amount of money in transaction;
    @Column(nullable = false)
    private Float transactionAmount;

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

    public String getPrimaryAccountNumber() {
        return primaryAccountNumber;
    }
    public void setPrimaryAccountNumber(String primaryAccountNumber) {
        this.primaryAccountNumber = primaryAccountNumber;
    }

    public Float getPrimaryAmountBefore() {
        return primaryAmountBefore;
    }
    public void setPrimaryAmountBefore(Float primaryAmountBefore) {
        this.primaryAmountBefore = primaryAmountBefore;
    }

    public Float getPrimaryAmountAfter() {
        return primaryAmountAfter;
    }
    public void setPrimaryAmountAfter(Float primaryAmountAfter) {
        this.primaryAmountAfter = primaryAmountAfter;
    }

    public BankAccount getSecondaryAccount() {
        return secondaryAccount;
    }
    public void setSecondaryAccount(BankAccount secondaryAccount) {
        this.secondaryAccount = secondaryAccount;
    }

    public String getSecondaryAccountNumber() {
        return secondaryAccountNumber;
    }
    public void setSecondaryAccountNumber(String secondaryAccountNumber) {
        this.secondaryAccountNumber = secondaryAccountNumber;
    }

    public Float getSecondaryAmountBefore() {
        return secondaryAmountBefore;
    }
    public void setSecondaryAmountBefore(Float secondaryAmountBefore) {
        this.secondaryAmountBefore = secondaryAmountBefore;
    }

    public Float getSecondaryAmountAfter() {
        return secondaryAmountAfter;
    }
    public void setSecondaryAmountAfter(Float secondaryAmountAfter) {
        this.secondaryAmountAfter = secondaryAmountAfter;
    }

    public LocalDateTime getTimeOfTransaction() {
        return timeOfTransaction;
    }
    public void setTimeOfTransaction(LocalDateTime timeOfTransaction) {
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

    public Float getTransactionAmount() {
        return transactionAmount;
    }
    public void setTransactionAmount(Float transactionAmount) {
        this.transactionAmount = transactionAmount;
    }
}
