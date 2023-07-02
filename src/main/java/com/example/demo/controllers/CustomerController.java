package com.example.demo.controllers;

import com.example.demo.dtos.AgeEffectiveProjection;
import com.example.demo.dtos.CustomerDto;
import com.example.demo.models.Customer;
import com.example.demo.services.CustomerService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping()
    public List<Customer> getCustomers(){
        return  this.customerService.getCustomers();
    }

    @GetMapping("/adults")
    public List<Customer> getAdults(@RequestParam(required = false, defaultValue = "18") int threshHold){
        return  this.customerService.getAdults(threshHold);
    }

    @GetMapping("/children")
    public List<Customer> getChildren(@RequestParam(required = false, defaultValue = "18") int threshHold){
        return  this.customerService.getChildren(threshHold);
    }

    @GetMapping("/statistics")
    public List<AgeEffectiveProjection> getAgeEffectiveCounts(){
        return  this.customerService.getAgeEffectiveCounts();
    }
}
