package com.revature.AccountManager.Controllers;

import com.revature.AccountManager.Entities.BankAccount;
import com.revature.AccountManager.Entities.BankUser;
import com.revature.AccountManager.Exceptions.InvalidAccountException;
import com.revature.AccountManager.Exceptions.InvalidUserException;
import com.revature.AccountManager.Exceptions.WrongPasswordException;
import com.revature.AccountManager.Services.BankAccountService;
import com.revature.AccountManager.Services.BankUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.Set;

import static com.revature.AccountManager.Utilities.MoneyRound.round;
import static java.lang.Float.parseFloat;
import static java.lang.String.valueOf;

@RestController
@RequestMapping("/account")
@CrossOrigin("*")
public class BankAccountController {
    BankAccountService bankAccountService;
    BankUserService bankUserService;
    public BankAccountController(BankAccountService bankAccountService, BankUserService bankUserService){this.bankAccountService = bankAccountService; this.bankUserService = bankUserService;}

    // get an account
    @GetMapping("/{id}")
    public ResponseEntity<BankAccount> getBankAccountById(@PathVariable Long id) throws InvalidAccountException {
            BankAccount bankAccount = this.bankAccountService.findById(id);
            return new ResponseEntity<>(bankAccount, HttpStatus.OK);
    }

    //get all accounts associated with a user
    @GetMapping("/user/{id}")
    public ResponseEntity<Set<BankAccount>> getBankAccountsByUser(@PathVariable Long id) throws InvalidUserException {
        BankUser bankUser = bankUserService.findById(id);
        Set<BankAccount> bankAccounts = bankUser.getAccounts();
        return new ResponseEntity<>(bankAccounts, HttpStatus.OK);
    }
    // add bank account
    @PostMapping("/add/{userId}")
    public BankAccount addBankAccount(@RequestBody BankAccount bankAccount, @PathVariable Long userId) throws InvalidUserException {
        BankUser bankUser = bankUserService.findById(userId);
        var rng = new Random();
        long accNum = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
        bankAccount.setAccountNumber(valueOf(accNum));
        bankAccount.setUser(bankUser);
        bankAccount.setBalance(round(bankAccount.getBalance()));
        return bankAccountService.saveBankAccount(bankAccount);
    }



    //update a bank account if it exists
    @PutMapping("/update")
    public ResponseEntity<BankAccount> updateBankAccount(@RequestBody BankAccount bankAccount) throws InvalidAccountException {
        bankAccount.setUser(bankAccountService.findById(bankAccount.getId()).getUser());
        return new ResponseEntity<>(bankAccountService.updateBankAccount(bankAccount), HttpStatus.OK);
    }


}
