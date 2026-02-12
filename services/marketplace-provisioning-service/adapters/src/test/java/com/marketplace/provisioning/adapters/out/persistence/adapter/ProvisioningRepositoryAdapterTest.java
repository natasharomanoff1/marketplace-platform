package com.marketplace.provisioning.adapters.out.persistence.adapter;

import com.marketplace.provisioning.adapters.out.persistence.document.ProvisioningDocument;
import com.marketplace.provisioning.adapters.out.persistence.mapper.ProvisioningPersistenceMapper;
import com.marketplace.provisioning.adapters.out.persistence.repository.ProvisioningRepository;
import com.marketplace.provisioning.adapters.testdata.ProvisioningDocumentTestDataFactory;
import com.marketplace.provisioning.adapters.testdata.ProvisioningTestDataFactory;
import com.marketplace.provisioning.domain.id.BuyerId;
import com.marketplace.provisioning.domain.id.OrderId;
import com.marketplace.provisioning.domain.id.ProvisioningId;
import com.marketplace.provisioning.domain.model.Provisioning;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProvisioningRepositoryAdapterTest {

    @Mock
    private ProvisioningRepository repository;

    @Mock
    private ProvisioningPersistenceMapper mapper;

    @InjectMocks
    private ProvisioningRepositoryAdapter adapter;

    @Nested
    class SaveTests {

        @Test
        void save() {

            Provisioning provisioning =
                    ProvisioningTestDataFactory.aProvisioning();

            ProvisioningDocument document =
                    ProvisioningDocumentTestDataFactory.aProvisioningDocument();

            when(mapper.toProvisioningDocument(provisioning))
                    .thenReturn(document);

            when(repository.save(document))
                    .thenReturn(Mono.empty());

            adapter.save(provisioning).block();
        }
    }

    @Nested
    class FindByOrderIdTests {

        @Test
        void findByOrderId() {

            UUID uuid = UUID.randomUUID();
            OrderId orderId = new OrderId(uuid);

            ProvisioningDocument document =
                    ProvisioningDocumentTestDataFactory.aProvisioningDocument();
            Provisioning provisioning =
                    ProvisioningTestDataFactory.aProvisioning();

            when(repository.findByOrderId(uuid.toString()))
                    .thenReturn(Mono.just(document));

            when(mapper.toProvisioning(document))
                    .thenReturn(provisioning);

            Provisioning result =
                    adapter.findByOrderId(orderId).block();

            assertThat(result).isEqualTo(provisioning);
        }
    }

    @Nested
    class FindByBuyerIdTests {

        @Test
        void findByBuyerId() {

            UUID uuid = UUID.randomUUID();
            BuyerId buyerId = new BuyerId(uuid);

            ProvisioningDocument document =
                    ProvisioningDocumentTestDataFactory.aProvisioningDocument();
            Provisioning provisioning =
                    ProvisioningTestDataFactory.aProvisioning();

            when(repository.findAllByBuyerId(uuid.toString()))
                    .thenReturn(Flux.just(document));

            when(mapper.toProvisioning(document))
                    .thenReturn(provisioning);

            Provisioning result =
                    adapter.findByBuyerId(buyerId).blockFirst();

            assertThat(result).isEqualTo(provisioning);
        }
    }

    @Nested
    class FindByIdTests {

        @Test
        void findById() {

            UUID uuid = UUID.randomUUID();
            ProvisioningId id = new ProvisioningId(uuid);

            ProvisioningDocument document =
                    ProvisioningDocumentTestDataFactory.aProvisioningDocument();
            Provisioning provisioning =
                    ProvisioningTestDataFactory.aProvisioning();

            when(repository.findById(uuid.toString()))
                    .thenReturn(Mono.just(document));

            when(mapper.toProvisioning(document))
                    .thenReturn(provisioning);

            Provisioning result =
                    adapter.findById(id).block();

            assertThat(result).isEqualTo(provisioning);
        }
    }
}
