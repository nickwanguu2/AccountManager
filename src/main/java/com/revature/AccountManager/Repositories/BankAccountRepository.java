package com.revature.AccountManager.Repositories;

import com.revature.AccountManager.Entities.BankAccount;
import com.revature.AccountManager.Entities.BankUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    //List<BankAccount> findAllByUser_Id(Long id);

}
