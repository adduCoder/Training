package com.banking.service;

import com.banking.AccountImp.CurrentAccount;
import com.banking.AccountImp.SalaryAccount;
import com.banking.AccountImp.SavingAccount;
import com.banking.entities.Account;
import com.banking.entities.AccountType;
import com.banking.repo.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
