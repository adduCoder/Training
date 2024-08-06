public class ExceptionHandler {

    public static class InsufficientBalanceException extends  RuntimeException{
        InsufficientBalanceException(){
            super("InsufficentBalance");
        }
    }

    public static class AccountNotFoundException extends  RuntimeException{
        AccountNotFoundException(){
            super("No Such Account Found");
        }
    }

    public  static class CustomerNotFoundException extends  RuntimeException{
        CustomerNotFoundException(){
        super("No Such Customer Found!");
    }

    }
}
