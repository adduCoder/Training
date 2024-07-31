package com.banking.entities;

import jakarta.persistence.*;

import java.util.List;

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


}

