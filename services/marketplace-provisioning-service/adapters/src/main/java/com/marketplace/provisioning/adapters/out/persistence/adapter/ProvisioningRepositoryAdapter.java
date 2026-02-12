package com.marketplace.provisioning.adapters.out.persistence.adapter;

import com.marketplace.provisioning.adapters.out.persistence.mapper.ProvisioningPersistenceMapper;
import com.marketplace.provisioning.adapters.out.persistence.repository.ProvisioningRepository;
import com.marketplace.provisioning.application.port.out.persistence.ProvisioningRepositoryPort;
import com.marketplace.provisioning.domain.id.BuyerId;
import com.marketplace.provisioning.domain.id.OrderId;
import com.marketplace.provisioning.domain.model.Provisioning;
import com.marketplace.provisioning.domain.id.ProvisioningId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ProvisioningRepositoryAdapter implements ProvisioningRepositoryPort {

    private final ProvisioningRepository repository;
    private final ProvisioningPersistenceMapper mapper;

    @Override
    public Mono<Void> save(Provisioning provisioning) {

        return repository.save(
                mapper.toProvisioningDocument(provisioning)
        ).then();
    }

    @Override
    public Mono<Provisioning> findByOrderId(OrderId orderId) {

        return repository.findByOrderId(orderId.getValue().toString())
                .map(mapper::toProvisioning);
    }

    @Override
    public Flux<Provisioning> findByBuyerId(BuyerId buyerId) {

        return repository.findAllByBuyerId(buyerId.getValue().toString())
                .map(mapper::toProvisioning);
    }

    @Override
    public Mono<Provisioning> findById(ProvisioningId id) {

        return repository.findById(id.getValue().toString())
                .map(mapper::toProvisioning);
    }
}
