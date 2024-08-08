package com.banking.AccountImp;

import com.banking.ExceptionHandler.InsufficientBalanceException;
import com.banking.ExceptionHandler.InvalidOperationException;
import com.banking.ExceptionHandler.NoSuchAccountException;
import com.banking.entities.Account;
import com.banking.entities.AccountOperation;
import com.banking.entities.AccountType;
import jakarta.persistence.Entity;

@Entity
public class SalaryAccount extends Account implements AccountOperation {
    private Double maxLimit=100000.0;

    @Override
    public void credit(Double amount) throws InvalidOperationException {
        if(this.getBalance()+amount>maxLimit){
            throw new InvalidOperationException();
        }
        else this.setBalance(this.getBalance()+amount);
    }

    @Override
    public void debit(Double amount) throws InsufficientBalanceException {
        if (amount > 0 && this.getBalance() >= amount) {
            this.setBalance(this.getBalance() - amount);
        } else {
            throw new InsufficientBalanceException();
        }
    }
}
