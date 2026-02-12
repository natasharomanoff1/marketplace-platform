package com.marketplace.provisioning.adapters.config;

import com.marketplace.provisioning.application.port.in.GetProvisioningByIdUseCase;
import com.marketplace.provisioning.application.port.in.GetProvisioningByOrderIdUseCase;
import com.marketplace.provisioning.application.port.in.GetProvisioningsByBuyerIdUseCase;
import com.marketplace.provisioning.application.port.out.persistence.ProvisioningRepositoryPort;
import com.marketplace.provisioning.application.service.ProvisioningService;
import com.marketplace.provisioning.application.usecase.GetProvisioningByIdUseCaseImpl;
import com.marketplace.provisioning.application.usecase.GetProvisioningByOrderIdUseCaseImpl;
import com.marketplace.provisioning.application.usecase.GetProvisioningsByBuyerIdUseCaseImpl;
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
    public ProvisioningService provisioningService(
            ProvisioningRepositoryPort repository
    ) {
        return new ProvisioningService(repository);
    }
}
