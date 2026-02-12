package com.marketplace.provisioning.application.port.in;

import com.marketplace.provisioning.domain.model.Provisioning;
import com.marketplace.provisioning.domain.id.ProvisioningId;
import reactor.core.publisher.Mono;

public interface GetProvisioningByIdUseCase {

    Mono<Provisioning> getById(ProvisioningId id);
}
