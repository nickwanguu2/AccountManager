package com.revature.AccountManager.Controllers;

import com.revature.AccountManager.Entities.BankUser;
import com.revature.AccountManager.Exceptions.InvalidUserException;
import com.revature.AccountManager.Exceptions.WrongPasswordException;
import com.revature.AccountManager.Services.BankUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

import static java.lang.String.valueOf;

@RestController
@RequestMapping("/user")
public class BankUserController {
    BankUserService bankUserService;

    public BankUserController(BankUserService bankUserService){this.bankUserService = bankUserService;};

    // get a user
    @GetMapping("/{id}")
    public ResponseEntity<BankUser> getUserById(@PathVariable Long id) throws InvalidUserException {
        BankUser bankUser = bankUserService.findById(id);
        return new ResponseEntity<>(bankUser, HttpStatus.OK);
    }
    //login
    @GetMapping("/login")
    public ResponseEntity<Object> login(@RequestBody BankUser bankUser) throws WrongPasswordException, InvalidUserException {
        BankUser validBankUser = bankUserService.findById(bankUser.getId());
        if (bankUser.getPassword().equals(validBankUser.getPassword())){
            return new ResponseEntity<>(validBankUser, HttpStatus.OK);
        } else {
            throw new WrongPasswordException("The password is incorrect.");
        }
    }

    // add a user
    @PostMapping("/add")
    public ResponseEntity<BankUser> addBankUser(@RequestBody BankUser bankUser) {
        bankUser.setRole("Account Holder");
        var rng = new Random();
        var custID = rng.nextInt(900000) + 100000;
        bankUser.setCustomerID(valueOf(custID));
        return new ResponseEntity<>(bankUserService.saveBankUser(bankUser), HttpStatus.OK);
    }

    // update user
    @PutMapping("/update")
    public ResponseEntity<BankUser> updateBankUser(@RequestBody BankUser bankUser) throws InvalidUserException {
        bankUserService.findById(bankUser.getId());
        return new ResponseEntity<>(bankUserService.updateBankUser(bankUser), HttpStatus.OK);
    }
    //users cannot be deleted.

}
