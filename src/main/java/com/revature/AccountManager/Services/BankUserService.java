package com.revature.AccountManager.Services;

import com.revature.AccountManager.Entities.BankUser;
import com.revature.AccountManager.Repositories.BankUserRepository;
import org.springframework.stereotype.Service;

@Service
public class BankUserService {
    BankUserRepository bankUserRepository;

    public BankUserService(BankUserRepository bankUserRepository) { this.bankUserRepository = bankUserRepository;};
    public BankUser saveBankUser(BankUser bankUser){return this.bankUserRepository.save(bankUser);};
    public BankUser findById(Long id){return this.bankUserRepository.findById(id).orElse(null);};
    public BankUser updateBankUser(BankUser bankUser){return this.bankUserRepository.save(bankUser);};
}
