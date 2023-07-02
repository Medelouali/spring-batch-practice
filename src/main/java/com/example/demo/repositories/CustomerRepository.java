package com.example.demo.repositories;

import com.example.demo.dtos.AgeEffectiveProjection;
import com.example.demo.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

    @Query("select c from Customer c where c.age > :threshHold")
    List<Customer> getAdults(@Param("threshHold") int threshHold);

    @Query("select c from Customer c where c.age <= :threshHold")
    List<Customer> getChildren(@Param("threshHold") int threshHold);

    @Query(value = "SELECT c.age AS age, COUNT(*) AS effective FROM customers c GROUP BY c.age", nativeQuery = true)
    List<Object[]> getAgeEffectiveCounts();

}
