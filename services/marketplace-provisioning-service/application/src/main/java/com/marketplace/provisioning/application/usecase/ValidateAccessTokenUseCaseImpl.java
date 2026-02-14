package com.marketplace.provisioning.application.usecase;

import com.marketplace.provisioning.application.port.in.ValidateAccessTokenUseCase;
import com.marketplace.provisioning.application.port.out.persistence.ProvisioningRepositoryPort;
import com.marketplace.provisioning.application.service.AccessTokenService;
import com.marketplace.provisioning.domain.exceptions.AccessTokenNotFoundException;
import com.marketplace.provisioning.domain.model.AccessValidationResult;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.time.Instant;

@RequiredArgsConstructor
public class ValidateAccessTokenUseCaseImpl implements ValidateAccessTokenUseCase {

    private final ProvisioningRepositoryPort repository;
    private final AccessTokenService accessTokenService;

    @Override
    public Mono<AccessValidationResult> validate(String token) {

        String tokenHash = accessTokenService.hash(token);

        return repository.findByAccessTokenHash(tokenHash)
                .switchIfEmpty(Mono.error(new AccessTokenNotFoundException()))
                .map(item -> {

                    boolean valid =
                            item.getExpiresAt() != null &&
                                    item.getExpiresAt().isAfter(Instant.now());

                    return AccessValidationResult.builder()
                            .valid(valid)
                            .productId(item.getProductId())
                            .expiresAt(item.getExpiresAt())
                            .build();
                });
    }
}

