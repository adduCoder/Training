package com.banking.AccountImp;

import com.banking.ExceptionHandler.InsufficientBalanceException;
import com.banking.entities.Account;
import com.banking.entities.AccountOperation;
import jakarta.persistence.Entity;

@Entity
public class SavingAccount extends Account implements AccountOperation {
    private final Double thresholdBalance = 2000.0;

    @Override
    public void credit(Double amount) {
        this.setBalance(this.getBalance() + amount);
    }

    @Override
    public void debit(Double amount) throws InsufficientBalanceException {
        if (this.getBalance() - amount >= thresholdBalance) {
            this.setBalance(this.getBalance() - amount);
        } else {
            throw new InsufficientBalanceException();
        }
    }

}
