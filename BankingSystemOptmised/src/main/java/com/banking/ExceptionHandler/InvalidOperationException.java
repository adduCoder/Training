package com.banking.ExceptionHandler;

public class InvalidOperationException extends Exception {
    public InvalidOperationException() {
        super("Operation Cant be performed");
    }
}
