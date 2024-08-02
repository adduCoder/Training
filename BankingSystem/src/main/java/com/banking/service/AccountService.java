package com.banking.service;

import com.banking.AccountImp.CurrentAccount;
import com.banking.AccountImp.SalaryAccount;
import com.banking.AccountImp.SavingAccount;
import com.banking.ExceptionHandler.*;
import com.banking.entities.Account;
import com.banking.entities.AccountOperation;
import com.banking.entities.AccountType;
import com.banking.repo.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepo accountRepo;

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

    public void credit(Integer accountId, Double amount) throws NoSuchAccountException, NoSuchAccountTypeException, InvalidOperationException, TransactionsLimitExceeded {
        Optional<Account> optionalAccount=accountRepo.findById(accountId);
        if(optionalAccount.isEmpty())throw new NoSuchAccountException();
        Account account=optionalAccount.get();
        if (account instanceof AccountOperation) {
            ((AccountOperation) account).credit(amount);
            accountRepo.save(account);
        } else {
            throw new NoSuchAccountTypeException();
        }
    }

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
        }
        else{
            throw new NoSuchAccountTypeException();
        }
    }
}
