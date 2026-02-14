package com.marketplace.provisioning.adapters.in.rest.mapper;

import com.marketplace.provisioning.adapters.in.api.model.AccessValidationResponse;
import com.marketplace.provisioning.adapters.mapper.IdMapper;
import com.marketplace.provisioning.domain.model.AccessValidationResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.ZoneOffset;

@Mapper(componentModel = "spring", uses = IdMapper.class, imports = {ZoneOffset.class})
public interface AccessApiMapper {

    @Mapping(target = "expiresAt",
             expression = "java(result.getExpiresAt() != null ? " +
                     "result.getExpiresAt().atOffset(ZoneOffset.UTC) : null)")
    AccessValidationResponse toAccessValidationResponse(AccessValidationResult result);
}

