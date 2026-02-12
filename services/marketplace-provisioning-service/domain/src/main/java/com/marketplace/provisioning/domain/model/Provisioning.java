package com.marketplace.provisioning.domain.model;

import com.marketplace.provisioning.domain.id.BuyerId;
import com.marketplace.provisioning.domain.id.OrderId;
import com.marketplace.provisioning.domain.id.ProvisioningId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Provisioning {

    private ProvisioningId id;
    private OrderId orderId;
    private BuyerId buyerId;
    private List<ProvisioningItem> items;
    private Instant createdAt;
}

