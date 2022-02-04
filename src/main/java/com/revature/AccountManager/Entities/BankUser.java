package com.revature.AccountManager.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class BankUser {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    //Unique user id
    private Long id;

    @Column(nullable = false, unique = true, length = 6)
    private String customerID;

    //User password
    @Column(nullable = false)
    private String password;

    //PAN
    @Column(nullable = false, unique = true)
    private Integer PAN;

    //Citizen UID
    @Column(nullable = false)
    private Integer citizenUID;

    //Name
    @Column(nullable = false)
    private String name;

    //Postal Address
    @Column(nullable = false)
    private String address;

    //Email
    @Column(nullable = false)
    private String email;
    //DoB
    @Column(nullable = false)
    private LocalDate dateOfBirth;

    //Role of the user: "Account Holder" and "Bank Manager"
    @Column(nullable = false)
    private String role;

    @OneToMany(mappedBy="user")
    @JsonManagedReference(value = "userAccount")
    private Set<BankAccount> accounts = new HashSet<>();;


    //Getters and setters

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPAN() {
        return PAN;
    }
    public void setPAN(Integer PAN) {
        this.PAN = PAN;
    }

    public Integer getCitizenUID() {
        return citizenUID;
    }
    public void setCitizenUID(Integer citizenUID) {
        this.citizenUID = citizenUID;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public Set<BankAccount> getAccounts() {
        return accounts;
    }
    public void setAccounts(Set<BankAccount> accounts) {
        this.accounts = accounts;
    }
}