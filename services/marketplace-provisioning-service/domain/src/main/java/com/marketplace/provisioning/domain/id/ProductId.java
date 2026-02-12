package com.marketplace.provisioning.domain.id;

import lombok.Value;

import java.util.UUID;

@Value
public class ProductId {

    UUID value;

    public ProductId(UUID value) {
        this.value = value;
    }
}
