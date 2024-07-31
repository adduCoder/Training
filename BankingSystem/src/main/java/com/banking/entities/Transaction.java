package com.banking.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "localDateTime")
    private LocalDateTime localDateTime;

    private Integer accountId;

}
