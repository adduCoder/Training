package com.banking.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    public String lastName;

    @Column(name = "address")
    public String address;

    private List<Integer> accountList;

    private Integer managerId;

    //
    public Customer(Integer customerId, String firstName, String lastName, String address, List<Integer> accountList, Integer managerId) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.accountList = accountList;
        this.managerId = managerId;
    }

    //

}
