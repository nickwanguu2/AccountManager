package com.revature.AccountManager.Repositories;

import com.revature.AccountManager.Entities.BankAccount;
import com.revature.AccountManager.Entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    /*List<Transaction> findAllByPrimaryAccount_accountNumber(Integer accountNumber);
    List<Transaction> findAllBySecondaryAccount_accountNumber(Integer accountNumber);*/
}
