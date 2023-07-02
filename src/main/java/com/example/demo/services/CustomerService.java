package com.example.demo.services;

import com.example.demo.dtos.AgeEffectiveProjection;
import com.example.demo.dtos.CustomerDto;
import com.example.demo.models.Customer;
import com.example.demo.repositories.CustomerRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final JobLauncher jobLauncher;
    private final Job job;

    public List<Customer> getCustomers(){
        return  this.customerRepository.findAll();
    }

    public List<Customer> getAdults(int threshHold){
        return this.customerRepository.getAdults(threshHold);
    }

    public List<Customer> getChildren(int threshHold){
        return this.customerRepository.getChildren(threshHold);
    }

    public List<AgeEffectiveProjection> getAgeEffectiveCounts(){
        List<AgeEffectiveProjection> ageEffectiveCounts = this.customerRepository.getAgeEffectiveCounts().stream()
                .map(result -> new AgeEffectiveProjection((Integer) result[0], (Long) result[1]))
                .collect(Collectors.toList());
        return ageEffectiveCounts;
    }

    public BatchStatus loadCustomersToDb() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        Map<String, JobParameter<?>> params=new HashMap<>();
        params.put("time", new JobParameter<Long>(System.currentTimeMillis(), Long.class));
        JobParameters jobParameters=new JobParameters(params);
        JobExecution jobExecution = this.jobLauncher.run(this.job, jobParameters);
        System.out.println("JobExecutionL\t"+jobExecution.getStatus());
        System.out.println("Batch is running...");
        while(jobExecution.isRunning()){
            System.out.println("...");
        }
        return jobExecution.getStatus();
    }
}
