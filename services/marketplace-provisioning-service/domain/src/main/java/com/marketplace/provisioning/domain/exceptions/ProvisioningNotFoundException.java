package com.marketplace.provisioning.domain.exceptions;

import com.marketplace.common.errors.enums.ErrorCode;
import com.marketplace.common.errors.exceptions.MpException;

import java.util.UUID;

public class ProvisioningNotFoundException extends MpException {

    public ProvisioningNotFoundException(UUID id) {
        super(
                ErrorCode.LISTING_NOT_FOUND,
                "Provisioning with id " + id + " not found");
    }

}