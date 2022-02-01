package com.revature.AccountManager.Repositories;

import com.revature.AccountManager.Entities.BankUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankUserRepository extends JpaRepository<BankUser, Long> {

    BankUser findByPAN(Integer pan);

}
