package com.marketplace.provisioning.application.usecase;

import com.marketplace.provisioning.application.command.CreateProvisioningCommand;
import com.marketplace.provisioning.application.port.out.persistence.ProvisioningRepositoryPort;
import com.marketplace.provisioning.application.service.ProvisioningService;
import com.marketplace.provisioning.application.testdata.CreateProvisioningCommandTestDataFactory;
import com.marketplace.provisioning.domain.model.Provisioning;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateProvisioningUseCaseImplTest {

    @Mock
    private ProvisioningRepositoryPort repository;

    @Mock
    private ProvisioningService provisioningService;

    @InjectMocks
    private CreateProvisioningUseCaseImpl useCase;

    @Test
    void execute_shouldCreateProvisioningAndSave() {

        CreateProvisioningCommand command =
                CreateProvisioningCommandTestDataFactory.aCreateProvisioningCommand();

        Provisioning provisioning =
                Provisioning.builder().build();

        when(provisioningService.createProvisioning(command))
                .thenReturn(provisioning);

        useCase.execute(command);

        verify(provisioningService).createProvisioning(command);
        verify(repository).save(provisioning);
    }
}
