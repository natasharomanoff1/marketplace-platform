package com.marketplace.provisioning.domain.id;

import lombok.Value;

import java.util.UUID;

@Value
public class BuyerId {

    UUID value;

    public BuyerId(UUID value) {
        this.value = value;
    }
}
