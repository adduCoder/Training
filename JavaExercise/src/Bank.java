import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Bank {

    public   void transactionPerform(Account account,int amount,String symbol){
           try {
                if(symbol.equals("+"))account.credit(amount);
                else if(symbol.equals("-"))account.debit(amount);
                else System.out.println("Not a valid transaction");
            }
            catch(NullPointerException e) {
                System.out.println("Invalid account value");
            }
            catch (ExceptionHandler.InsufficientBalanceException e) {
               System.out.println(e.getMessage());
            }
    }

    public void transfer(Account fromAccount,Integer toAccountId,int amount,List<Account> accountList){

        try{
            fromAccount.transferTo(toAccountId,amount,accountList);
        } catch (ExceptionHandler.AccountNotFoundException e) {
            System.out.println(e.getMessage());
        }
        catch(NullPointerException e){
            System.out.print("Invalid account value");
        }
    }

    public   void getAllTransactions(Account account,List<Customer>customerList){
        try {
            account.getAllTransactions(customerList);
        } catch (ExceptionHandler.CustomerNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String args[]) {
        List<Customer> customerList=new ArrayList<>();
        List<Account> accountList=new ArrayList<>();
        Customer c1=new Customer(1, "Alpha", "Patel",Gender.MALE);
        Customer c2=new Customer(2, "Beta", "kumari",Gender.FEMALE);
        Customer c3=new Customer(3, "Gamma", "singh",Gender.OTHERS);
        Customer c4=new Customer(4, "Gamma", "singh", Gender.OTHERS);

        customerList.add(c1);
        customerList.add(c2);
        customerList.add(c3);
        customerList.add(c4);

        Account account1=new Account(1,1,100);
        Account account2=new Account(2,2,100);
        Account account3=new Account(3,3,100);
        accountList.add(account1);
        accountList.add(account2);

        Bank bank =new Bank();
        bank.transfer(account1,2,10000,accountList);
        bank.transactionPerform(account1,100,"-");
        bank.transactionPerform(account1,100,"+");
        bank.transactionPerform(null,100,"+");
        bank.transfer(account1,2,30,accountList);
        bank.getAllTransactions(account1,customerList);
        account3.getAllTransactions(customerList);
        System.out.println("\n\n");
        c1.getGenderCount(customerList);
        bank.transfer(null,1,10,accountList);
        }
}

