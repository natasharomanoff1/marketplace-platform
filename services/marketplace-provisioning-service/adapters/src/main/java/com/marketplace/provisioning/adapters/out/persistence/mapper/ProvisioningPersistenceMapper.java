package com.marketplace.provisioning.adapters.out.persistence.mapper;

import com.marketplace.provisioning.adapters.mapper.IdMapper;
import com.marketplace.provisioning.adapters.out.persistence.document.ProvisioningItemDocument;
import com.marketplace.provisioning.domain.model.Provisioning;
import com.marketplace.provisioning.adapters.out.persistence.document.ProvisioningDocument;
import com.marketplace.provisioning.domain.model.ProvisioningItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = {IdMapper.class, ProvisioningItemPersistenceMapper.class})
public interface ProvisioningPersistenceMapper {

    ProvisioningDocument toProvisioningDocument(Provisioning provisioning);

    Provisioning toProvisioning(ProvisioningDocument document);
}
