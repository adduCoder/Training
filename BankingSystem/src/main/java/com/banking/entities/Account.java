package com.banking.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountId;

    @Column(name = "balance")
    private Double balance;

    private List<Integer> transactionList;

    private Integer customerId;

    private AccountType accountType;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public List<Integer> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Integer> transactionList) {
        this.transactionList = transactionList;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account account)) return false;
        return Objects.equals(accountId, account.accountId) && Objects.equals(balance, account.balance) && Objects.equals(transactionList, account.transactionList) && Objects.equals(customerId, account.customerId) && accountType == account.accountType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, balance, transactionList, customerId, accountType);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", balance=" + balance +
                ", transactionList=" + transactionList +
                ", customerId=" + customerId +
                ", accountType=" + accountType +
                '}';
    }
}

