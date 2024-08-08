package com.banking.service;

import com.banking.AccountImp.CurrentAccount;
import com.banking.AccountImp.SalaryAccount;
import com.banking.AccountImp.SavingAccount;
import com.banking.ExceptionHandler.*;
import com.banking.entities.Account;
import com.banking.entities.AccountOperation;
import com.banking.entities.AccountType;
import com.banking.entities.TransactionType;
import com.banking.repo.AccountRepo;
import jakarta.transaction.Transactional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AccountService {


    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private TransactionService transactionService;

    public Account addAccount(Integer accountId, Double balance, Integer customerId, String accountType) throws InvalidOperationException {
        log.info("attempting to create account");
        Account newAccount = null;
        switch (accountType) {
            case "SAVING":
                newAccount = new SavingAccount();
                break;
            case "CURRENT":
                newAccount = new CurrentAccount();
                break;
            case "SALARY":
                newAccount = new SalaryAccount();
                break;
            default:
                newAccount = null;
        }
        if (newAccount == null) throw new InvalidOperationException();
        newAccount.setAccountId(accountId);
        newAccount.setAccountType(AccountType.valueOf(accountType));
        if(balance!=null)
            newAccount.setBalance(balance);
        newAccount.setCustomerId(customerId);
        log.info("account saving");
        return accountRepo.save(newAccount);
    }

    @Transactional
    public void credit(Integer accountId, Double amount) throws NoSuchAccountException, NoSuchAccountTypeException, InvalidOperationException, TransactionsLimitExceeded {
        log.info("attempting to credit balance");
        Optional<Account> optionalAccount = accountRepo.findById(accountId);
        if (optionalAccount.isEmpty()) throw new NoSuchAccountException();
        Account account = optionalAccount.get();
        if (account instanceof AccountOperation) {
            ((AccountOperation) account).credit(amount);
            accountRepo.save(account);
            transactionService.createTransaction(accountId, String.valueOf(TransactionType.CREDIT), amount);
            log.info("amount credited successfull");
        } else {
            log.warn("account not found");
            throw new NoSuchAccountTypeException();
        }
    }

    @Transactional
    public void debit(Integer accountId, Double amount) throws InsufficientBalanceException, NoSuchAccountException {
        Optional<Account> optionalAccount = accountRepo.findById(accountId);
        if (optionalAccount.isEmpty()) throw new NoSuchAccountException();
        Account account = optionalAccount.get();
        ((AccountOperation) account).debit(amount);
        accountRepo.save(account);
        transactionService.createTransaction(accountId, String.valueOf(TransactionType.DEBIT), amount);
    }

    public Account viewAccount(Integer accountId) throws NoSuchAccountException {
        Optional<Account> optionalAccount = accountRepo.findById(accountId);
        if (optionalAccount.isEmpty()) throw new NoSuchAccountException();
        return optionalAccount.get();
    }

    public List<Account> getAllAccount() {
        List<Account> accountList = accountRepo.findAll();
        return accountList;
    }

    public Account deleteAccount(Integer accountId) {
        Optional<Account> optionalAccount = accountRepo.findById(accountId);
        if (optionalAccount.isEmpty()) {
            throw new NoSuchAccountException();
        }
        Account account = optionalAccount.get();
        accountRepo.deleteById(accountId);
        return account;
    }
}
