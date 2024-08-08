package com.banking.ExceptionHandler;

public class TransactionsLimitExceeded extends Exception {
    public TransactionsLimitExceeded() {
        super("Transactions Limit Exceeded");
    }
}
