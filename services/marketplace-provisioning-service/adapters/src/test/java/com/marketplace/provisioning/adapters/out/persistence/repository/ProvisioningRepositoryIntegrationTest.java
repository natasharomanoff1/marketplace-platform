package com.marketplace.provisioning.adapters.out.persistence.repository;

import com.marketplace.provisioning.adapters.config.AbstractMongoIT;
import com.marketplace.provisioning.adapters.out.persistence.document.ProvisioningDocument;
import com.marketplace.provisioning.adapters.testdata.ProvisioningDocumentTestDataFactory;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.test.StepVerifier;

@DataMongoTest
class ProvisioningRepositoryIntegrationTest extends AbstractMongoIT {

    @Autowired
    private ProvisioningRepository repository;

    @Nested
    class FindByIdTests {

        @Test
        void findById_found() {

            ProvisioningDocument doc =
                    ProvisioningDocumentTestDataFactory.aProvisioningDocument();

            persist(doc);

            StepVerifier.create(repository.findById(doc.getId()))
                    .expectNextMatches(found ->
                            found.getId().equals(doc.getId()))
                    .verifyComplete();
        }

        @Test
        void findById_notFound() {

            StepVerifier.create(repository.findById("unknown"))
                    .verifyComplete();
        }
    }

    @Nested
    class FindByOrderIdTests {

        @Test
        void findByOrderId_found() {

            ProvisioningDocument doc =
                    ProvisioningDocumentTestDataFactory.aProvisioningDocument();

            persist(doc);

            StepVerifier.create(repository.findByOrderId(doc.getOrderId()))
                    .expectNextMatches(found ->
                            found.getOrderId().equals(doc.getOrderId()))
                    .verifyComplete();
        }

        @Test
        void findByOrderId_notFound() {

            StepVerifier.create(repository.findByOrderId("unknown"))
                    .verifyComplete();
        }
    }

    @Nested
    class FindAllByBuyerIdTests {

        @Test
        void findAllByBuyerId_found() {

            ProvisioningDocument doc =
                    ProvisioningDocumentTestDataFactory.aProvisioningDocument();

            persist(doc);

            StepVerifier.create(repository.findAllByBuyerId(doc.getBuyerId()))
                    .expectNextMatches(found ->
                            found.getBuyerId().equals(doc.getBuyerId()))
                    .verifyComplete();
        }

        @Test
        void findAllByBuyerId_notFound() {

            StepVerifier.create(repository.findAllByBuyerId("unknown"))
                    .verifyComplete();
        }
    }

    @Nested
    class FindItemByAccessTokenHashTests {

        @Test
        void findItemByAccessTokenHash_found() {

            ProvisioningDocument doc =
                    ProvisioningDocumentTestDataFactory.aProvisioningDocument();

            persist(doc);

            String tokenHash =
                    doc.getItems().get(0).getAccessTokenHash();

            StepVerifier.create(repository.findItemByAccessTokenHash(tokenHash))
                    .expectNextMatches(found ->
                            found.getItems().get(0).getAccessTokenHash().equals(tokenHash))
                    .verifyComplete();
        }

        @Test
        void findItemByAccessTokenHash_notFound() {

            StepVerifier.create(repository.findItemByAccessTokenHash("unknown"))
                    .verifyComplete();
        }
    }

    private void persist(ProvisioningDocument doc) {
        repository.save(doc).block();
    }
}
