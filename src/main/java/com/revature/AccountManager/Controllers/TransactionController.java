package com.revature.AccountManager.Controllers;

import com.revature.AccountManager.Entities.BankAccount;
import com.revature.AccountManager.Entities.Transaction;
import com.revature.AccountManager.Exceptions.IllegalTransactionException;
import com.revature.AccountManager.Exceptions.InvalidAccountException;
import com.revature.AccountManager.Exceptions.InvalidTransactionException;
import com.revature.AccountManager.Services.BankAccountService;
import com.revature.AccountManager.Services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

import static com.revature.AccountManager.Utilities.MoneyRound.round;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    BankAccountService bankAccountService;
    TransactionService transactionService;

    public TransactionController(BankAccountService bankAccountService, TransactionService transactionService){this.bankAccountService = bankAccountService; this.transactionService = transactionService;}

    // get a transaction
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) throws InvalidTransactionException {
        Transaction transaction = this.transactionService.findById(id);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    //get last 5 transactions associated with an account
    @GetMapping("/recent/{id}")
    public ResponseEntity<List<Transaction>> getTransactionsByAccount(@PathVariable Long id) throws InvalidAccountException {
        BankAccount bankAccount = this.bankAccountService.findById(id);
        List<Transaction> transactionsList = new ArrayList<>();
        transactionsList.addAll(bankAccount.getPrimaryTransactions());
        transactionsList.addAll(bankAccount.getSecondaryTransactions());
        //to be added: sort and only return the 5 most recent transactions;
        transactionsList.sort((Transaction t1, Transaction t2)-> t1.getTimeOfTransaction().isAfter(t2.getTimeOfTransaction()) ? -1 : 1);
        HttpStatus status;
        status = HttpStatus.OK;

        return new ResponseEntity<List<Transaction>>(transactionsList.subList(0,5), status);
    }

    @PostMapping("/add/{primaryId}")
    public Transaction addTransaction(@RequestBody Transaction transaction, @PathVariable Long primaryId) throws InvalidAccountException, IllegalTransactionException {
        //find the primary account and set the values into transaction.
        BankAccount primaryAccount = bankAccountService.findById(primaryId);
        transaction.setPrimaryAccount(primaryAccount);
        transaction.setPrimaryAccountNumber(primaryAccount.getAccountNumber());

        //Round the amount and add it to transaction.
        Float amount = round(transaction.getTransactionAmount());
        transaction.setTransactionAmount(amount);

        // get time
        transaction.setTimeOfTransaction(LocalDateTime.now());

        //check if amount is negative, throw error if it is
        if(amount <= 0){
            throw new IllegalTransactionException("Please input a positive amount.");
        } else if(transaction.getTransactionType().equals("withdrawal")){
            //withdraw money, update primary account.
            transaction.setPrimaryAmountBefore(primaryAccount.getBalance());
            bankAccountService.changeBalance(primaryAccount, -1 * amount);
            transaction.setPrimaryAmountAfter(primaryAccount.getBalance());
            return transactionService.saveTransaction(transaction);
        } else if (transaction.getTransactionType().equals("deposit")){
            //deposit money, update primary account.
            transaction.setPrimaryAmountBefore(primaryAccount.getBalance());
            bankAccountService.changeBalance(primaryAccount, amount);
            transaction.setPrimaryAmountAfter(primaryAccount.getBalance());
            return transactionService.saveTransaction(transaction);
        } else {
            //throw error if transaction type is ambiguous
            throw new IllegalTransactionException("Invalid Transaction Type");
        }

    }
    @PostMapping("/add/{primaryId}/{secondaryId}")
    public Transaction addTransaction(@RequestBody Transaction transaction, @PathVariable Long primaryId, @PathVariable Long secondaryId) throws InvalidAccountException, IllegalTransactionException {
        //find the primary account and set the values into transaction.
        BankAccount primaryAccount = bankAccountService.findById(primaryId);
        transaction.setPrimaryAccount(primaryAccount);
        transaction.setPrimaryAccountNumber(primaryAccount.getAccountNumber());
        //find the secondary account and set the values into transaction.
        BankAccount secondaryAccount = bankAccountService.findById(secondaryId);
        transaction.setSecondaryAccount(secondaryAccount);
        transaction.setSecondaryAccountNumber(secondaryAccount.getAccountNumber());
        //round and set amount
        Float amount = round(transaction.getTransactionAmount());
        transaction.setTransactionAmount(amount);
        //get time
        transaction.setTimeOfTransaction(LocalDateTime.now());
        if(amount <= 0){
            throw new IllegalTransactionException("Please input a positive amount.");
        } else if(transaction.getTransactionType().equals("transfer") ){
            //transfer money, update both accounts
            transaction.setPrimaryAmountBefore(primaryAccount.getBalance());
            transaction.setSecondaryAmountBefore(secondaryAccount.getBalance());
            bankAccountService.transferBalance(primaryAccount,secondaryAccount, amount);
            transaction.setPrimaryAmountAfter(primaryAccount.getBalance());
            transaction.setSecondaryAmountAfter(secondaryAccount.getBalance());
            return transactionService.saveTransaction(transaction);
        } else {
            //throw error if transaction type is ambiguous
            throw new IllegalTransactionException("Invalid Transaction Type");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Transaction> updateTransaction(@RequestBody Transaction transaction) throws InvalidTransactionException {
        transaction.setPrimaryAccount(transactionService.findById(transaction.getTransactionID()).getPrimaryAccount());
        transaction.setSecondaryAccount(transactionService.findById(transaction.getTransactionID()).getSecondaryAccount());
        return new ResponseEntity<>(transactionService.updateTransaction(transaction), HttpStatus.OK);
    }

}
