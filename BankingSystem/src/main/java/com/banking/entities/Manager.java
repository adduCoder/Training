package com.banking.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "manager")
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer managerId;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    private List<Integer> customerList;
 }
