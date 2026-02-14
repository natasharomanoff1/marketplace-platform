package com.marketplace.provisioning.application.service;


import com.marketplace.provisioning.application.command.CreateProvisioningCommand;
import com.marketplace.provisioning.application.port.out.persistence.ProvisioningRepositoryPort;
import com.marketplace.provisioning.domain.enums.ProvisioningStatus;
import com.marketplace.provisioning.domain.enums.ProvisioningType;
import com.marketplace.provisioning.domain.id.ProvisioningId;
import com.marketplace.provisioning.domain.model.Provisioning;
import com.marketplace.provisioning.domain.model.ProvisioningItem;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ProvisioningService {

    private final AccessTokenService accessTokenService;

    public Provisioning createProvisioning(CreateProvisioningCommand command) {

        return Provisioning.builder()
                .id(new ProvisioningId(UUID.randomUUID()))
                .orderId(command.getOrderId())
                .buyerId(command.getBuyerId())
                .createdAt(Instant.now())
                .items(
                        command.getItems()
                                .stream()
                                .map(this::toProvisioningItem)
                                .collect(Collectors.toList())
                )
                .build();

    }

    private ProvisioningItem toProvisioningItem(CreateProvisioningCommand.Item item) {

        ProvisioningType type =
                ProvisioningType.valueOf(item.getProvisioningType());

        String tokenHash = null;
        Instant expiresAt = null;

        if (type == ProvisioningType.DIGITAL_ACCESS) {
            // Store only the token hash; the plain token is sent to the buyer
            String plainToken = accessTokenService.generatePlainToken();
            tokenHash = accessTokenService.hash(plainToken);
            expiresAt = Instant.now().plusSeconds(60L * 60 * 24 * 30);
        }

        return ProvisioningItem.builder()
                .productId(item.getProductId())
                .type(type)
                .status(ProvisioningStatus.PENDING)
                .accessTokenHash(tokenHash)
                .expiresAt(expiresAt)
                .build();
    }
}

