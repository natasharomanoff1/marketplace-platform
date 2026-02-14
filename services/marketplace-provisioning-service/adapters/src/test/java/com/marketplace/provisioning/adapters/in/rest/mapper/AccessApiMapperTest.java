package com.marketplace.provisioning.adapters.in.rest.mapper;

import com.marketplace.provisioning.adapters.in.api.model.AccessValidationResponse;
import com.marketplace.provisioning.adapters.mapper.IdMapper;
import com.marketplace.provisioning.domain.id.ProductId;
import com.marketplace.provisioning.domain.model.AccessValidationResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        AccessApiMapperImpl.class,
        IdMapper.class
})
class AccessApiMapperTest {

    @Autowired
    private AccessApiMapper mapper;

    @Test
    void toAccessValidationResponse_full() {

        Instant expires = Instant.now();

        AccessValidationResult input =
                AccessValidationResult.builder()
                        .valid(true)
                        .productId(new ProductId(UUID.randomUUID()))
                        .expiresAt(expires)
                        .build();

        AccessValidationResponse result =
                mapper.toAccessValidationResponse(input);

        assertThat(result)
                .extracting(
                        AccessValidationResponse::getValid,
                        AccessValidationResponse::getProductId,
                        AccessValidationResponse::getExpiresAt
                )
                .containsExactly(
                        input.isValid(),
                        input.getProductId().getValue().toString(),
                        input.getExpiresAt().atOffset(ZoneOffset.UTC)
                );
    }

    @Test
    void toAccessValidationResponse_nullExpires() {

        AccessValidationResult input =
                AccessValidationResult.builder()
                        .valid(false)
                        .productId(new ProductId(UUID.randomUUID()))
                        .expiresAt(null)
                        .build();

        AccessValidationResponse result =
                mapper.toAccessValidationResponse(input);

        assertThat(result.getExpiresAt()).isNull();
    }

    @Test
    void toAccessValidationResponse_nullInput() {

        AccessValidationResponse result =
                mapper.toAccessValidationResponse(null);

        assertThat(result).isNull();
    }
}
