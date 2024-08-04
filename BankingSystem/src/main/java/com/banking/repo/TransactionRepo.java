package com.banking.repo;

import com.banking.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepo extends JpaRepository<Transaction,Integer> {

    List<Transaction> findAllByAccountId(Integer accountId);
}
