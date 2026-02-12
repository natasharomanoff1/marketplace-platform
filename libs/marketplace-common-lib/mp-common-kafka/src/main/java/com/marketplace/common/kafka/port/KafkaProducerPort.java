package com.marketplace.common.kafka.port;

import org.springframework.kafka.support.SendResult;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface KafkaProducerPort {

    CompletableFuture<SendResult<String, Object>> send(
            String topic,
            String key,
            Object message
    );

    CompletableFuture<SendResult<String, Object>> send(
            String topic,
            String key,
            Object message,
            Map<String, String> headers
    );
}
