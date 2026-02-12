package com.marketplace.common.kafka.adapter;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import com.marketplace.common.kafka.port.KafkaProducerPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducerAdapter implements KafkaProducerPort {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public CompletableFuture<SendResult<String, Object>> send(
            String topic,
            String key,
            Object message
    ) {
        log.debug("Publishing to Kafka: topic={}, key={}", topic, key);

        return logResult(
                topic,
                key,
                kafkaTemplate.send(topic, key, message)
        );
    }

    @Override
    public CompletableFuture<SendResult<String, Object>> send(
            String topic,
            String key,
            Object message,
            Map<String, String> headers
    ) {
        ProducerRecord<String, Object> record =
                new ProducerRecord<>(topic, key, message);

        headers.forEach((k, v) ->
                record.headers().add(k, v.getBytes(StandardCharsets.UTF_8))
        );

        log.debug(
                "Publishing to Kafka with headers: topic={}, key={}, headers={}",
                topic, key, headers.keySet()
        );

        return logResult(
                topic,
                key,
                kafkaTemplate.send(record)
        );
    }

    private CompletableFuture<SendResult<String, Object>> logResult(
            String topic,
            String key,
            CompletableFuture<SendResult<String, Object>> future
    ) {
        return future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info(
                        "Kafka send OK: topic={}, partition={}, offset={}",
                        topic,
                        result.getRecordMetadata().partition(),
                        result.getRecordMetadata().offset()
                );
            } else {
                log.error(
                        "Kafka send FAILED: topic={}, key={}, ex={}",
                        topic, key, ex.toString()
                );
            }
        });
    }
}
