package com.example.demo.models;

import com.example.demo.config.springBatch.CustomerWriter;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String customerId;

    String username;
    String email;
    int age;

    @OneToMany(mappedBy = "customer")
    private List<Product> products=new ArrayList<>();

    public Customer(String username, String email, int age) {
        this.username = username;
        this.email = email;
        this.age = age;
    }
}
