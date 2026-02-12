package com.marketplace.provisioning.application.port.in;

import com.marketplace.provisioning.domain.id.BuyerId;
import com.marketplace.provisioning.domain.model.Provisioning;
import reactor.core.publisher.Flux;

public interface GetProvisioningsByBuyerIdUseCase {

    Flux<Provisioning> getByBuyerId(BuyerId buyerId);
}
