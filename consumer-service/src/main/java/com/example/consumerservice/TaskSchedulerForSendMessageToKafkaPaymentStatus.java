package com.example.consumerservice;

import lombok.extern.slf4j.Slf4j;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class TaskSchedulerForSendMessageToKafkaPaymentStatus {

    @KafkaListener(
            topics = "lms",
            groupId = "groupId"
    )
    public void scheduledSelectFromDBTask(String data) throws InterruptedException {

        log.info("Checking status of payment!" + data);
    }
}