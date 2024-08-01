package com.banking.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "account")
public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountId;

    @Column(name = "balance")
    private Double balance;

    private Integer customerId;

     @Enumerated(EnumType.STRING)
     private AccountType accountType;

    public Account(Integer accountId, Double balance, Integer customerId, AccountType accountType) {
        this.accountId = accountId;
        this.balance = balance;
        this.customerId = customerId;
        this.accountType = accountType;
    }

    public Account() {
    }

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
        return Objects.equals(accountId, account.accountId) && Objects.equals(balance, account.balance)  && Objects.equals(customerId, account.customerId) && accountType == account.accountType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, balance, customerId, accountType);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", balance=" + balance +
                ", transactionList="  +
                ", customerId=" + customerId +
                ", accountType=" + accountType +
                '}';
    }

}

