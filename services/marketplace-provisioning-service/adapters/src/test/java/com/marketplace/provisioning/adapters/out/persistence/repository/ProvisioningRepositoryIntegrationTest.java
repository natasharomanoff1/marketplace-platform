package com.marketplace.provisioning.adapters.out.persistence.repository;

import com.marketplace.provisioning.adapters.config.AbstractMongoIT;
import com.marketplace.provisioning.adapters.out.persistence.document.ProvisioningDocument;
import com.marketplace.provisioning.adapters.testdata.ProvisioningDocumentTestDataFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.test.StepVerifier;

@DataMongoTest
class ProvisioningRepositoryIntegrationTest extends AbstractMongoIT {

    @Autowired
    private ProvisioningRepository repository;

    @Test
    void findById() {

        ProvisioningDocument doc =
                ProvisioningDocumentTestDataFactory.aProvisioningDocument();

        repository.save(doc).block();

        StepVerifier.create(repository.findById(doc.getId()))
                .expectNextMatches(found ->
                        found.getId().equals(doc.getId()))
                .verifyComplete();
    }

    @Test
    void findByOrderId() {

        ProvisioningDocument doc =
                ProvisioningDocumentTestDataFactory.aProvisioningDocument();

        repository.save(doc).block();

        StepVerifier.create(repository.findByOrderId(doc.getOrderId()))
                .expectNextMatches(found ->
                        found.getOrderId().equals(doc.getOrderId()))
                .verifyComplete();
    }

    @Test
    void findAllByBuyerId() {

        ProvisioningDocument doc =
                ProvisioningDocumentTestDataFactory.aProvisioningDocument();

        repository.save(doc).block();

        StepVerifier.create(repository.findAllByBuyerId(doc.getBuyerId()))
                .expectNextMatches(found ->
                        found.getBuyerId().equals(doc.getBuyerId()))
                .verifyComplete();
    }
}
