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
import org.springframework.kafka.core.KafkaTemplate;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {
    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private CourseClient courseClient;

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

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

        verify(paymentRepository, Mockito.times(0)).save(any(Payment.class));
    }

    @Test
    void testCheckPaymentInvalidSumOfFavour() {
        PaymentCheckRequest paymentCheckRequest = new PaymentCheckRequest();
        paymentCheckRequest.setSumOfFavour(100);
        paymentCheckRequest.setAccountCheck("valid");

        String result = paymentService.checkPayment(paymentCheckRequest, 1);

        assertEquals("Error, you have problem with sumOfFavour or AccountCheck!", result);

        verify(paymentRepository, Mockito.times(0)).save(any(Payment.class));
    }

    @Test
    void testCheckPaymentNullAccountCheck() {
        PaymentCheckRequest paymentCheckRequest = new PaymentCheckRequest();
        paymentCheckRequest.setSumOfFavour(1000);
        paymentCheckRequest.setAccountCheck(null);

        String result = paymentService.checkPayment(paymentCheckRequest, 1);

        assertEquals("Error, you have problem with sumOfFavour or AccountCheck!", result);

        verify(paymentRepository, Mockito.times(0)).save(any(Payment.class));
    }

    @Test
    void testOnCreatePaymentOrNot() {

        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setPrice(100);
        paymentRequest.setAccountCheck("testAccount");

        Payment payment = new Payment();
        payment.setId(1);
        payment.setIsChecked(true);

        when(paymentRepository.getById(1)).thenReturn(payment);

        paymentService = new PaymentServiceImpl(paymentRepository, courseClient, kafkaTemplate);

        String result = paymentService.addPayment(paymentRequest, 1);
        assertEquals("Payment Created", result);

        verify(payment, Mockito.times(3)).setStatus(PaymentStatus.STATUS_CREATED);
        verify(payment).setCreated_at(any(Timestamp.class));
        verify(payment).setFinished(false);
        verify(payment).setSumOfFavour(100);
        verify(payment).setAccountCheck("testAccount");
        verify(paymentRepository).save(payment);

        verify(kafkaTemplate).send(eq("lms"), contains("Payment id: 1 and his payment status: CREATED"));
    }
}