package com.revature.AccountManager.Services;


import com.revature.AccountManager.Entities.Transaction;
import com.revature.AccountManager.Repositories.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) { this.transactionRepository = transactionRepository;};
    public Transaction saveTransaction(Transaction transaction){return this.transactionRepository.save(transaction);};
    public Transaction findById(Long id){return this.transactionRepository.findById(id).orElse(null);};
    public Transaction updateTransaction(Transaction transaction){return this.transactionRepository.save(transaction);};
}