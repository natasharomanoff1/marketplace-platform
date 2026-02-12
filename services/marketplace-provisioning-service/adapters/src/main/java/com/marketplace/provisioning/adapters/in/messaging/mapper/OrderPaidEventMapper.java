package com.marketplace.provisioning.adapters.in.messaging.mapper;

import com.marketplace.provisioning.adapters.in.messaging.event.OrderPaidEvent;
import com.marketplace.provisioning.adapters.mapper.IdMapper;
import com.marketplace.provisioning.application.command.CreateProvisioningCommand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = IdMapper.class)
public interface OrderPaidEventMapper {

    CreateProvisioningCommand toCreateProvisioningCommand(OrderPaidEvent event);
}
