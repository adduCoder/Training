package com.banking.repo;

import com.banking.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepo extends JpaRepository<Transaction,Integer> {
}
