package com.revature.AccountManager.Controllers;

import com.revature.AccountManager.Entities.BankAccount;
import com.revature.AccountManager.Entities.BankUser;
import com.revature.AccountManager.Entities.Transaction;
import com.revature.AccountManager.Services.BankAccountService;
import com.revature.AccountManager.Services.BankUserService;
import com.revature.AccountManager.Services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    BankAccountService bankAccountService;
    TransactionService transactionService;
    public TransactionController(BankAccountService bankAccountService, TransactionService transactionService){this.bankAccountService = bankAccountService; this.transactionService = transactionService;};

    // get a transaction
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        Transaction transaction = this.transactionService.findById(id);
        HttpStatus status;
        if (transaction == null) {
            status = HttpStatus.NOT_FOUND;
        } else {
            status = HttpStatus.OK;
        }

        return new ResponseEntity<>(transaction, status);
    }

    //get all transactions associated with an account
    @GetMapping("/user/{id}")
    public ResponseEntity<Set<Transaction>> getTransactionsByAccount(@PathVariable Long id) {
        BankAccount bankAccount = this.bankAccountService.findByAccountNumber(id);
        List<Transaction> transactionsList = new ArrayList<Transaction>();
        transactionsList.addAll(bankAccount.getPrimaryTransactions());
        transactionsList.addAll(bankAccount.getSecondaryTransactions());
        //to be added: sort and only return the 5 most recent transactions;
        //transactions.sort()
        //repurpose the transactions into a set after sorting.
        Set<Transaction> transactions = new HashSet<>();
        transactions.addAll(transactionsList);

        HttpStatus status;
        if (bankAccount == null ) {
            status = HttpStatus.NOT_FOUND;
        } else {
            status = HttpStatus.OK;
        }
        return new ResponseEntity<Set<Transaction>>(transactions, status);
    }

    @PostMapping("/add")
    public Transaction addTransaction(@RequestBody Transaction transaction) {
        return transactionService.saveTransaction(transaction);
    }

    @PutMapping("/update")
    public ResponseEntity<Transaction> updateTransaction(@RequestBody Transaction transaction) {
        Transaction currentTransaction = transactionService.findById(transaction.getTransactionID());
        HttpStatus status;
        if (currentTransaction == null) {
            status = HttpStatus.NOT_FOUND;
        } else {
            currentTransaction = transactionService.updateTransaction(transaction);
            status = HttpStatus.OK;
        }

        return new ResponseEntity<>(currentTransaction, status);
    }

}
