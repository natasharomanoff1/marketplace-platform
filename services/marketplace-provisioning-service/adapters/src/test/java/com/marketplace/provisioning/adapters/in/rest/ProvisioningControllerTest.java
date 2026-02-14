package com.marketplace.provisioning.adapters.in.rest;

import com.marketplace.provisioning.adapters.in.api.model.ProvisioningResponse;
import com.marketplace.provisioning.adapters.in.rest.mapper.ProvisioningApiMapper;
import com.marketplace.provisioning.application.port.in.GetProvisioningByIdUseCase;
import com.marketplace.provisioning.application.port.in.GetProvisioningByOrderIdUseCase;
import com.marketplace.provisioning.application.port.in.GetProvisioningsByBuyerIdUseCase;
import com.marketplace.provisioning.adapters.testdata.ProvisioningTestDataFactory;
import com.marketplace.provisioning.domain.exceptions.ProvisioningNotFoundException;
import com.marketplace.provisioning.domain.model.Provisioning;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest(ProvisioningController.class)
class ProvisioningControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private ProvisioningApiMapper mapper;

    @MockitoBean
    private GetProvisioningByIdUseCase getProvisioningByIdUseCase;

    @MockitoBean
    private GetProvisioningByOrderIdUseCase getProvisioningByOrderIdUseCase;

    @MockitoBean
    private GetProvisioningsByBuyerIdUseCase getProvisioningsByBuyerIdUseCase;

    @Nested
    class GetProvisioningByIdTests {

        @Test
        void getProvisioningById() {

            UUID id = UUID.randomUUID();

            Provisioning provisioning =
                    ProvisioningTestDataFactory.aProvisioning();

            ProvisioningResponse response = new ProvisioningResponse();
            response.setId(id.toString());

            when(getProvisioningByIdUseCase.getById(any()))
                    .thenReturn(Mono.just(provisioning));

            when(mapper.toProvisioningResponse(provisioning))
                    .thenReturn(response);

            webTestClient.get()
                    .uri("/provisionings/{id}", id)
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody(ProvisioningResponse.class)
                    .value(res ->
                            assertThat(res.getId()).isEqualTo(id.toString())
                    );
        }

        @Test
        void getProvisioningById_notFound() {

            UUID id = UUID.randomUUID();

            when(getProvisioningByIdUseCase.getById(any()))
                    .thenReturn(Mono.error(new ProvisioningNotFoundException(id)));

            webTestClient.get()
                    .uri("/provisionings/{id}", id)
                    .exchange()
                    .expectStatus().isNotFound();
        }
    }

    @Nested
    class GetProvisioningByOrderIdTests {

        @Test
        void getProvisioningByOrderId() {

            UUID orderId = UUID.randomUUID();

            Provisioning provisioning =
                    ProvisioningTestDataFactory.aProvisioning();

            ProvisioningResponse response = new ProvisioningResponse();

            when(getProvisioningByOrderIdUseCase.getByOrderId(any()))
                    .thenReturn(Mono.just(provisioning));

            when(mapper.toProvisioningResponse(provisioning))
                    .thenReturn(response);

            webTestClient.get()
                    .uri("/provisionings/order/{orderId}", orderId)
                    .exchange()
                    .expectStatus().isOk();
        }

        @Test
        void getProvisioningByOrderId_notFound() {

            UUID orderId = UUID.randomUUID();

            when(getProvisioningByOrderIdUseCase.getByOrderId(any()))
                    .thenReturn(Mono.error(new ProvisioningNotFoundException(orderId)));

            webTestClient.get()
                    .uri("/provisionings/order/{orderId}", orderId)
                    .exchange()
                    .expectStatus().isNotFound();
        }
    }

    @Nested
    class GetProvisioningsByBuyerIdTests {

        @Test
        void getProvisioningsByBuyerId() {

            UUID buyerId = UUID.randomUUID();

            Provisioning provisioning =
                    ProvisioningTestDataFactory.aProvisioning();

            ProvisioningResponse response = new ProvisioningResponse();

            when(getProvisioningsByBuyerIdUseCase.getByBuyerId(any()))
                    .thenReturn(Flux.just(provisioning));

            when(mapper.toProvisioningResponse(provisioning))
                    .thenReturn(response);

            webTestClient.get()
                    .uri("/provisionings/buyer/{buyerId}", buyerId)
                    .exchange()
                    .expectStatus().isOk();
        }
    }
}

