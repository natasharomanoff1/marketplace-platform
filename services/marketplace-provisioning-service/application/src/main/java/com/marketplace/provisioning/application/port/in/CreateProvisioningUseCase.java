package com.marketplace.provisioning.application.port.in;

import com.marketplace.provisioning.application.command.CreateProvisioningCommand;

public interface CreateProvisioningUseCase {

    void execute(CreateProvisioningCommand command);
}
