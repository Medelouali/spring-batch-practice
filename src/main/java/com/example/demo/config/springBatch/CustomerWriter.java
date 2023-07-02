package com.example.demo.config.springBatch;

import com.example.demo.models.Customer;
import com.example.demo.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomerWriter implements ItemWriter<Customer> {
    private final CustomerRepository customerRepository;

    @Override
    public void write(Chunk<? extends Customer> chunk) throws Exception {
        System.out.println("Data Saved For Users:\t"+ chunk);
        this.customerRepository.saveAll(chunk);
    }
}
