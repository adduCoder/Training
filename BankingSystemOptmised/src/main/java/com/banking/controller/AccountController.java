package com.banking.controller;

import com.banking.DTO.AccountDto;
import com.banking.DTO.AmountDto;
import com.banking.ExceptionHandler.*;
import com.banking.entities.Account;
import com.banking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/add")
    public ResponseEntity<?> addAccount(@RequestBody AccountDto accountDto) throws InvalidOperationException {
        Account accountCreated = accountService.addAccount(accountDto.getAccountId(), accountDto.getBalance(),
                accountDto.getCustomerId(), accountDto.getAccountType().toString());
        return new ResponseEntity<>(accountCreated, HttpStatus.CREATED);
    }

    @PostMapping("/credit/{accountId}")
    public ResponseEntity<?> creditAmount(@PathVariable Integer accountId, @RequestBody AmountDto amountDto) throws InvalidOperationException, NoSuchAccountTypeException, TransactionsLimitExceeded {

        accountService.credit(accountId, amountDto.getAmount());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/debit/{accountId}")
    public ResponseEntity<?> debitAmount(@PathVariable Integer accountId, @RequestBody AmountDto amountDto) throws InsufficientBalanceException, NoSuchAccountException {
        accountService.debit(accountId, amountDto.getAmount());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/viewAccount/{accountId}")
    public ResponseEntity<?> viewAccount(@PathVariable Integer accountId) {
        Account account = accountService.viewAccount(accountId);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @GetMapping("/viewAll")
    public ResponseEntity<?> viewAll() {
        List<Account> accountList = accountService.getAllAccount();
        return new ResponseEntity<>(accountList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Account> deleteAccount(@PathVariable Integer id) {
        Account account = accountService.deleteAccount(id);
        ResponseEntity<Account> accountResponseEntity = new ResponseEntity<>(account, HttpStatus.OK);
        return accountResponseEntity;
    }



}
