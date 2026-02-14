package com.marketplace.provisioning.adapters.out.persistence.mapper;

import com.marketplace.provisioning.adapters.mapper.IdMapper;
import com.marketplace.provisioning.adapters.out.persistence.document.ProvisioningItemDocument;
import com.marketplace.provisioning.adapters.testdata.ProvisioningDocumentTestDataFactory;
import com.marketplace.provisioning.adapters.testdata.ProvisioningTestDataFactory;
import com.marketplace.provisioning.domain.model.ProvisioningItem;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        ProvisioningItemPersistenceMapperImpl.class,
        IdMapper.class })
class ProvisioningItemPersistenceMapperTest {

    @Autowired
    private ProvisioningItemPersistenceMapper mapper;

    @Nested
    class ToProvisioningItemDocumentTests {

        @Test
        void toProvisioningItemDocument_full() {

            ProvisioningItem item =
                    ProvisioningTestDataFactory.aProvisioningItem();

            ProvisioningItemDocument result =
                    mapper.toProvisioningItemDocument(item);

            assertThat(result).isNotNull();

            assertThat(result)
                    .extracting(
                            ProvisioningItemDocument::getProductId,
                            ProvisioningItemDocument::getType,
                            ProvisioningItemDocument::getStatus,
                            ProvisioningItemDocument::getAccessTokenHash,
                            ProvisioningItemDocument::getShipmentId,
                            ProvisioningItemDocument::getExpiresAt
                    )
                    .containsExactly(
                            item.getProductId().getValue().toString(),
                            item.getType(),
                            item.getStatus(),
                            item.getAccessTokenHash(),
                            item.getShipmentId().getValue().toString(),
                            item.getExpiresAt()
                    );
        }

        @Test
        void toProvisioningItemDocument_nullInput() {

            ProvisioningItemDocument result =
                    mapper.toProvisioningItemDocument(null);

            assertThat(result).isNull();
        }
    }

    @Nested
    class ToProvisioningItemTests {

        @Test
        void toProvisioningItem_full() {

            ProvisioningItemDocument document =
                    ProvisioningDocumentTestDataFactory.aProvisioningItemDocument();

            ProvisioningItem result =
                    mapper.toProvisioningItem(document);

            assertThat(result).isNotNull();

            assertThat(result)
                    .extracting(
                            r -> r.getProductId().getValue().toString(),
                            ProvisioningItem::getType,
                            ProvisioningItem::getStatus,
                            ProvisioningItem::getAccessTokenHash,
                            r -> r.getShipmentId().getValue().toString(),
                            ProvisioningItem::getExpiresAt
                    )
                    .containsExactly(
                            document.getProductId(),
                            document.getType(),
                            document.getStatus(),
                            document.getAccessTokenHash(),
                            document.getShipmentId(),
                            document.getExpiresAt()
                    );
        }

        @Test
        void toProvisioningItem_nullInput() {

            ProvisioningItem result =
                    mapper.toProvisioningItem(null);

            assertThat(result).isNull();
        }
    }
}

