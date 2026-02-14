package com.marketplace.provisioning.adapters.in.messaging.listener;

import com.marketplace.provisioning.adapters.in.messaging.event.OrderPaidEvent;
import com.marketplace.provisioning.adapters.in.messaging.mapper.OrderPaidEventMapper;
import com.marketplace.provisioning.application.port.in.CreateProvisioningUseCase;
import com.marketplace.provisioning.application.service.ProvisioningService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderPaidKafkaListener {

    private final CreateProvisioningUseCase useCase;
    private final OrderPaidEventMapper mapper;

    @KafkaListener(
            topics = "${marketplace.kafka.topic.order-paid}",
            groupId = "${marketplace.kafka.consumer.group-id}",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void handle(OrderPaidEvent event) {

        log.info("Received OrderPaidEvent: orderId={}", event.getOrderId());

        useCase.execute(
                mapper.toCreateProvisioningCommand(event));
    }
}

