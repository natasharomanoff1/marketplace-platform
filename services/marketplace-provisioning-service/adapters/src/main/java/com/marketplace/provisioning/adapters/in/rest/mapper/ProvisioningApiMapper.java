package com.marketplace.provisioning.adapters.in.rest.mapper;

import com.marketplace.provisioning.adapters.in.api.model.ProvisioningResponse;
import com.marketplace.provisioning.adapters.mapper.IdMapper;
import com.marketplace.provisioning.domain.model.Provisioning;
import com.marketplace.provisioning.adapters.in.api.model.ProvisioningItem;
import org.mapstruct.Mapper;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper(componentModel = "spring", uses = IdMapper.class)
public interface ProvisioningApiMapper {

    ProvisioningResponse toProvisioningResponse(Provisioning provisioning);

    default OffsetDateTime map(Instant instant) {
        return instant == null ? null : instant.atOffset(ZoneOffset.UTC);
    }
}
