package com.marketplace.provisioning.adapters.config;

import com.marketplace.provisioning.application.port.in.*;
import com.marketplace.provisioning.application.port.out.persistence.ProvisioningRepositoryPort;
import com.marketplace.provisioning.application.service.AccessTokenService;
import com.marketplace.provisioning.application.service.ProvisioningService;
import com.marketplace.provisioning.application.usecase.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ProvisioningServiceConfig {

    @Bean
    public GetProvisioningByIdUseCase getProvisioningByIdUseCase(
            ProvisioningRepositoryPort repository
    ) {
        return new GetProvisioningByIdUseCaseImpl(repository);
    }

    @Bean
    public GetProvisioningByOrderIdUseCase getProvisioningByOrderIdUseCase(
            ProvisioningRepositoryPort repository
    ) {
        return new GetProvisioningByOrderIdUseCaseImpl(repository);
    }

    @Bean
    public GetProvisioningsByBuyerIdUseCase getProvisioningsByBuyerIdUseCase(
            ProvisioningRepositoryPort repository
    ) {
        return new GetProvisioningsByBuyerIdUseCaseImpl(repository);
    }

    @Bean
    public CreateProvisioningUseCase createProvisioningUseCase(
            ProvisioningRepositoryPort repository,
            ProvisioningService provisioningService
    ) {
        return new CreateProvisioningUseCaseImpl(repository, provisioningService);
    }

    @Bean
    public ValidateAccessTokenUseCase validateAccessTokenUseCase(
            ProvisioningRepositoryPort repository,
            AccessTokenService accessTokenService
    ) {
        return new ValidateAccessTokenUseCaseImpl(repository, accessTokenService);
    }

    @Bean
    public ProvisioningService provisioningService(
            AccessTokenService accessTokenService
    ) {
        return new ProvisioningService(accessTokenService);
    }

    @Bean
    public AccessTokenService accessTokenService() {
        return new AccessTokenService();
    }
}
