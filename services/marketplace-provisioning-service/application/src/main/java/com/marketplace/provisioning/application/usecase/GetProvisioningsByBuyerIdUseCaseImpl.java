package com.marketplace.provisioning.application.usecase;

import com.marketplace.provisioning.application.port.in.GetProvisioningsByBuyerIdUseCase;
import com.marketplace.provisioning.application.port.out.persistence.ProvisioningRepositoryPort;
import com.marketplace.provisioning.domain.id.BuyerId;
import com.marketplace.provisioning.domain.model.Provisioning;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetProvisioningsByBuyerIdUseCaseImpl implements GetProvisioningsByBuyerIdUseCase {

    private final ProvisioningRepositoryPort repository;

    @Override
    public Flux<Provisioning> getByBuyerId(BuyerId buyerId) {
        return repository.findByBuyerId(buyerId);
    }
}
