package com.marketplace.provisioning.adapters.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.concurrent.CountDownLatch;

@TestConfiguration
public class DlqTestConfig {

    public final CountDownLatch dlqLatch = new CountDownLatch(1);
    public Object received;

    @KafkaListener(topics = "order-paid-dlq", groupId = "test-dlq-group")
    public void listen(Object msg) {
        received = msg;
        dlqLatch.countDown();
    }
}

