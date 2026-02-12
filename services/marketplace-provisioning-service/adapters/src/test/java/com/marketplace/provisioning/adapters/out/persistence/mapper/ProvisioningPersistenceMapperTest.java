package com.marketplace.provisioning.adapters.out.persistence.mapper;

import com.marketplace.provisioning.adapters.mapper.IdMapper;
import com.marketplace.provisioning.adapters.out.persistence.document.ProvisioningDocument;
import com.marketplace.provisioning.adapters.testdata.ProvisioningDocumentTestDataFactory;
import com.marketplace.provisioning.adapters.testdata.ProvisioningTestDataFactory;
import com.marketplace.provisioning.domain.id.BuyerId;
import com.marketplace.provisioning.domain.id.OrderId;
import com.marketplace.provisioning.domain.model.Provisioning;
import com.marketplace.provisioning.domain.id.ProvisioningId;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        ProvisioningPersistenceMapperImpl.class,
        ProvisioningItemPersistenceMapperImpl.class,
        IdMapper.class
})
class ProvisioningPersistenceMapperTest {

    @Autowired
    private ProvisioningPersistenceMapper mapper;

    @Nested
    class ToProvisioningDocumentTests {

        @Test
        void toProvisioningDocument_full() {

            Provisioning provisioning =
                    ProvisioningTestDataFactory.aProvisioning();

            ProvisioningDocument result =
                    mapper.toProvisioningDocument(provisioning);

            assertThat(result).isNotNull();

            assertThat(result)
                    .extracting(
                            ProvisioningDocument::getId,
                            ProvisioningDocument::getOrderId,
                            ProvisioningDocument::getBuyerId,
                            ProvisioningDocument::getCreatedAt
                    )
                    .containsExactly(
                            provisioning.getId().getValue().toString(),
                            provisioning.getOrderId().getValue().toString(),
                            provisioning.getBuyerId().getValue().toString(),
                            provisioning.getCreatedAt()
                    );

            assertThat(result.getItems())
                    .isNotNull()
                    .hasSize(provisioning.getItems().size());
        }

        @Test
        void toProvisioningDocument_nullItems() {

            Provisioning provisioning = ProvisioningTestDataFactory.aProvisioning()
                            .toBuilder()
                            .items(null)
                            .build();

            ProvisioningDocument result = mapper.toProvisioningDocument(provisioning);

            assertThat(result.getItems()).isNull();
        }

        @Test
        void toProvisioningDocument_nullInput() {

            ProvisioningDocument result = mapper.toProvisioningDocument(null);

            assertThat(result).isNull();
        }
    }

    @Nested
    class ToProvisioningTests {

        @Test
        void toProvisioning_full() {

            ProvisioningDocument document =
                    ProvisioningDocumentTestDataFactory.aProvisioningDocument();

            Provisioning result =
                    mapper.toProvisioning(document);

            assertThat(result).isNotNull();

            assertThat(result)
                    .extracting(
                            Provisioning::getId,
                            Provisioning::getOrderId,
                            Provisioning::getBuyerId,
                            Provisioning::getCreatedAt
                    )
                    .containsExactly(
                            new ProvisioningId(UUID.fromString(document.getId())),
                            new OrderId(UUID.fromString(document.getOrderId())),
                            new BuyerId(UUID.fromString(document.getBuyerId())),
                            document.getCreatedAt()
                    );

            assertThat(result.getItems())
                    .isNotNull()
                    .hasSize(document.getItems().size());
        }

        @Test
        void toProvisioning_nullItems() {

            ProvisioningDocument document =
                    ProvisioningDocumentTestDataFactory.aProvisioningDocument();
            document.setItems(null);

            Provisioning result = mapper.toProvisioning(document);

            assertThat(result.getItems()).isNull();
        }


        @Test
        void toProvisioning_nullInput() {

            Provisioning result =
                    mapper.toProvisioning(null);

            assertThat(result).isNull();
        }
    }
}

