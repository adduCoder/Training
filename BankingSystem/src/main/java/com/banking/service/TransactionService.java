package com.banking.service;

import com.banking.entities.Transaction;
import com.banking.repo.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepo transactionRepo;

    public Transaction createTransaction(Integer accountId,String operation,Double amount){
        Transaction transaction=new Transaction();
        transaction.setAccountId(accountId);
        transaction.setAmount(amount);
        transaction.setLocalDateTime(LocalDateTime.now());
        transaction.setOperation(operation);
        return transactionRepo.save(transaction);
    }

    public List<Transaction> viewAllTransactionById(Integer accountId){
        List<Transaction> transactionList=transactionRepo.findAllByAccountId(accountId);
        return transactionList;
    }

    public List<Transaction> viewAllTransaction(){
        List<Transaction> transactionList=transactionRepo.findAll();
        return transactionList;
    }

}
