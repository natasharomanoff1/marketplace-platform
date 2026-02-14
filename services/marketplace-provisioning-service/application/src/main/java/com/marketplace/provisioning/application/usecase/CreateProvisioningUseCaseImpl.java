package com.marketplace.provisioning.application.usecase;

import com.marketplace.provisioning.application.command.CreateProvisioningCommand;
import com.marketplace.provisioning.application.port.in.CreateProvisioningUseCase;
import com.marketplace.provisioning.application.port.out.persistence.ProvisioningRepositoryPort;
import com.marketplace.provisioning.application.service.ProvisioningService;
import com.marketplace.provisioning.domain.model.Provisioning;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateProvisioningUseCaseImpl implements CreateProvisioningUseCase {

    private final ProvisioningRepositoryPort repository;
    private final ProvisioningService provisioningService;

    @Override
    public void execute(CreateProvisioningCommand command) {

        Provisioning provisioning =
                provisioningService.createProvisioning(command);

        repository.save(provisioning);
    }
}
