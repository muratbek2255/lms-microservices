package com.example.paymentservice;

public interface PaymentService {

    public String checkPayment(PaymentCheckRequest paymentCheckRequest, int courseId);

    public String addPayment(PaymentRequest paymentRequest, int id);

    public String setPayment(int id);

    public String rollbackPayment(int id);
}
