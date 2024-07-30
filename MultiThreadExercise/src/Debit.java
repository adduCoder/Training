import java.util.Objects;

public class Debit implements  Runnable{
    public Bank bank;
    public Double amount;

    public Debit(Bank bank,Double amount){
        this.bank=bank;
        this.amount=amount;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Debit debit)) return false;
        return Objects.equals(bank, debit.bank) && Objects.equals(amount, debit.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bank, amount);
    }

    @Override
    public String toString() {
        return "Debit{" +
                "bank=" + bank +
                ", amount=" + amount +
                '}';
    }

    public  void run(){
        for(int i=0;i<5;i++){
        try {
            bank.debit(amount);
            System.out.println("" + amount + " debited from "+bank.getAccountId());
        }
        catch (ExceptionHandler.InsufficientBalanceException e){
            System.out.println(e.getMessage());
        }
    }
    }
}
