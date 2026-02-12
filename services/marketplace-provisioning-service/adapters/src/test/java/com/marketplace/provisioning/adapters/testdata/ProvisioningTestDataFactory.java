package com.marketplace.provisioning.adapters.testdata;

import com.marketplace.provisioning.adapters.out.persistence.document.ProvisioningDocument;
import com.marketplace.provisioning.domain.enums.ProvisioningStatus;
import com.marketplace.provisioning.domain.enums.ProvisioningType;
import com.marketplace.provisioning.domain.id.*;
import com.marketplace.provisioning.domain.model.Provisioning;
import com.marketplace.provisioning.domain.model.ProvisioningItem;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public final class ProvisioningTestDataFactory {

    private ProvisioningTestDataFactory() {}

    public static Provisioning aProvisioning() {

        UUID provisioningId = UUID.randomUUID();
        UUID orderId = UUID.randomUUID();
        UUID buyerId = UUID.randomUUID();

        return Provisioning.builder()
                .id(new ProvisioningId(provisioningId))
                .orderId(new OrderId(orderId))
                .buyerId(new BuyerId(buyerId))
                .items(List.of(
                        aProvisioningItem(),
                        aProvisioningItem()
                ))
                .createdAt(Instant.now())
                .build();
    }

    public static ProvisioningItem aProvisioningItem() {
        return ProvisioningItem.builder()
                .productId(new ProductId(UUID.randomUUID()))
                .type(ProvisioningType.DIGITAL_ACCESS)
                .status(ProvisioningStatus.READY)
                .accessToken("token-123")
                .shipmentId(new ShipmentId(UUID.randomUUID()))
                .expiresAt(Instant.now())
                .build();
    }

}

