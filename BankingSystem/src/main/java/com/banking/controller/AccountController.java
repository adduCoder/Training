package com.banking.controller;

import com.banking.DTO.AccountDto;
import com.banking.DTO.AmountDto;
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

    @PostMapping("/credit/{accountId}")
    public ResponseEntity<?> creditAmount(@PathVariable Integer accountId,@RequestBody AmountDto amountDto){
        try{
            accountService.credit(accountId,amountDto.getAmount());
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/debit/{accountId}")
    public ResponseEntity<?> debitAmount(@PathVariable Integer accountId,@RequestBody AmountDto amountDto){
        try{
            accountService.debit(accountId,amountDto.getAmount());
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/viewAccount/{accountId}")
    public ResponseEntity<?> viewAccount(@PathVariable Integer accountId){
        Account account=accountService.viewAccount(accountId);
        if(account==null)return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(account,HttpStatus.OK);
    }

    @GetMapping("/viewAll")
    public ResponseEntity<?> viewAll(){
        List<Account> accountList=accountService.getAllAccount();
        if(accountList==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(accountList,HttpStatus.OK);
    }

}
