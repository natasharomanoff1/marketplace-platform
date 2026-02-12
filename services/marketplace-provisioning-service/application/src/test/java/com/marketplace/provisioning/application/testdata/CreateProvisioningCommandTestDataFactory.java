package com.marketplace.provisioning.application.testdata;

import com.marketplace.provisioning.application.command.CreateProvisioningCommand;
import com.marketplace.provisioning.domain.enums.ProvisioningType;
import com.marketplace.provisioning.domain.id.BuyerId;
import com.marketplace.provisioning.domain.id.OrderId;
import com.marketplace.provisioning.domain.id.ProductId;

import java.util.List;
import java.util.UUID;

public final class CreateProvisioningCommandTestDataFactory {

    private CreateProvisioningCommandTestDataFactory() {}

    public static CreateProvisioningCommand aCreateProvisioningCommand() {

        CreateProvisioningCommand command = new CreateProvisioningCommand();
        command.setOrderId(new OrderId(UUID.randomUUID()));
        command.setBuyerId(new BuyerId(UUID.randomUUID()));
        command.setItems(List.of(
                aItem(ProvisioningType.DIGITAL_ACCESS.name()),
                aItem(ProvisioningType.PHYSICAL_SHIPMENT.name())
        ));

        return command;
    }

    public static CreateProvisioningCommand.Item aItem(String type) {

        CreateProvisioningCommand.Item item =
                new CreateProvisioningCommand.Item();

        item.setProductId(new ProductId(UUID.randomUUID()));
        item.setProvisioningType(type);

        return item;
    }
}

