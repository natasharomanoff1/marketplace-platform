package com.marketplace.provisioning.application.usecase;

import com.marketplace.provisioning.application.port.out.persistence.ProvisioningRepositoryPort;
import com.marketplace.provisioning.application.testdata.ProvisioningTestDataFactory;
import com.marketplace.provisioning.domain.exceptions.ProvisioningNotFoundException;
import com.marketplace.provisioning.domain.id.OrderId;
import com.marketplace.provisioning.domain.model.Provisioning;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetProvisioningByOrderIdUseCaseImplTest {

    @Mock
    private ProvisioningRepositoryPort repository;

    @InjectMocks
    private GetProvisioningByOrderIdUseCaseImpl useCase;

    @Test
    void getByOrderId_shouldReturnProvisioning_whenFound() {

        Provisioning provisioning =
                ProvisioningTestDataFactory.aProvisioning();

        OrderId orderId = provisioning.getOrderId();

        when(repository.findByOrderId(orderId))
                .thenReturn(Mono.just(provisioning));

        Provisioning result =
                useCase.getByOrderId(orderId).block();

        assertThat(result).isEqualTo(provisioning);
        verify(repository).findByOrderId(orderId);
    }

    @Test
    void getByOrderId_shouldThrowException_whenNotFound() {

        OrderId orderId =
                new OrderId(UUID.randomUUID());

        when(repository.findByOrderId(orderId))
                .thenReturn(Mono.empty());

        assertThatThrownBy(() -> useCase.getByOrderId(orderId).block())
                .isInstanceOf(ProvisioningNotFoundException.class);

        verify(repository).findByOrderId(orderId);
    }
}
