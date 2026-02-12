package com.marketplace.provisioning.domain.id;

import lombok.Value;

import java.util.UUID;

@Value
public class ShipmentId {

    UUID value;

    public ShipmentId(UUID value) {
        this.value = value;
    }
}
