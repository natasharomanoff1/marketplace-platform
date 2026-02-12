package com.marketplace.provisioning.adapters.testdata;

import com.marketplace.provisioning.adapters.out.persistence.document.ProvisioningDocument;
import com.marketplace.provisioning.adapters.out.persistence.document.ProvisioningItemDocument;
import com.marketplace.provisioning.domain.enums.ProvisioningStatus;
import com.marketplace.provisioning.domain.enums.ProvisioningType;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public final class ProvisioningDocumentTestDataFactory {

    private ProvisioningDocumentTestDataFactory() {}

    public static ProvisioningDocument aProvisioningDocument() {
        return ProvisioningDocument.builder()
                .id(UUID.randomUUID().toString())
                .orderId(UUID.randomUUID().toString())
                .buyerId(UUID.randomUUID().toString())
                .items(List.of(
                        aProvisioningItemDocument(),
                        aProvisioningItemDocument()
                ))
                .createdAt(Instant.now())
                .build();
    }

    public static ProvisioningItemDocument aProvisioningItemDocument() {
        return ProvisioningItemDocument.builder()
                .productId(UUID.randomUUID().toString())
                .type(ProvisioningType.DIGITAL_ACCESS)
                .status(ProvisioningStatus.READY)
                .accessToken("token-123")
                .shipmentId(UUID.randomUUID().toString())
                .expiresAt(Instant.now())
                .build();
    }
}

