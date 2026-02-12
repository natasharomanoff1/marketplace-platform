package com.marketplace.provisioning.adapters.in.messaging.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderPaidEvent {

    private String orderId;
    private String buyerId;

    private List<OrderPaidItem> items;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderPaidItem {
        private String productId;
        private String provisioningType; // DIGITAL_ACCESS | PHYSICAL_SHIPMENT | LICENSE
    }
}
