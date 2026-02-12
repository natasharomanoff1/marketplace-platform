package com.marketplace.provisioning.adapters.mapper;

import com.marketplace.provisioning.domain.id.*;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class IdMapper {

    /* ===== ProductId ===== */

    public String map(ProductId id) {
        return id == null ? null : id.getValue().toString();
    }

    public ProductId mapProductId(String id) {
        return id == null ? null : new ProductId(UUID.fromString(id));
    }

    /* ===== ProvisioningId ===== */

    public String map(ProvisioningId id) {
        return id == null ? null : id.getValue().toString();
    }

    public ProvisioningId mapProvisioningId(String id) {
        return id == null ? null : new ProvisioningId(UUID.fromString(id));
    }

    /* ===== OrderId ===== */

    public String map(OrderId id) {
        return id == null ? null : id.getValue().toString();
    }

    public OrderId mapOrderId(String id) {
        return id == null ? null : new OrderId(UUID.fromString(id));
    }

    /* ===== BuyerId ===== */

    public String map(BuyerId id) {
        return id == null ? null : id.getValue().toString();
    }

    public BuyerId mapBuyerId(String id) {
        return id == null ? null : new BuyerId(UUID.fromString(id));
    }

    /* ===== ShipmentId ===== */

    public String map(ShipmentId id) {
        return id == null ? null : id.getValue().toString();
    }

    public ShipmentId mapShipmentId(String id) {
        return id == null ? null : new ShipmentId(UUID.fromString(id));
    }
}

