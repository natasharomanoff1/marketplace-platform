package com.marketplace.provisioning.application.port.in;

import com.marketplace.provisioning.domain.id.OrderId;
import com.marketplace.provisioning.domain.model.Provisioning;
import reactor.core.publisher.Mono;

public interface GetProvisioningByOrderIdUseCase {

    Mono<Provisioning> getByOrderId(OrderId orderId);
}
