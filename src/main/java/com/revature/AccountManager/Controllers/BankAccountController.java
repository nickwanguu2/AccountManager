package com.revature.AccountManager.Controllers;

import com.revature.AccountManager.Entities.BankAccount;
import com.revature.AccountManager.Entities.BankUser;
import com.revature.AccountManager.Services.BankAccountService;
import com.revature.AccountManager.Services.BankUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/account")
@CrossOrigin("*")
public class BankAccountController {
    BankAccountService bankAccountService;
    BankUserService bankUserService;
    public BankAccountController(BankAccountService bankAccountService, BankUserService bankUserService){this.bankAccountService = bankAccountService; this.bankUserService = bankUserService;}

    // get an account
    @GetMapping("/{id}")
    public ResponseEntity<BankAccount> getBankAccountById(@PathVariable Long id) {
        BankAccount bankAccount = this.bankAccountService.findByAccountNumber(id);
        HttpStatus status;
        if (bankAccount == null) {
            status = HttpStatus.NOT_FOUND;
        } else {
            status = HttpStatus.OK;
        }

        return new ResponseEntity<>(bankAccount, status);
    }

    //get all accounts associated with a user
    @GetMapping("/user/{id}")
    public ResponseEntity<Set<BankAccount>> getBankAccountsByUser(@PathVariable Long id) {
        BankUser bankUser = this.bankUserService.findById(id);
        HttpStatus status;
        Set<BankAccount> bankAccounts = new HashSet<>();
        if (bankUser == null ) {
            status = HttpStatus.NOT_FOUND;
        } else {
            bankAccounts = bankUser.getAccounts();
            status = HttpStatus.OK;
        }
        return new ResponseEntity<>(bankAccounts, status);
    }
    // add bank account
    @PostMapping("/add")
    public BankAccount addBankAccount(@RequestBody BankAccount bankAccount) {
        return bankAccountService.saveBankAccount(bankAccount);
    }

    //update a bank account if it exists
    @PutMapping("/update")
    public ResponseEntity<BankAccount> updateBankAccount(@RequestBody BankAccount bankAccount) {
        BankAccount currentBankAccount = bankAccountService.findByAccountNumber(bankAccount.getAccountNumber());
        HttpStatus status;
        if (currentBankAccount == null) {
            status = HttpStatus.NOT_FOUND;
        } else {
            currentBankAccount = bankAccountService.updateBankAccount(bankAccount);
            status = HttpStatus.OK;
        }

        return new ResponseEntity<>(currentBankAccount, status);
    }


}
