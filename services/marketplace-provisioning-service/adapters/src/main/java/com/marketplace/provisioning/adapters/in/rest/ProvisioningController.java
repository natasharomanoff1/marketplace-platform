package com.marketplace.provisioning.adapters.in.rest;

import com.marketplace.provisioning.adapters.in.api.ProvisioningApi;
import com.marketplace.provisioning.adapters.in.api.model.ProvisioningResponse;
import com.marketplace.provisioning.adapters.in.rest.mapper.ProvisioningApiMapper;
import com.marketplace.provisioning.application.port.in.GetProvisioningByIdUseCase;
import com.marketplace.provisioning.application.port.in.GetProvisioningByOrderIdUseCase;
import com.marketplace.provisioning.application.port.in.GetProvisioningsByBuyerIdUseCase;
import com.marketplace.provisioning.domain.id.BuyerId;
import com.marketplace.provisioning.domain.id.OrderId;
import com.marketplace.provisioning.domain.id.ProvisioningId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ProvisioningController implements ProvisioningApi {

    private final GetProvisioningByIdUseCase getProvisioningByIdUseCase;
    private final GetProvisioningByOrderIdUseCase getProvisioningByOrderIdUseCase;
    private final GetProvisioningsByBuyerIdUseCase getProvisioningsByBuyerIdUseCase;
    private final ProvisioningApiMapper mapper;

    @Override
    public Mono<ResponseEntity<ProvisioningResponse>> getProvisioningById(
            UUID id,
            ServerWebExchange exchange) {

        return getProvisioningByIdUseCase
                .getById(new ProvisioningId(id))
                .map(mapper::toProvisioningResponse)
                .map(ResponseEntity::ok);
    }

    @Override
    public Mono<ResponseEntity<ProvisioningResponse>> getProvisioningByOrderId(
            UUID orderId,
            ServerWebExchange exchange) {

        return getProvisioningByOrderIdUseCase
                .getByOrderId(new OrderId(orderId))
                .map(mapper::toProvisioningResponse)
                .map(ResponseEntity::ok);
    }

    @Override
    public Mono<ResponseEntity<Flux<ProvisioningResponse>>> getProvisioningsByBuyerId(
            UUID buyerId,
            ServerWebExchange exchange) {

        Flux<ProvisioningResponse> responseFlux =
                getProvisioningsByBuyerIdUseCase
                        .getByBuyerId(new BuyerId(buyerId))
                        .map(mapper::toProvisioningResponse);

        return Mono.just(ResponseEntity.ok(responseFlux));
    }
}
