package com.example.paymentservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
class PaymentServiceImplTest {
    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private CourseClient courseClient;

    @InjectMocks
    private PaymentServiceImpl paymentService;


    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCheckPaymentValid() {
        PaymentCheckRequest paymentCheckRequest = new PaymentCheckRequest();
        paymentCheckRequest.setSumOfFavour(1000);
        paymentCheckRequest.setAccountCheck("valid");

        String result = paymentService.checkPayment(paymentCheckRequest, 1);

        assertEquals("Order is checked!", result);

        Mockito.verify(paymentRepository, Mockito.times(0)).save(Mockito.any(Payment.class));
    }

    @Test
    void testCheckPaymentInvalidSumOfFavour() {
        PaymentCheckRequest paymentCheckRequest = new PaymentCheckRequest();
        paymentCheckRequest.setSumOfFavour(100);
        paymentCheckRequest.setAccountCheck("valid");

        String result = paymentService.checkPayment(paymentCheckRequest, 1);

        assertEquals("Error, you have problem with sumOfFavour or AccountCheck!", result);

        Mockito.verify(paymentRepository, Mockito.times(0)).save(Mockito.any(Payment.class));
    }

    @Test
    void testCheckPaymentNullAccountCheck() {
        PaymentCheckRequest paymentCheckRequest = new PaymentCheckRequest();
        paymentCheckRequest.setSumOfFavour(1000);
        paymentCheckRequest.setAccountCheck(null);

        String result = paymentService.checkPayment(paymentCheckRequest, 1);

        assertEquals("Error, you have problem with sumOfFavour or AccountCheck!", result);

        Mockito.verify(paymentRepository, Mockito.times(0)).save(Mockito.any(Payment.class));
    }

    @Test
    void testOnCreatePaymentOrNot() {

        PaymentRequest paymentCheckRequest = new PaymentRequest();

        Payment payment = paymentRepository.getById(1);

        payment.setStatus(PaymentStatus.STATUS_CREATED);
        payment.setCreated_at(Timestamp.from(Instant.now()));
        payment.setFinished(Boolean.FALSE);
        payment.setSumOfFavour(paymentCheckRequest.getPrice());
        payment.setAccountCheck(paymentCheckRequest.getAccountCheck());

        paymentCheckRequest.setPrice(payment.getSumOfFavour());
        paymentCheckRequest.setAccountCheck(payment.getAccountCheck());

        String result = paymentService.addPayment(paymentCheckRequest, 1);

        assertEquals("You have some with problem with AccountCheck or SumOfFavour", result);

        Mockito.verify(paymentRepository, Mockito.times(0)).save(Mockito.any(Payment.class));
    }
}