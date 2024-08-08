package com.banking.repo;


import com.banking.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepo extends JpaRepository<Account, Integer> {
    List<Account> findByCustomerId(Integer customerId);
}
