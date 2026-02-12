package com.marketplace.provisioning.application.port.out.persistence;

import com.marketplace.provisioning.domain.id.BuyerId;
import com.marketplace.provisioning.domain.id.OrderId;
import com.marketplace.provisioning.domain.model.Provisioning;
import com.marketplace.provisioning.domain.id.ProvisioningId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProvisioningRepositoryPort {

    Mono<Void> save(Provisioning provisioning);

    Mono<Provisioning> findByOrderId(OrderId orderId);

    Flux<Provisioning> findByBuyerId(BuyerId buyerId);

    Mono<Provisioning> findById(ProvisioningId id);
}
