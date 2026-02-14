package com.marketplace.provisioning.adapters.out.persistence.document;

import com.marketplace.provisioning.domain.enums.ProvisioningStatus;
import com.marketplace.provisioning.domain.enums.ProvisioningType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProvisioningItemDocument {

    private String productId;
    private ProvisioningType type;
    private ProvisioningStatus status;
    private String accessTokenHash;
    private String shipmentId;
    private Instant expiresAt;
}

