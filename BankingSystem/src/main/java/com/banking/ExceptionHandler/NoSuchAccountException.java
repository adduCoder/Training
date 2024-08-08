package com.banking.ExceptionHandler;

public class NoSuchAccountException extends  Exception{
        public NoSuchAccountException(){
               super("No Account Found Exception");
        }
}
