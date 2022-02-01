package com.revature.AccountManager.Services;

import com.revature.AccountManager.Entities.BankAccount;
import com.revature.AccountManager.Repositories.BankAccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankAccountService {
    BankAccountRepository bankAccountRepository;

    public BankAccountService(BankAccountRepository bankAccountRepository) { this.bankAccountRepository = bankAccountRepository;};
    public BankAccount saveBankAccount(BankAccount bankAccount){return this.bankAccountRepository.save(bankAccount);};
    public BankAccount findByAccountNumber(Long accountNumber){return this.bankAccountRepository.findById(accountNumber).orElse(null);};
    public BankAccount updateBankAccount(BankAccount bankAccount){return this.bankAccountRepository.save(bankAccount);};
//    public List<BankAccount> getBankAccountsByUser(Long accountNumber) {
//        List<BankAccount> accounts = bankAccountRepository.findAllByUser_Id(accountNumber);
//        return accounts;
//    }
    public Boolean deleteBankAccountByAccountNumber(Long id) {
        BankAccount bankAccount = bankAccountRepository.findById(id).orElse(null);

        if (bankAccount == null) {
            return false;
        } else {
            this.bankAccountRepository.delete(bankAccount);
            return true;
        }

    }
}
