package com.revature.AccountManager.Services;

import com.revature.AccountManager.Entities.BankAccount;
import com.revature.AccountManager.Exceptions.IllegalTransactionException;
import com.revature.AccountManager.Exceptions.InvalidAccountException;
import com.revature.AccountManager.Repositories.BankAccountRepository;
import org.springframework.stereotype.Service;

import static com.revature.AccountManager.Utilities.MoneyRound.round;

@Service
public class BankAccountService {
    BankAccountRepository bankAccountRepository;

    public BankAccountService(BankAccountRepository bankAccountRepository) { this.bankAccountRepository = bankAccountRepository;}
    public BankAccount saveBankAccount(BankAccount bankAccount){return bankAccountRepository.save(bankAccount);}
    //throw a custom error instead
    public BankAccount findById(Long id) throws InvalidAccountException {return this.bankAccountRepository.findById(id).orElseThrow(() -> new InvalidAccountException("Account with id: " + id + " does not exist"));};
    public BankAccount updateBankAccount(BankAccount bankAccount){return this.bankAccountRepository.save(bankAccount);};

    public BankAccount changeBalance(BankAccount bankAccount, Float amount) throws IllegalTransactionException {
        Float balance = bankAccount.getBalance();
        if (balance + amount >= 0) {
            bankAccount.setBalance(round(balance + amount));
            return bankAccountRepository.save(bankAccount);
        } else {
            throw new IllegalTransactionException("Insufficient funds.");
        }
    }
    public void transferBalance(BankAccount bankAccount1, BankAccount bankAccount2, Float amount) throws IllegalTransactionException {
        Float balance = bankAccount1.getBalance();
        if (balance - amount >= 0) {
            bankAccount1.setBalance(round(balance - amount));
            bankAccount2.setBalance(round(bankAccount2.getBalance() + amount));
            bankAccountRepository.save(bankAccount1);
            bankAccountRepository.save(bankAccount2);
        } else {
            throw new IllegalTransactionException("Insufficient funds.");
        }
    }

//    public List<BankAccount> getBankAccountsByUser(Long accountNumber) {
//        List<BankAccount> accounts = bankAccountRepository.findAllByUser_Id(accountNumber);
//        return accounts;
//    }
//    public Boolean deleteBankAccountByAccountNumber(Long id) {
//        BankAccount bankAccount = bankAccountRepository.findById(id).orElse(null);
//
//        if (bankAccount == null) {
//            return false;
//        } else {
//            this.bankAccountRepository.delete(bankAccount);
//            return true;
//        }
//
//    }
}
