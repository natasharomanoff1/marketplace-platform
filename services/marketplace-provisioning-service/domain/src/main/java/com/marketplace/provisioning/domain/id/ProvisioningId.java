package com.marketplace.provisioning.domain.id;

import lombok.Value;

import java.util.UUID;

@Value
public class ProvisioningId {

    UUID value;

    public ProvisioningId(UUID value) {
        this.value = value;
    }
}
