package com.banking.controller;

import com.banking.DTO.AccountDto;
import com.banking.DTO.CustomerDto;
import com.banking.entities.Account;
import com.banking.service.AccountService;
import com.banking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.banking.entities.Customer;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/add")
    public ResponseEntity<?> addAccount(@RequestBody AccountDto accountDto){
        Account accountCreated=accountService.addAccount(accountDto.getAccountId(),accountDto.getBalance(),
                accountDto.getCustomerId(),accountDto.getAccountType().toString());
        if(accountCreated==null)return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(accountCreated,HttpStatus.CREATED);
    }
}
