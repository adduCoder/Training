package com.banking.service;

import com.banking.AccountImp.CurrentAccount;
import com.banking.AccountImp.SalaryAccount;
import com.banking.AccountImp.SavingAccount;
import com.banking.ExceptionHandler.*;
import com.banking.entities.Account;
import com.banking.entities.AccountOperation;
import com.banking.entities.AccountType;
import com.banking.repo.AccountRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private TransactionService transactionService;

    public Account addAccount(Integer accountId,Double balance,Integer customerId,String accountType){
        Account newAccount=null;
        switch (accountType) {
            case "SAVING":
                newAccount=new SavingAccount();
                break;
            case "CURRENT":
                newAccount=new CurrentAccount();
                break;
            case "SALARY":
                newAccount= new SalaryAccount();
                break;
            default:
                newAccount=null;
        }
        if(newAccount==null)return null;
        newAccount.setAccountId(accountId);
        newAccount.setAccountType(AccountType.valueOf(accountType));
        newAccount.setBalance(balance);
        newAccount.setCustomerId(customerId);
        return accountRepo.save(newAccount);
    }

    @Transactional
    public void credit(Integer accountId, Double amount) throws NoSuchAccountException, NoSuchAccountTypeException, InvalidOperationException, TransactionsLimitExceeded {
        Optional<Account> optionalAccount=accountRepo.findById(accountId);
        if(optionalAccount.isEmpty())throw new NoSuchAccountException();
        Account account=optionalAccount.get();
        if (account instanceof AccountOperation) {
            ((AccountOperation) account).credit(amount);
            accountRepo.save(account);
            transactionService.createTransaction(accountId,"credit",amount);
        } else {
            throw new NoSuchAccountTypeException();
        }
    }

    @Transactional
    public void debit(Integer accountId, Double amount) throws NoSuchAccountException, NoSuchAccountTypeException, InsufficientBalanceException, InvalidOperationException {
        Optional<Account> optionalAccount=accountRepo.findById(accountId);
        if(optionalAccount.isEmpty())throw new NoSuchAccountException();
        Account account=optionalAccount.get();
        if(account instanceof AccountOperation){
            try {
                ((AccountOperation) account).debit(amount);
            }
            catch (InsufficientBalanceException e) {
                throw new InsufficientBalanceException();
            }
            accountRepo.save(account);
            transactionService.createTransaction(accountId,"debit",amount);
        }
        else{
            throw new NoSuchAccountTypeException();
        }
    }

    public Account viewAccount(Integer accountId) {
        Optional<Account> optionalAccount=accountRepo.findById(accountId);
        if(optionalAccount.isEmpty())return null;
        return optionalAccount.get();
    }

    public List<Account> getAllAccount() {
        List<Account> accountList=accountRepo.findAll();
        return accountList;
    }
}
