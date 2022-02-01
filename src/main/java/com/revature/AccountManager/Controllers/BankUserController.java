package com.revature.AccountManager.Controllers;

import com.revature.AccountManager.Entities.BankAccount;
import com.revature.AccountManager.Entities.BankUser;
import com.revature.AccountManager.Services.BankAccountService;
import com.revature.AccountManager.Services.BankUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class BankUserController {
    BankUserService bankUserService;

    public BankUserController(BankUserService bankUserService){this.bankUserService = bankUserService;};

    // get a user
    @GetMapping("/{id}")
    public ResponseEntity<BankUser> getUserById(@PathVariable Long id) {
        BankUser bankUser = bankUserService.findById(id);
        HttpStatus status;
        if (bankUser == null) {
            status = HttpStatus.NOT_FOUND;
        } else {
            status = HttpStatus.OK;
        }

        return new ResponseEntity<>(bankUser, status);
    }

    // add a user
    @PostMapping("/add")
    public BankUser addBankUser(@RequestBody BankUser bankUser) {
        return bankUserService.saveBankUser(bankUser);
    }

    // update uset
    @PutMapping("/update")
    public ResponseEntity<BankUser> updateBankUser(@RequestBody BankUser bankUser) {
        BankUser currentBankUser = bankUserService.findById(bankUser.getId());
        HttpStatus status;
        if (currentBankUser == null) {
            status = HttpStatus.NOT_FOUND;
        } else {
            currentBankUser = bankUserService.updateBankUser(bankUser);
            status = HttpStatus.OK;
        }

        return new ResponseEntity<>(currentBankUser, status);
    }
    //users cannot be deleted.

}
