package com.marketplace.provisioning.adapters.in.messaging.listener;

import com.marketplace.provisioning.adapters.config.DlqTestConfig;
import com.marketplace.provisioning.adapters.in.messaging.event.OrderPaidEvent;
import com.marketplace.provisioning.adapters.testdata.OrderPaidEventTestDataFactory;
import com.marketplace.provisioning.application.port.in.CreateProvisioningUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringBootTest
@Import(DlqTestConfig.class)
@TestPropertySource(properties = {
        "marketplace.kafka.topic.order-paid=order-paid-topic",
        "marketplace.kafka.bootstrap-servers=${spring.embedded.kafka.brokers}"
})
@EmbeddedKafka(partitions = 1, topics = {"order-paid-topic", "order-paid-dlq"})
class OrderPaidKafkaListenerIntegrationTest {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private DlqTestConfig dlqConfig;

    @MockitoBean
    private CreateProvisioningUseCase useCase;

    @Autowired
    private KafkaListenerEndpointRegistry registry;

    @Value("${marketplace.kafka.topic.order-paid}")
    private String topic;

    @Test
    void shouldConsumeOrderPaidEvent() throws Exception {

        registry.getListenerContainers()
                .forEach(container -> ContainerTestUtils.waitForAssignment(container, 1));

        OrderPaidEvent event = OrderPaidEventTestDataFactory.anOrderPaidEvent();

        CountDownLatch latch = new CountDownLatch(1);

        doAnswer(invocation -> {
            latch.countDown();
            return null;
        }).when(useCase).execute(any());

        kafkaTemplate.send(topic, event);

        assertTrue(latch.await(10, TimeUnit.SECONDS));

        verify(useCase).execute(any());
    }

    @Test
    void shouldSendToDlqWhenProcessingFails() throws Exception {

        OrderPaidEvent event = OrderPaidEventTestDataFactory.anOrderPaidEvent();

        doThrow(new RuntimeException("boom"))
                .when(useCase).execute(any());

        kafkaTemplate.send(topic, event);

        assertTrue(dlqConfig.dlqLatch.await(10, TimeUnit.SECONDS));
    }
}

