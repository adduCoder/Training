package com.banking.ExceptionHandler;

public class NoCustomerFound extends RuntimeException {
    public NoCustomerFound() {
        super("No Customer Found Exception");
    }
}
