package com.marketplace.provisioning.domain.id;

import lombok.Value;

import java.util.UUID;

@Value
public class OrderId {

    UUID value;

    public OrderId(UUID value) {
        this.value = value;
    }
}
