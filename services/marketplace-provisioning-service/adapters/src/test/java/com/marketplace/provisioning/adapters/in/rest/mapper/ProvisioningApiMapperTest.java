package com.marketplace.provisioning.adapters.in.rest.mapper;

import com.marketplace.provisioning.adapters.in.api.model.ProvisioningItem;
import com.marketplace.provisioning.adapters.mapper.IdMapper;
import com.marketplace.provisioning.adapters.testdata.ProvisioningTestDataFactory;
import com.marketplace.provisioning.domain.model.Provisioning;
import com.marketplace.provisioning.adapters.in.api.model.ProvisioningResponse;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        ProvisioningApiMapperImpl.class,
        IdMapper.class
})
class ProvisioningApiMapperTest {

    @Autowired
    private ProvisioningApiMapper mapper;

    @Nested
    class ToProvisioningResponseTests {

        @Test
        void toProvisioningResponse_full() {

            Provisioning provisioning =
                    ProvisioningTestDataFactory.aProvisioning();

            ProvisioningResponse result =
                    mapper.toProvisioningResponse(provisioning);

            assertThat(result).isNotNull();

            assertThat(result)
                    .extracting(
                            ProvisioningResponse::getId,
                            ProvisioningResponse::getOrderId,
                            ProvisioningResponse::getBuyerId
                    )
                    .containsExactly(
                            provisioning.getId().getValue().toString(),
                            provisioning.getOrderId().getValue().toString(),
                            provisioning.getBuyerId().getValue().toString()
                    );

            assertThat(result.getCreatedAt())
                    .isEqualTo(provisioning.getCreatedAt().atOffset(java.time.ZoneOffset.UTC));

            assertThat(result.getItems())
                    .hasSize(provisioning.getItems().size());

            assertThat(result.getItems().get(0))
                    .extracting(
                            ProvisioningItem::getProductId,
                            ProvisioningItem::getType,
                            ProvisioningItem::getStatus,
                            ProvisioningItem::getAccessToken
                    )
                    .containsExactly(
                            provisioning.getItems().get(0).getProductId().getValue().toString(),
                            provisioning.getItems().get(0).getType().name(),
                            provisioning.getItems().get(0).getStatus().name(),
                            provisioning.getItems().get(0).getAccessTokenHash()
                    );
        }

        @Test
        void toProvisioningResponse_nullInput() {

            ProvisioningResponse result =
                    mapper.toProvisioningResponse(null);

            assertThat(result).isNull();
        }
    }

    @Nested
    class InstantMappingTests {

        @Test
        void map_instantToOffsetDateTime() {

            Instant instant = Instant.now();

            OffsetDateTime result = mapper.map(instant);

            assertThat(result)
                    .isEqualTo(instant.atOffset(ZoneOffset.UTC));
        }

        @Test
        void map_nullInstant() {

            OffsetDateTime result = mapper.map(null);

            assertThat(result).isNull();
        }
    }
}
