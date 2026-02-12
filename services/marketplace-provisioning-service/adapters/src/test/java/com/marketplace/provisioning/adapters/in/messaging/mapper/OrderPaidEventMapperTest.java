package com.marketplace.provisioning.adapters.in.messaging.mapper;

import com.marketplace.provisioning.adapters.in.messaging.event.OrderPaidEvent;
import com.marketplace.provisioning.adapters.mapper.IdMapper;
import com.marketplace.provisioning.adapters.testdata.OrderPaidEventTestDataFactory;
import com.marketplace.provisioning.application.command.CreateProvisioningCommand;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        OrderPaidEventMapperImpl.class,
        IdMapper.class
})
class OrderPaidEventMapperTest {

    @Autowired
    private OrderPaidEventMapper mapper;

    @Nested
    class ToCreateProvisioningCommandTests {

        @Test
        void toCreateProvisioningCommand_full() {

            OrderPaidEvent event =
                    OrderPaidEventTestDataFactory.anOrderPaidEvent();

            CreateProvisioningCommand result =
                    mapper.toCreateProvisioningCommand(event);

            assertThat(result).isNotNull();

            assertThat(result)
                    .extracting(
                            r -> r.getOrderId().getValue().toString(),
                            r -> r.getBuyerId().getValue().toString()
                    )
                    .containsExactly(
                            event.getOrderId(),
                            event.getBuyerId()
                    );

            assertThat(result.getItems()).hasSize(event.getItems().size());

            assertThat(result.getItems().get(0))
                    .extracting(
                            i -> i.getProductId().getValue().toString(),
                            CreateProvisioningCommand.Item::getProvisioningType
                    )
                    .containsExactly(
                            event.getItems().get(0).getProductId(),
                            event.getItems().get(0).getProvisioningType()
                    );
        }

        @Test
        void toCreateProvisioningCommand_nullInput() {

            CreateProvisioningCommand result =
                    mapper.toCreateProvisioningCommand(null);

            assertThat(result).isNull();
        }
    }
}

