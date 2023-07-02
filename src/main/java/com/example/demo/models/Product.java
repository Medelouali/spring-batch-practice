package com.example.demo.models;

import jakarta.persistence.*;

@Entity
@Table
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String productId;

    @ManyToOne
    @JoinColumn(name="customerId")
    private Customer customer;
}
