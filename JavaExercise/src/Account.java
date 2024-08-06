import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Account {
    private Integer accountId;
    private int balance;
    private int customerId;
    private List<String> transactions;

    public Account(int accountId,int customerId,int balance){
        this.customerId=customerId;
        this.transactions=new ArrayList<String>();
        this.accountId=accountId;
        this.balance=balance;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getBalance(){
        return balance;
    }


    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public List<String> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<String> transactions) {
        this.transactions = transactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account account)) return false;
        return balance == account.balance && customerId == account.customerId && Objects.equals(accountId, account.accountId) && Objects.equals(transactions, account.transactions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, balance, customerId, transactions);
    }

    public void credit(int amount){
        balance+=amount;
        transactions.add(" "+amount+" $credited");
    }

    public void getAccountHolderName(int customerId,List<Customer> customerList) throws ExceptionHandler.CustomerNotFoundException{
        for(Customer customer:customerList) {
            if (customer.getCustomerId() == customerId) {
                System.out.println(customer.getFirstName() + " " + customer.getLastName());
                return;
            }
        }
        throw new ExceptionHandler.CustomerNotFoundException();
    }

    public void debit(int amount) throws ExceptionHandler.InsufficientBalanceException{
        if(amount>balance){
            transactions.add("Unsuccessfull Transaction");
            throw new ExceptionHandler.InsufficientBalanceException();
        }
        balance-=amount;
        transactions.add(" "+amount+" $debited");
    }

    void transferTo(Integer accountId, int amount, List<Account>accountList) throws ExceptionHandler.AccountNotFoundException {
        accountList.forEach(account->{
            if (account.getAccountId().equals(accountId)) {
                this.balance -= amount;
                account.balance += amount;
                account.transactions.add(" " + amount + " $received from " + this.accountId);
                transactions.add(" " + amount + " $transfered to " + account.accountId);
                return;
            }
        });
        throw new ExceptionHandler.AccountNotFoundException();
    }

    public void getAllTransactions(List<Customer>customerList) throws ExceptionHandler.CustomerNotFoundException{
        System.out.print("\nTransactions History of : ");
        getAccountHolderName(customerId,customerList);
        System.out.println("AccountId : "+accountId);
        System.out.println("CustomerId : "+customerId);
        System.out.print("Transactions List :\n");

        if(transactions.isEmpty()){
            System.out.println("Nill");
            return;
        }

        Stream<String> stringStream=transactions.stream();
        stringStream.forEach(System.out::println);
    }

}
