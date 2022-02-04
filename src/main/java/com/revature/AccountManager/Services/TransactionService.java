package com.revature.AccountManager.Services;


import com.revature.AccountManager.Entities.Transaction;
import com.revature.AccountManager.Exceptions.InvalidTransactionException;
import com.revature.AccountManager.Repositories.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) { this.transactionRepository = transactionRepository;};
    public Transaction saveTransaction(Transaction transaction){return this.transactionRepository.save(transaction);};
    public Transaction findById(Long id) throws InvalidTransactionException {return this.transactionRepository.findById(id).orElseThrow(() -> new InvalidTransactionException("User with id : " + id + " does not exist"));};
    public Transaction updateTransaction(Transaction transaction){return this.transactionRepository.save(transaction);};
}