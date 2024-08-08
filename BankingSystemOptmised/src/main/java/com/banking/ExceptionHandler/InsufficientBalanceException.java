package com.banking.ExceptionHandler;

public class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException() {
        super("Balance is insufficient");
    }
}
