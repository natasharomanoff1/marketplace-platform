package com.marketplace.provisioning.adapters.in.messaging.listener;

import com.marketplace.provisioning.adapters.in.messaging.event.OrderPaidEvent;
import com.marketplace.provisioning.adapters.in.messaging.mapper.OrderPaidEventMapper;
import com.marketplace.provisioning.adapters.testdata.OrderPaidEventTestDataFactory;
import com.marketplace.provisioning.application.command.CreateProvisioningCommand;
import com.marketplace.provisioning.application.port.in.CreateProvisioningUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderPaidKafkaListenerTest {

    @Mock
    private CreateProvisioningUseCase useCase;

    @Mock
    private OrderPaidEventMapper mapper;

    @InjectMocks
    private OrderPaidKafkaListener listener;

    @Test
    void shouldInvokeUseCase() {

        OrderPaidEvent event = OrderPaidEventTestDataFactory.anOrderPaidEvent();

        when(mapper.toCreateProvisioningCommand(event))
                .thenReturn(new CreateProvisioningCommand());

        listener.handle(event);

        verify(mapper).toCreateProvisioningCommand(event);
        verify(useCase).execute(any(CreateProvisioningCommand.class));
    }
}
