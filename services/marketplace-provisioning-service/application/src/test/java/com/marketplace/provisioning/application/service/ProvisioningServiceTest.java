package com.marketplace.provisioning.application.service;

import com.marketplace.provisioning.application.command.CreateProvisioningCommand;
import com.marketplace.provisioning.application.port.out.persistence.ProvisioningRepositoryPort;
import com.marketplace.provisioning.application.testdata.CreateProvisioningCommandTestDataFactory;
import com.marketplace.provisioning.domain.model.Provisioning;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProvisioningServiceTest {

    @Mock
    private ProvisioningRepositoryPort repository;

    @InjectMocks
    private ProvisioningService service;

    @Test
    void createProvisioningFromEvent_shouldSaveProvisioning() {

        CreateProvisioningCommand command =
                CreateProvisioningCommandTestDataFactory
                        .aCreateProvisioningCommand();

        service.createProvisioningFromEvent(command);

        ArgumentCaptor<Provisioning> captor =
                ArgumentCaptor.forClass(Provisioning.class);

        verify(repository).save(captor.capture());

        Provisioning saved = captor.getValue();

        assertThat(saved.getOrderId()).isEqualTo(command.getOrderId());
        assertThat(saved.getBuyerId()).isEqualTo(command.getBuyerId());
        assertThat(saved.getItems()).hasSize(command.getItems().size());

        assertThat(saved.getItems().get(0).getAccessToken()).isNotNull();
        assertThat(saved.getItems().get(0).getExpiresAt()).isNotNull();

        assertThat(saved.getItems().get(1).getAccessToken()).isNull();
        assertThat(saved.getItems().get(1).getExpiresAt()).isNull();
    }
}
