package com.banking.ExceptionHandler;

public class NoManagerFound extends RuntimeException {
    public NoManagerFound() {
        super("No Customer Found Exception");
    }
}
