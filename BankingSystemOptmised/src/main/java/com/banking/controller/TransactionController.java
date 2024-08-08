package com.banking.controller;

import com.banking.entities.Transaction;
import com.banking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/viewAllTransaction/{accountId}")
    public ResponseEntity<?> viewAllTransaction(@PathVariable Integer accountId) {
        List<Transaction> transactionList = transactionService.viewAllTransactionById(accountId);
        if (transactionList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(transactionList, HttpStatus.OK);
    }

    @GetMapping("/viewAllTransaction")
    public ResponseEntity<?> viewAll() {
        List<Transaction> transactionList = transactionService.viewAllTransaction();
        if (transactionList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(transactionList, HttpStatus.OK);
    }
}
