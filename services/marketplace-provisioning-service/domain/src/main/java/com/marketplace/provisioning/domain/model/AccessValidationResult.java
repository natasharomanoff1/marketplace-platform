package com.marketplace.provisioning.domain.model;

import com.marketplace.provisioning.domain.id.ProductId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccessValidationResult {

    private boolean valid;
    private ProductId productId;
    private Instant expiresAt;
}
