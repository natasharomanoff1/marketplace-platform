package com.marketplace.provisioning.adapters.out.persistence.repository;

import com.marketplace.provisioning.adapters.out.persistence.document.ProvisioningDocument;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProvisioningRepository
        extends ReactiveMongoRepository<ProvisioningDocument, String> {

    Mono<ProvisioningDocument> findByOrderId(String orderId);

    Flux<ProvisioningDocument> findAllByBuyerId(String buyerId);

    @Query(
            value = "{ 'items.accessTokenHash': ?0 }",
            fields = "{ 'items.$': 1 }"
    )
    Mono<ProvisioningDocument> findItemByAccessTokenHash(String tokenHash);
}

