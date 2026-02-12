package com.marketplace.provisioning.adapters.mapper;

import com.marketplace.provisioning.domain.id.*;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class IdMapperTest {

    private final IdMapper mapper = new IdMapper();

    @Nested
    class ProductIdMappingTests {

        @Test
        void map_toString() {
            UUID uuid = UUID.randomUUID();
            ProductId id = new ProductId(uuid);

            String result = mapper.map(id);

            assertThat(result).isEqualTo(uuid.toString());
        }

        @Test
        void map_null() {
            assertThat(mapper.map((ProductId) null)).isNull();
        }

        @Test
        void mapProductId_fromString() {
            UUID uuid = UUID.randomUUID();

            ProductId result = mapper.mapProductId(uuid.toString());

            assertThat(result.getValue()).isEqualTo(uuid);
        }

        @Test
        void mapProductId_null() {
            assertThat(mapper.mapProductId(null)).isNull();
        }
    }

    @Nested
    class ProvisioningIdMappingTests {

        @Test
        void roundtrip() {
            UUID uuid = UUID.randomUUID();
            ProvisioningId id = new ProvisioningId(uuid);

            String mapped = mapper.map(id);
            ProvisioningId result = mapper.mapProvisioningId(mapped);

            assertThat(result.getValue()).isEqualTo(uuid);
        }

        @Test
        void nullMapping() {
            assertThat(mapper.map((ProvisioningId) null)).isNull();
            assertThat(mapper.mapProvisioningId(null)).isNull();
        }
    }

    @Nested
    class OrderIdMappingTests {

        @Test
        void roundtrip() {
            UUID uuid = UUID.randomUUID();
            OrderId id = new OrderId(uuid);

            String mapped = mapper.map(id);
            OrderId result = mapper.mapOrderId(mapped);

            assertThat(result.getValue()).isEqualTo(uuid);
        }

        @Test
        void nullMapping() {
            assertThat(mapper.map((OrderId) null)).isNull();
            assertThat(mapper.mapOrderId(null)).isNull();
        }
    }

    @Nested
    class BuyerIdMappingTests {

        @Test
        void roundtrip() {
            UUID uuid = UUID.randomUUID();
            BuyerId id = new BuyerId(uuid);

            String mapped = mapper.map(id);
            BuyerId result = mapper.mapBuyerId(mapped);

            assertThat(result.getValue()).isEqualTo(uuid);
        }

        @Test
        void nullMapping() {
            assertThat(mapper.map((BuyerId) null)).isNull();
            assertThat(mapper.mapBuyerId(null)).isNull();
        }
    }

    @Nested
    class ShipmentIdMappingTests {

        @Test
        void roundtrip() {
            UUID uuid = UUID.randomUUID();
            ShipmentId id = new ShipmentId(uuid);

            String mapped = mapper.map(id);
            ShipmentId result = mapper.mapShipmentId(mapped);

            assertThat(result.getValue()).isEqualTo(uuid);
        }

        @Test
        void nullMapping() {
            assertThat(mapper.map((ShipmentId) null)).isNull();
            assertThat(mapper.mapShipmentId(null)).isNull();
        }
    }
}

