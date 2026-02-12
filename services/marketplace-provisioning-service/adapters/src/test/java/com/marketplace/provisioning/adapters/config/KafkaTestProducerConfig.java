package com.marketplace.provisioning.adapters.config;

import com.marketplace.provisioning.adapters.in.messaging.event.OrderPaidEvent;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import java.util.Map;

@TestConfiguration
public class KafkaTestProducerConfig {

    @Bean
    public ProducerFactory<String, OrderPaidEvent> producerFactory(
            EmbeddedKafkaBroker broker) {

        Map<String, Object> props =
                KafkaTestUtils.producerProps(broker);

        return new DefaultKafkaProducerFactory<>(
                props,
                new StringSerializer(),
                new JsonSerializer<>());
    }

    @Bean
    public KafkaTemplate<String, OrderPaidEvent> kafkaTemplate(
            ProducerFactory<String, OrderPaidEvent> pf) {
        return new KafkaTemplate<>(pf);
    }
}

