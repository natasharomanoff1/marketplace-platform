package com.marketplace.provisioning.application.usecase;

import com.marketplace.provisioning.application.port.in.GetProvisioningByOrderIdUseCase;
import com.marketplace.provisioning.application.port.out.persistence.ProvisioningRepositoryPort;
import com.marketplace.provisioning.domain.exceptions.ProvisioningNotFoundException;
import com.marketplace.provisioning.domain.id.OrderId;
import com.marketplace.provisioning.domain.model.Provisioning;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class GetProvisioningByOrderIdUseCaseImpl implements GetProvisioningByOrderIdUseCase {

    private final ProvisioningRepositoryPort repository;

    @Override
    public Mono<Provisioning> getByOrderId(OrderId orderId) {
        return repository.findByOrderId(orderId)
                .switchIfEmpty(Mono.error(new ProvisioningNotFoundException(orderId.getValue())));
    }
}
