package com.banking.entities;

import com.banking.ExceptionHandler.InsufficientBalanceException;
import com.banking.ExceptionHandler.InvalidOperationException;
import com.banking.ExceptionHandler.TransactionsLimitExceeded;

public interface AccountOperation {
    void credit(Double amount) throws InvalidOperationException, TransactionsLimitExceeded;

    void debit(Double amount) throws InsufficientBalanceException;
}
