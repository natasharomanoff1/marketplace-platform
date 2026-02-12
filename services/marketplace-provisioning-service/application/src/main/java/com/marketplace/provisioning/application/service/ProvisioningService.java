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

    private final ProvisioningRepositoryPort repository;

    public void createProvisioningFromEvent(CreateProvisioningCommand command) {

        Provisioning provisioning = Provisioning.builder()
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

        repository.save(provisioning);
    }

    private ProvisioningItem toProvisioningItem(CreateProvisioningCommand.Item item) {

        ProvisioningType type =
                ProvisioningType.valueOf(item.getProvisioningType());

        return ProvisioningItem.builder()
                .productId(item.getProductId())
                .type(type)
                .status(ProvisioningStatus.PENDING)
                .accessToken(
                        type == ProvisioningType.DIGITAL_ACCESS
                                ? UUID.randomUUID().toString()
                                : null
                )
                .expiresAt(
                        type == ProvisioningType.DIGITAL_ACCESS
                                ? Instant.now().plusSeconds(60 * 60 * 24 * 30) //30 days
                                : null
                )
                .build();
    }
}

