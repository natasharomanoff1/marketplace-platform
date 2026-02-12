package com.marketplace.provisioning.application.usecase;

import com.marketplace.provisioning.application.port.out.persistence.ProvisioningRepositoryPort;
import com.marketplace.provisioning.application.testdata.ProvisioningTestDataFactory;
import com.marketplace.provisioning.domain.id.BuyerId;
import com.marketplace.provisioning.domain.model.Provisioning;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetProvisioningsByBuyerIdUseCaseImplTest {

    @Mock
    private ProvisioningRepositoryPort repository;

    @InjectMocks
    private GetProvisioningsByBuyerIdUseCaseImpl useCase;

    @Test
    void getByBuyerId_shouldReturnProvisionings() {

        BuyerId buyerId = new BuyerId(UUID.randomUUID());

        Provisioning p1 = ProvisioningTestDataFactory.aProvisioning();
        Provisioning p2 = ProvisioningTestDataFactory.aProvisioning();

        when(repository.findByBuyerId(buyerId))
                .thenReturn(Flux.just(p1, p2));

        List<Provisioning> result =
                useCase.getByBuyerId(buyerId)
                        .collectList()
                        .block();

        assertThat(result).containsExactly(p1, p2);

        verify(repository).findByBuyerId(buyerId);
    }
}
