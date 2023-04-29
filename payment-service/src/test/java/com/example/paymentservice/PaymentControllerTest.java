package com.example.paymentservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class PaymentControllerTest {


    @Mock
    private PaymentServiceImpl paymentService;

    @Mock
    private PaymentRequest paymentRequest;

    @Mock
    private PaymentCheckRequest paymentCheckRequest;

    @InjectMocks
    private PaymentController paymentController;


    @BeforeEach
    public void setUp() {

        paymentRequest = new PaymentRequest();
        paymentCheckRequest = new PaymentCheckRequest();

    }


    @Test
    public void testCheckPayment() {

        int id = 1;

        Mockito.when(paymentService.checkPayment(paymentCheckRequest, id)).thenReturn("success");

        ResponseEntity<String> response = paymentController.checkPayment(paymentCheckRequest, id);

        assertEquals("success", response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testAddPayment() {

        int id = 1;

        Mockito.when(paymentService.addPayment(paymentRequest, id)).thenReturn("success");

        ResponseEntity<String> response = paymentController.addPayment(paymentRequest, id);

        assertEquals("success", response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testUpdateStatus() {

        int id = 1;

        Mockito.when(paymentService.setPayment(id)).thenReturn("success");

        ResponseEntity<String> response = paymentController.updateStatus(id);

        assertEquals("success", response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testRollbackStatus() {

        int id = 1;

        Mockito.when(paymentService.rollbackPayment(1)).thenReturn("success");

        ResponseEntity<String> response = paymentController.rollbackStatus(id);

        assertEquals("success", response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}