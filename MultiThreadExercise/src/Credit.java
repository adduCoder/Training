import java.util.Objects;

public class Credit implements  Runnable{
    public Bank bank;
    public Double amount;

    public Credit(Bank bank,Double amount){
        this.bank=bank;
        this.amount=amount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Credit credit)) return false;
        return Objects.equals(bank, credit.bank) && Objects.equals(amount, credit.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bank, amount);
    }

    @Override
    public String toString() {
        return "Credit{" +
                "bank=" + bank +
                ", amount=" + amount +
                '}';
    }

    public  void run() {
        for (int i = 0; i < 5; i++) {
            bank.credit(amount);
            System.out.println(amount + " credited to " + bank.getAccountId());
        }
    }
}
