package com.marketplace.provisioning.application.usecase;

import com.marketplace.provisioning.application.port.out.persistence.ProvisioningRepositoryPort;
import com.marketplace.provisioning.application.testdata.ProvisioningTestDataFactory;
import com.marketplace.provisioning.domain.exceptions.ProvisioningNotFoundException;
import com.marketplace.provisioning.domain.id.ProvisioningId;
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
class GetProvisioningByIdUseCaseImplTest {

    @Mock
    private ProvisioningRepositoryPort repository;

    @InjectMocks
    private GetProvisioningByIdUseCaseImpl useCase;

    @Test
    void getById_shouldReturnProvisioning_whenFound() {

        Provisioning provisioning =
                ProvisioningTestDataFactory.aProvisioning();

        ProvisioningId id = provisioning.getId();

        when(repository.findById(id))
                .thenReturn(Mono.just(provisioning));

        Provisioning result =
                useCase.getById(id).block();

        assertThat(result).isEqualTo(provisioning);
        verify(repository).findById(id);
    }

    @Test
    void getById_shouldThrowException_whenNotFound() {

        ProvisioningId id =
                new ProvisioningId(UUID.randomUUID());

        when(repository.findById(id))
                .thenReturn(Mono.empty());

        assertThatThrownBy(() -> useCase.getById(id).block())
                .isInstanceOf(ProvisioningNotFoundException.class);

        verify(repository).findById(id);
    }
}
