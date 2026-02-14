package com.marketplace.provisioning.adapters.in.rest;

import com.marketplace.provisioning.adapters.in.api.AccessApi;
import com.marketplace.provisioning.adapters.in.api.model.AccessValidationResponse;
import com.marketplace.provisioning.adapters.in.rest.mapper.AccessApiMapper;
import com.marketplace.provisioning.application.port.in.ValidateAccessTokenUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class AccessController implements AccessApi {

    private final ValidateAccessTokenUseCase useCase;
    private final AccessApiMapper mapper;

    @Override
    public Mono<ResponseEntity<AccessValidationResponse>> validateAccessToken(String token,
            ServerWebExchange exchange) {
        return useCase.validate(token)
                .map(mapper::toAccessValidationResponse)
                .map(ResponseEntity::ok);
    }
}
