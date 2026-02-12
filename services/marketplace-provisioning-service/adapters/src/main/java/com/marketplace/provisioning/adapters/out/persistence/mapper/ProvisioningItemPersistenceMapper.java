package com.marketplace.provisioning.adapters.out.persistence.mapper;

import com.marketplace.provisioning.adapters.mapper.IdMapper;
import com.marketplace.provisioning.domain.model.ProvisioningItem;
import com.marketplace.provisioning.adapters.out.persistence.document.ProvisioningItemDocument;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = IdMapper.class)
public interface ProvisioningItemPersistenceMapper {

    ProvisioningItemDocument toProvisioningItemDocument(ProvisioningItem item);

    ProvisioningItem toProvisioningItem(ProvisioningItemDocument document);
}

