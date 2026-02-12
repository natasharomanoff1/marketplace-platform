package com.marketplace.provisioning.application.usecase;

import com.marketplace.provisioning.application.port.in.GetProvisioningByIdUseCase;
import com.marketplace.provisioning.application.port.out.persistence.ProvisioningRepositoryPort;
import com.marketplace.provisioning.domain.exceptions.ProvisioningNotFoundException;
import com.marketplace.provisioning.domain.model.Provisioning;
import com.marketplace.provisioning.domain.id.ProvisioningId;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class GetProvisioningByIdUseCaseImpl implements GetProvisioningByIdUseCase {

    private final ProvisioningRepositoryPort repository;

    @Override
    public Mono<Provisioning> getById(ProvisioningId id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new ProvisioningNotFoundException(id.getValue())));
    }
}
