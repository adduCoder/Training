package com.banking.AccountImp;

import com.banking.ExceptionHandler.InsufficientBalanceException;
import com.banking.ExceptionHandler.TransactionsLimitExceeded;
import com.banking.entities.Account;
import com.banking.entities.AccountOperation;
import jakarta.persistence.Entity;

@Entity
public class CurrentAccount extends Account implements AccountOperation {
    private Integer maxTransactions=20;
    private Integer transactionsCount=0;

    @Override
    public void credit(Double amount) throws TransactionsLimitExceeded {
        if(transactionsCount>maxTransactions)throw new TransactionsLimitExceeded();
        transactionsCount++;
        this.setBalance(this.getBalance()+amount);
    }

    @Override
    public void debit(Double amount) throws InsufficientBalanceException {
        if (amount > 0 && this.getBalance() >= amount) {
            transactionsCount++;
            this.setBalance(this.getBalance() - amount);
        } else {
            throw new InsufficientBalanceException();
        }
    }
    
}
