package com.example.paymentservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    @Query(value = "SELECT * FROM payments WHERE payments.id = ?1", nativeQuery = true)
    Payment getById(@Param("id") int id);

    @Query(value = "SELECT * FROM payments WHERE payments.status = ?1", nativeQuery = true)
    List<Payment> getByStatus(@Param("status") String status);
}
