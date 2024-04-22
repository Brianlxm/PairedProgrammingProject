package com.revature.daos;

import com.revature.models.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountDAO extends JpaRepository<BankAccount, Integer> {
    List<BankAccount> findByUserUserId(int userId);
}
