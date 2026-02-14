package com.marketplace.provisioning.application.service;

import com.marketplace.provisioning.application.command.CreateProvisioningCommand;
import com.marketplace.provisioning.application.testdata.CreateProvisioningCommandTestDataFactory;
import com.marketplace.provisioning.domain.model.Provisioning;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProvisioningServiceTest {

    @Mock
    private AccessTokenService accessTokenService;

    @InjectMocks
    private ProvisioningService service;

    @Test
    void createProvisioning_shouldCreateProvisioning() {

        when(accessTokenService.generatePlainToken())
                .thenReturn("plain-token");

        when(accessTokenService.hash("plain-token"))
                .thenReturn("hash-token");

        CreateProvisioningCommand command =
                CreateProvisioningCommandTestDataFactory
                        .aCreateProvisioningCommand();

        Provisioning saved = service.createProvisioning(command);

        assertThat(saved.getOrderId()).isEqualTo(command.getOrderId());
        assertThat(saved.getBuyerId()).isEqualTo(command.getBuyerId());
        assertThat(saved.getItems()).hasSize(command.getItems().size());

        assertThat(saved.getItems().get(0).getAccessTokenHash()).isEqualTo("hash-token");
        assertThat(saved.getItems().get(0).getExpiresAt()).isNotNull();

        assertThat(saved.getItems().get(1).getAccessTokenHash()).isNull();
        assertThat(saved.getItems().get(1).getExpiresAt()).isNull();
    }
}
