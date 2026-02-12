package com.marketplace.provisioning.adapters.testdata;

import com.marketplace.provisioning.adapters.in.messaging.event.OrderPaidEvent;

import java.util.List;
import java.util.UUID;

public final class OrderPaidEventTestDataFactory {

    private OrderPaidEventTestDataFactory() {}

    public static OrderPaidEvent anOrderPaidEvent() {

        return OrderPaidEvent.builder()
                .orderId(UUID.randomUUID().toString())
                .buyerId(UUID.randomUUID().toString())
                .items(List.of(
                        OrderPaidEvent.OrderPaidItem.builder()
                                .productId(UUID.randomUUID().toString())
                                .provisioningType("DIGITAL")
                                .build()
                ))
                .build();
    }
}

