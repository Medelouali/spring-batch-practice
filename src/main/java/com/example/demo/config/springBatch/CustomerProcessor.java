package com.example.demo.config.springBatch;

import com.example.demo.models.Customer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class CustomerProcessor implements ItemProcessor<Customer, Customer> {
    @Override
    public Customer process(Customer item) throws Exception {
        System.out.println("Processing user:\t"+item);
        return new Customer(item.getUsername(), item.getEmail(), item.getAge()+200);
    }
}
