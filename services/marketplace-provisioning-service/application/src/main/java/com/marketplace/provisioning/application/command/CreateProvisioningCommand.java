package com.marketplace.provisioning.application.command;

import com.marketplace.provisioning.domain.id.BuyerId;
import com.marketplace.provisioning.domain.id.OrderId;
import com.marketplace.provisioning.domain.id.ProductId;
import lombok.Data;
import java.util.List;

@Data
public class CreateProvisioningCommand {

    private OrderId orderId;
    private BuyerId buyerId;
    private List<Item> items;

    @Data
    public static class Item {
        private ProductId productId;
        private String provisioningType;
    }
}