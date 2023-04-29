package com.example.paymentservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;


@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    private final CourseClient courseClient;

    private final KafkaTemplate<String, String> kafkaTemplate;


    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, CourseClient courseClient, KafkaTemplate<String, String> kafkaTemplate) {
        this.paymentRepository = paymentRepository;
        this.courseClient = courseClient;
        this.kafkaTemplate = kafkaTemplate;
    }


    @Override
    public String checkPayment(PaymentCheckRequest paymentCheckRequest, int courseId) {

        if(paymentCheckRequest.getSumOfFavour() > 500 && paymentCheckRequest.getSumOfFavour() < 25000 &&
                paymentCheckRequest.getAccountCheck() != null) {

            Payment payment = new Payment();

            payment.setIsChecked(Boolean.TRUE);
            payment.setCourseId(courseId);

            paymentRepository.save(payment);

            return "Order is checked!";

        } else  {

            return "Error, you have problem with sumOfFavour or AccountCheck!";

        }
    }


    @Override
    public String addPayment(PaymentRequest paymentRequest, int id) {

        Payment payment = paymentRepository.getById(id);

        payment.setStatus(PaymentStatus.STATUS_PROCESS);

        kafkaTemplate.send("lms", "Payment id: " + payment.getId() +
                " and his payment status: " + payment.getStatus());

        if(payment.getIsChecked()) {

            payment.setStatus(PaymentStatus.STATUS_CREATED);
            payment.setCreated_at(Timestamp.from(Instant.now()));
            payment.setFinished(Boolean.FALSE);
            payment.setSumOfFavour(paymentRequest.getPrice());
            payment.setAccountCheck(paymentRequest.getAccountCheck());

            paymentRepository.save(payment);

            return "Payment Created";

        } else {
            return "You have some with problem with AccountCheck or SumOfFavour";
        }
    }


    @Override
    public String setPayment(int id) {
        Payment payment = paymentRepository.getById(id);

        payment.setFinished(Boolean.TRUE);

        if(payment.getFinished()) {
            payment.setStatus(PaymentStatus.STATUS_SUCCESS);

            courseClient.updateIsPaymentField(payment.getCourseId());

            return "Payment is Success";
        }

        payment.setUpdated_at(Timestamp.from(Instant.now()));

        paymentRepository.save(payment);

        return "Your status in payment: " + payment.getStatus();
    }


    @Override
    public String rollbackPayment(int id) {

        Payment payment = paymentRepository.getById(id);

        switch (payment.getStatus()) {
            case STATUS_SUCCESS -> {
                int Milli = (int) Math.abs(payment.getUpdated_at().getTime() - new Date().getTime());

                if(Milli < 1080000) {
                    payment.setStatus(PaymentStatus.STATUS_ROLLBACK);
                    payment.setUpdated_at(Timestamp.from(Instant.now()));
                    paymentRepository.save(payment);
                    return "Payment Rollbacked";
                }
                else return "3 days gone";

            }
            case STATUS_CREATED -> {
                payment.setStatus(PaymentStatus.STATUS_ROLLBACK);
                payment.setUpdated_at(Timestamp.from(Instant.now()));
                paymentRepository.save(payment);
                return "Payment Rollbacked";
            }
            default -> {
                return "You don't have payment or your status fail";
            }
        }
    }
}