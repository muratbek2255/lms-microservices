package com.example.courseservice;


import com.example.courseservice.dto.PaymentCheckRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "payment-service", url = "http://localhost:8087")
public interface PaymentClient {

    @PostMapping("/payment/check/{id}")
    public ResponseEntity<String> checkPayment(@RequestBody PaymentCheckRequest paymentRequest, @PathVariable long id);
}
