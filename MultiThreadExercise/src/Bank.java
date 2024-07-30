import java.util.Objects;

public class Bank {
    public Integer accountId;
    public String firstName;
    public String lastName;
    public Double balance;

    public Bank(Integer accountId,String firstName,String lastName,Double balance){
        this.accountId=accountId;
        this.firstName=firstName;
        this.lastName=lastName;
        this.balance=balance;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bank bank)) return false;
        return Objects.equals(accountId, bank.accountId) && Objects.equals(firstName, bank.firstName) && Objects.equals(lastName, bank.lastName) && Objects.equals(balance, bank.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, firstName, lastName, balance);
    }

    @Override
    public String toString() {
        return "Bank{" +
                "accountId=" + accountId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", balance=" + balance +
                '}';
    }

    public synchronized void credit(Double amount){
        balance+=amount;
    }

    public synchronized void debit(Double amount) throws ExceptionHandler.InsufficientBalanceException{
        if(amount<=balance) {
            balance -= amount;
        }
        else{
            throw new ExceptionHandler.InsufficientBalanceException();
        }
    }

    public void getDetails(){
        System.out.println("Account Id "+accountId);
        System.out.println("Name : "+firstName+" "+lastName);
        System.out.println(balance);
    }

    public static void main(String args[]){
        Bank bank=new Bank(101,"Alpha","Patel",100.0);
        Credit credit=new Credit(bank, 20.0);
        Thread creditAmount=new Thread(credit);
        Debit debit=new Debit(bank,50.0);
        Thread debitAmount=new Thread(debit);

        debitAmount.start();
        creditAmount.start();

      }
}
