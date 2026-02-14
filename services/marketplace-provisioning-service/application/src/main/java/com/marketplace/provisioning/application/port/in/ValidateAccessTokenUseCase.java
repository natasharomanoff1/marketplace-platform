package com.marketplace.provisioning.application.port.in;

import com.marketplace.provisioning.domain.model.AccessValidationResult;
import reactor.core.publisher.Mono;

public interface ValidateAccessTokenUseCase {

    Mono<AccessValidationResult> validate(String token);
}
