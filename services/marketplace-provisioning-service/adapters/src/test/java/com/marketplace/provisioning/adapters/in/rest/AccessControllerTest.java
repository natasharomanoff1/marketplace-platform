package com.marketplace.provisioning.adapters.in.rest;

import com.marketplace.provisioning.adapters.in.api.model.AccessValidationResponse;
import com.marketplace.provisioning.adapters.in.rest.mapper.AccessApiMapper;
import com.marketplace.provisioning.application.port.in.ValidateAccessTokenUseCase;
import com.marketplace.provisioning.domain.exceptions.AccessTokenNotFoundException;
import com.marketplace.provisioning.domain.model.AccessValidationResult;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@WebFluxTest(AccessController.class)
class AccessControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private ValidateAccessTokenUseCase useCase;

    @MockitoBean
    private AccessApiMapper mapper;

    @Nested
    class ValidateAccessTokenTests {

        @Test
        void validateAccessToken_valid() {

            String token = "token";

            AccessValidationResult result =
                    AccessValidationResult.builder()
                            .valid(true)
                            .build();

            AccessValidationResponse response =
                    new AccessValidationResponse(true);

            when(useCase.validate(token))
                    .thenReturn(Mono.just(result));

            when(mapper.toAccessValidationResponse(result))
                    .thenReturn(response);

            webTestClient.get()
                    .uri("/access/{token}", token)
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody(AccessValidationResponse.class)
                    .value(res ->
                            assertThat(res.getValid()).isTrue()
                    );
        }

        @Test
        void validateAccessToken_notFound() {

            String token = "token";

            when(useCase.validate(token))
                    .thenReturn(Mono.error(new AccessTokenNotFoundException()));

            webTestClient.get()
                    .uri("/access/{token}", token)
                    .exchange()
                    .expectStatus().isNotFound();
        }
    }
}

