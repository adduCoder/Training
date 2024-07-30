public class ExceptionHandler {
    public static class InsufficientBalanceException extends Exception {
        public InsufficientBalanceException(){
            super("Insufficient Balance Exception!");
        }
    }
}
