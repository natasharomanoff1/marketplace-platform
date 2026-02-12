package com.marketplace.provisioning.adapters.out.persistence.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("provisionings")
public class ProvisioningDocument {

    @Id
    private String id;
    private String orderId;
    private String buyerId;
    private List<ProvisioningItemDocument> items;
    private Instant createdAt;
}

