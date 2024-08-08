package com.banking.ExceptionHandler;

public class NoSuchAccountException extends RuntimeException {
    public NoSuchAccountException() {
        super("No Account Found Exception");
    }
}
