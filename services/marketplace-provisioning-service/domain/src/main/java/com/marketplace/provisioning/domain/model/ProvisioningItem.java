package com.marketplace.provisioning.domain.model;

import com.marketplace.provisioning.domain.enums.ProvisioningStatus;
import com.marketplace.provisioning.domain.enums.ProvisioningType;
import com.marketplace.provisioning.domain.id.ProductId;
import com.marketplace.provisioning.domain.id.ShipmentId;
import lombok.*;

import java.time.Instant;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProvisioningItem {

    private ProductId productId;
    private ProvisioningType type;
    private ProvisioningStatus status;
    private String accessToken;
    private ShipmentId shipmentId;
    private Instant expiresAt;
}

